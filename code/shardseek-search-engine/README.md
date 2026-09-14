# ShardSeek - Single-Node Search Engine Prototype

A functional Java + Spring Boot implementation for the Phase 1-3 submission. It intentionally implements one real search node, not a simulated distributed system.

## Complete pipeline

`Documents -> normalization -> tokenization -> keyword extraction -> inverted index -> H2 persistence -> REST API -> coordinator -> BM25 -> Top-K -> web UI`

## What works

- Multi-file text upload through the web UI or `POST /api/documents`.
- Text normalization and stop-word-aware tokenization in `TextAnalyzer`.
- Keyword extraction as unique token frequency statistics for each document.
- A genuine inverted index: `term -> document ID -> term frequency`.
- H2 file-backed persistence for source documents and `document_terms`; restart the application and the index rebuilds automatically from persisted records.
- `GET /api/search?q=...` calculates real BM25 scores (`k1=1.5`, `b=0.75`) and returns sorted Top-K results.
- Small TTL query cache; indexing clears it so fresh documents appear immediately.
- Search results show score, matched terms, a matching snippet, and a full document viewer.

## Run

Install Java 21+ and Maven 3.9+, then run:

```powershell
mvn spring-boot:run
```

Visit `http://localhost:8080`. The H2 database is saved in `./data/shardseek` and its local development console is at `http://localhost:8080/h2-console`.

## API

```powershell
curl.exe -F "files=@notes.txt" http://localhost:8080/api/documents
curl.exe "http://localhost:8080/api/search?q=distributed+indexing&limit=10"
curl.exe http://localhost:8080/api/metrics
```

## Honest scope boundary

The future roadmap includes 8 physical shards, concurrent fan-out, Redis locks, PostgreSQL, etcd, Docker microservices, vector embeddings, and hybrid ranking. Those are not claimed as implemented in this submission prototype. The persistence and API boundaries here provide the migration point for those phases.
