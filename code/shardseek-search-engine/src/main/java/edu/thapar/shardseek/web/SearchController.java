  package edu.thapar.shardseek.web;

import edu.thapar.shardseek.model.*;
import edu.thapar.shardseek.service.SearchCoordinator;
import jakarta.validation.constraints.NotBlank;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class SearchController {
  private final SearchCoordinator coordinator;

  public SearchController(SearchCoordinator coordinator) {
    this.coordinator = coordinator;
  }

  @GetMapping("/search")
  public SearchResponse search(
      @RequestParam @NotBlank String q, @RequestParam(defaultValue = "10") int limit) {
    return coordinator.search(q, Math.min(Math.max(limit, 1), 50));
  }

  @PostMapping(value = "/documents", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public List<IndexedDocument> upload(@RequestParam("files") List<MultipartFile> files)
      throws IOException {
    List<IndexedDocument> added = new ArrayList<>();
    for (MultipartFile file : files) {
      if (!file.isEmpty())
        added.add(
            coordinator.ingest(
                Objects.requireNonNullElse(file.getOriginalFilename(), "untitled.txt"),
                new String(file.getBytes(), StandardCharsets.UTF_8),
                Objects.requireNonNullElse(file.getContentType(), "text/plain")));
    }
    return added;
  }

  @PostMapping(value = "/documents/text", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public IndexedDocument addText(@RequestBody TextDocument body) {
    return coordinator.ingest(body.title(), body.content(), "text/plain");
  }

  @GetMapping("/documents/{id}")
  public IndexedDocument document(@PathVariable String id) {
    return coordinator
        .getDocument(id)
        .orElseThrow(() -> new NoSuchElementException("Document not found"));
  }

  @GetMapping("/metrics")
  public Map<String, Object> metrics() {
    return coordinator.metrics();
  }

  public record TextDocument(@NotBlank String title, @NotBlank String content) {}
}
