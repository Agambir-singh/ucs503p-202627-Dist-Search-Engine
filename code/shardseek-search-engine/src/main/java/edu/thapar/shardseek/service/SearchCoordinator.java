package edu.thapar.shardseek.service;

import edu.thapar.shardseek.model.*;
import edu.thapar.shardseek.persistence.*;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import java.time.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/** Single-node coordinator: owns a persisted inverted index and ranks locally with BM25. */
@Service
public class SearchCoordinator {
  private final TextAnalyzer analyzer;
  private final DocumentRepository documentRepository;
  private final TermStatRepository termRepository;
  private final Duration ttl;
  private final Map<String, IndexedDocument> documents = new ConcurrentHashMap<>();
  private final Map<String, Map<String, Integer>> invertedIndex = new ConcurrentHashMap<>();
  private final Map<String, CachedResponse> cache = new ConcurrentHashMap<>();

  public SearchCoordinator(
      TextAnalyzer analyzer,
      DocumentRepository documentRepository,
      TermStatRepository termRepository,
      @Value("${shardseek.cache-ttl-seconds:300}") long ttlSeconds) {
    this.analyzer = analyzer;
    this.documentRepository = documentRepository;
    this.termRepository = termRepository;
    this.ttl = Duration.ofSeconds(ttlSeconds);
  }

  @PostConstruct
  @Transactional
  public void restoreIndex() {
    List<TermStatEntity> stats = termRepository.findAll();
    documentRepository
        .findAll()
        .forEach(
            e -> {
              List<String> terms =
                  stats.stream()
                      .filter(s -> s.getDocument().getId().equals(e.getId()))
                      .flatMap(s -> Collections.nCopies(s.getTermFrequency(), s.getTerm()).stream())
                      .toList();
              documents.put(e.getId(), toModel(e, terms));
            });
    stats.forEach(
        s ->
            invertedIndex
                .computeIfAbsent(s.getTerm(), x -> new ConcurrentHashMap<>())
                .put(s.getDocument().getId(), s.getTermFrequency()));
  }

  @Transactional
  public synchronized IndexedDocument ingest(String title, String content, String mediaType) {
    List<String> terms = analyzer.tokenize(title + " " + content);
    DocumentEntity entity =
        documentRepository.save(
            new DocumentEntity(
                UUID.randomUUID().toString(), title, content, mediaType, Instant.now()));
    Map<String, Integer> frequencies =
        terms.stream().collect(Collectors.groupingBy(t -> t, Collectors.summingInt(t -> 1)));
    termRepository.saveAll(
        frequencies.entrySet().stream()
            .map(e -> new TermStatEntity(entity, e.getKey(), e.getValue()))
            .toList());
    IndexedDocument model = toModel(entity, terms);
    documents.put(model.id(), model);
    frequencies.forEach(
        (term, tf) ->
            invertedIndex
                .computeIfAbsent(term, x -> new ConcurrentHashMap<>())
                .put(model.id(), tf));
    cache.clear();
    return model;
  }

  public SearchResponse search(String rawQuery, int limit) {
    long started = System.nanoTime();
    String key = rawQuery.trim().toLowerCase(Locale.ROOT) + ":" + limit;
    CachedResponse saved = cache.get(key);
    if (saved != null && saved.expiresAt().isAfter(Instant.now()))
      return new SearchResponse(rawQuery, saved.response().results(), elapsed(started), true);
    List<String> queryTerms = analyzer.tokenize(rawQuery);
    int n = documents.size();
    double averageLength =
        documents.values().stream().mapToInt(d -> d.terms().size()).average().orElse(1);
    Map<String, Double> scores = new HashMap<>();
    Map<String, Set<String>> matches = new HashMap<>();
    for (String term : new LinkedHashSet<>(queryTerms)) {
      Map<String, Integer> postings = invertedIndex.get(term);
      if (postings == null) continue;
      double idf = Math.log(1 + (n - postings.size() + .5) / (postings.size() + .5));
      postings.forEach(
          (id, tf) -> {
            IndexedDocument d = documents.get(id);
            double score =
                idf * (tf * 2.5) / (tf + 1.5 * (1 - .75 + .75 * d.terms().size() / averageLength));
            scores.merge(id, score, Double::sum);
            matches.computeIfAbsent(id, x -> new LinkedHashSet<>()).add(term);
          });
    }
    List<SearchResult> results =
        scores.entrySet().stream()
            .map(
                e -> {
                  IndexedDocument d = documents.get(e.getKey());
                  return new SearchResult(
                      d.id(),
                      d.title(),
                      snippet(d.content(), matches.get(d.id())),
                      e.getValue(),
                      matches.get(d.id()));
                })
            .sorted(Comparator.comparingDouble(SearchResult::score).reversed())
            .limit(limit)
            .toList();
    SearchResponse response = new SearchResponse(rawQuery, results, elapsed(started), false);
    cache.put(key, new CachedResponse(response, Instant.now().plus(ttl)));
    return response;
  }

  public Optional<IndexedDocument> getDocument(String id) {
    return Optional.ofNullable(documents.get(id));
  }

  public Map<String, Object> metrics() {
    return Map.of(
        "nodeStatus",
        "online",
        "documents",
        documents.size(),
        "terms",
        invertedIndex.size(),
        "cacheEntries",
        cache.size(),
        "indexEntries",
        invertedIndex.values().stream().mapToInt(Map::size).sum());
  }

  private IndexedDocument toModel(DocumentEntity e, List<String> terms) {
    return new IndexedDocument(
        e.getId(), e.getTitle(), e.getContent(), e.getMediaType(), terms, e.getCreatedAt());
  }

  private String snippet(String content, Set<String> terms) {
    String lower = content.toLowerCase(Locale.ROOT);
    int at = terms.stream().mapToInt(lower::indexOf).filter(i -> i >= 0).findFirst().orElse(0);
    int start = Math.max(0, at - 90), end = Math.min(content.length(), at + 220);
    return (start > 0 ? "…" : "")
        + content.substring(start, end).replaceAll("\\s+", " ")
        + (end < content.length() ? "…" : "");
  }

  private double elapsed(long started) {
    return Math.round((System.nanoTime() - started) / 10000.0) / 100.0;
  }

  private record CachedResponse(SearchResponse response, Instant expiresAt) {}
}
