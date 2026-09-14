# Project Journal
**Project: Distributed Search Engine**

## Week 1 — Understanding the Project Requirements

I studied and understood the requirements and objectives of the proposed Distributed Search Engine project. The project aims to build a scalable search platform capable of indexing, processing, and retrieving information from large document collections.

I learned about the main requirements and concepts involved in the project, including:

- Distributed search architecture
- Inverted indexing
- Relevance-based ranking
- Caching
- Fault tolerance and scalability
- Microservices-based architecture

The project proposal describes separate services for document ingestion, indexing, query coordination, search shards, ranking, and caching.

## Week 2 — Learning Spring Boot

I started learning Spring Boot, which will be used to develop the backend microservices and Search API for the project.

I studied how REST APIs are structured in Spring Boot, how services communicate with each other, and how it can be used to develop the query coordinator, indexing service, and search shard components of the search engine.

## Week 3 — Preparing the Project Presentation

I prepared the project presentation (PPT) summarizing the problem statement, proposed architecture, and evaluation criteria of the Distributed Search Engine.

The presentation helped organize the project's key ideas into a clear, structured overview for explaining the system to others.

## Week 4 — Preparing the Dataflow Diagram

I prepared the Dataflow Diagram (DFD) for the project after studying the project requirements and workflow.

The diagram was created at three levels:

- **Level 0** — a context diagram showing the system's interaction with the User and Content Source.
- **Level 1** — detailed flows for the indexing pipeline (document ingestion → indexing → inverted index) and the query pipeline (query → coordinator → cache check → shards → ranking → results).
- **Level 2** — internal breakdowns of the ingestion process, shard-level query handling, and the ranking module's hybrid scoring.

The dataflow diagram helped in understanding how data moves through the system and the responsibilities of each component before beginning implementation.

## Week 5 — Prototype Report and Proposal Update

I prepared the Prototype Report for the project, documenting the design and implementation of the working backend prototype (ShardSeek) built so far.

The report covers:

- The problem statement and project objectives for the search engine prototype.
- The system architecture, including the Client Web UI, the REST API layer, the SearchCoordinator, and the H2-backed persistence layer.
- The document ingestion and indexing pipeline, showing how uploaded documents are tokenized, filtered, and converted into an inverted index.
- The BM25-based search and ranking engine, including how queries are scored, cached, and returned as ranked results.
- Testing methodology and performance metrics collected from the prototype.
- Key findings, current limitations, and planned work for the next phase of the project.

Preparing this report helped consolidate the implementation work completed so far into a clear technical document, and highlighted areas that need further testing and refinement before the final submission.

I also updated the original project proposal to improve its readability and presentation. The Data Flow Diagram and Use Case Diagram sections were removed, as the proposal had become too text-heavy and repetitive with the diagrams already covered in more detail elsewhere.

In their place, I added two focused diagrams that better summarize the project at a proposal stage:

- A core workflow diagram showing the end-to-end flow from document ingestion to ranked results.
- A high-level system architecture diagram showing how the Query Coordinator, Cache, Indexer, Search Shards, and Storage Layer fit together.

This update made the proposal shorter, easier to read, and more visually structured, while keeping all the original problem statement, solution approach, evaluation criteria, and scope sections intact.
