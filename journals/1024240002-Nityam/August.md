# Project Journal

## Project: Distributed Search Engine

### Work Completed So Far

#### 1. Understanding the Project Requirements

I studied and understood the requirements and objectives of the proposed **Distributed Search Engine** project. The project aims to build a scalable search platform capable of indexing, processing, and retrieving information from large document collections.

I learned about the main requirements and concepts involved in the project, including:

- Distributed search architecture
- Inverted indexing
- Relevance-based ranking
- Caching
- Fault tolerance and scalability
- Microservices-based architecture

The project proposal describes separate services for document ingestion, indexing, query coordination, search shards, ranking, and caching.

#### 2. Learning Spring Boot

I also started learning **Spring Boot**, which will be used to develop the backend microservices and Search API for the project.

I studied how REST APIs are structured in Spring Boot, how services communicate with each other, and how it can be used to develop the query coordinator, indexing service, and search shard components of the search engine.

#### 3. Preparing the Project Presentation

I prepared the **project presentation (PPT)** summarizing the problem statement, proposed architecture, and evaluation criteria of the Distributed Search Engine.

The presentation helped organize the project's key ideas into a clear, structured overview for explaining the system to others.

#### 4. Preparing the Dataflow Diagram

I prepared the **Dataflow Diagram (DFD)** for the project after studying the project requirements and workflow.

The diagram was created at three levels:

- **Level 0** — a context diagram showing the system's interaction with the User and Content Source.
- **Level 1** — detailed flows for the indexing pipeline (document ingestion → indexing → inverted index) and the query pipeline (query → coordinator → cache check → shards → ranking → results).
- **Level 2** — internal breakdowns of the ingestion process, shard-level query handling, and the ranking module's hybrid scoring.

The dataflow diagram helped in understanding how data moves through the system and the responsibilities of each component before beginning implementation.

