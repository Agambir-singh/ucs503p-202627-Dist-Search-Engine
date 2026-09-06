Thapar Institute of Engineering and Technology
Department of Computer Science and Engineering (CSED)

# Distributed Search Engine

Author(s):
- Nityam Mantri (Roll No: 1024240002)
- Hitesh (Roll No: 1024240021)
- Agambir Singh (Roll No: 1024240032)

Submitted to: Dr. Jeelani

August 2026

---

## Project Overview

Distributed Search Engine is a scalable, production-oriented search platform built with Java Spring Boot, designed to overcome the limitations of traditional single-node search systems. It separates document ingestion, indexing, query processing, ranking, and storage into independent services, distributing search operations across multiple shards for horizontal scalability, low query latency, and fault tolerance. A hybrid ranking approach combines BM25 keyword scoring with vector embedding similarity to improve semantic relevance alongside exact keyword matching.

## System Architecture

The system follows a microservice-based architecture:

1. **Document Ingestion Service:** Collects documents and performs preprocessing — text normalization and tokenization — to prepare data for indexing.
2. **Indexing Service:** Generates and maintains inverted indexes mapping terms to documents for efficient retrieval, with index data distributed across shards.
3. **Search Shard Services:** Maintain independent portions of the search index and execute queries concurrently.
4. **Search/Query Coordinator:** Receives incoming queries, performs concurrent searches across shards, and merges shard results using a Top-K ranking algorithm to produce the final ranked response.
5. **Ranking Module:** Improves result ordering via hybrid ranking — BM25-based scoring combined with vector embedding similarity for semantic relevance.
6. **Caching Layer (Redis):** Reduces repeated query processing time and improves system responsiveness.
7. **Storage Layer (PostgreSQL):** Stores documents, metadata, and persistent application information.


## Core Workflow

1. Documents are collected via the ingestion pipeline and processed through text normalization and tokenization.
2. The indexing service builds an inverted index mapping terms to documents.
3. Index data is distributed among multiple search shards for parallel retrieval.
4. User queries are received by the coordinator service.
5. The coordinator performs concurrent searches across shards.
6. Shard results are merged using a Top-K ranking algorithm.
7. Final ranked documents are returned to the user.

## Evaluation Criteria

**Primary metrics**
- Query latency — response time under increasing document size and query load.
- Search relevance — ranking accuracy against expected relevant documents.

**Secondary metrics**
- Queries processed per second
- Cache hit ratio
- System availability
- Resource utilization (CPU/memory under concurrent load)

## Installation

To run the project locally:

```
# Clone the repository
git clone https://github.com/Agambir-singh/ucs503p-202627-Dist-Search-Engine.git

```
