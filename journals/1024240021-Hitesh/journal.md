Project Journal - Hitesh

Project: Distributed Search Engine

Work Completed So far

1. Understanding the Distributed Search Engine Architecture

I looked at the design and goals of the Distributed Search Engine project. The main goal of the Distributed Search Engine is to build a search system that can grow and manage large collections of documents by sharing the work across many different services.

I examined the parts of a distributed search system and learned how each part talks to the others to give fast and accurate document results.

The key ideas I studied include:

- Principles for designing a distributed system

- Service‑oriented and microservices design

- Scaling horizontally and sharing workloads

- Fault tolerance and how the system recovers from failures

- Balancing the load between search nodes

- Ways to split and duplicate data

- How distributed services communicate

The planned design has several separate services that each handle a stage of the search process, such as:

- Document Ingestion Service – collects and prepares new documents.

- Indexing Service – builds and keeps the inverted index that lets the system find documents quickly.

- Search Shard Services – store and fetch indexed data across many servers.

- Query Coordinator Service – splits user queries to the shards and combines the answers.

- Ranking Service – scores how relevant each result is and orders the list.

- Caching Service – speeds up the system by saving popular queries and results.

2. Understanding Search Engine Internals

I explored the ideas behind building a search engine and how modern systems find and return information quickly.

The main ideas I looked at include:

Inverted Indexing

I learned why inverted indexes are essential in search engines and how they link words to the documents that contain those words. This lets the system find results faster than scanning every document.

Topics covered:

- Splitting text into tokens. Cleaning it

- Removing common words that add no meaning

- Reducing words to their root form and normalizing them

- Mapping terms to documents

- Building and saving the index

Search and Ranking Algorithms

I studied the different ways a search engine decides which results are most useful.

Ideas explored:

- Term Frequency‑Inverse Document Frequency (TF‑IDF)

- How relevance scores are calculated

- Measuring how similar a query is to a document

- The full ranking process

- Future plans to use machine learning for ranking

Distributed Query Processing

I looked at how a search query moves through a distributed system:

- User sends a query.

- Query Coordinator receives and checks the request.

- Query is sent to search shards.

- Each shard finds local matches.

- Results are combined and ranked.

- Final list is returned to the user.

3. Learning Spring Boot for Backend Microservices Development

I began studying Spring Boot, the tool that will build the backend services for the Distributed Search Engine.

The focus was on how Spring Boot can create REST services.

Points I learned:

- How a Spring Boot project is organized

- Managing dependencies with Maven

- Building REST APIs

- Using Controllers, Services and Repository layers

- Injecting dependencies into components

- Managing configuration settings

- Handling errors and exceptions

- Working with application properties

- Connecting services to databases

I also saw how Spring Boot allows each service to operate independently while still talking to other services in a distributed setup.

4. Designing the System Workflow and Functional Requirements

After studying the project needs I wrote the system workflow. Mapped the main interactions between parts.

The workflow helped me see:

- How documents enter the system

- How indexing happens

- How search queries are processed

- How results come from nodes

- How caching speeds up responses

- How the system deals with failures

This turned the high‑level ideas into concrete parts that can be built.

5. Preparing the Use Case Diagram

I drew the Use Case Diagram for the Distributed Search Engine from the system needs.

The diagram shows:

- actors of the system

- How users interact with the system

- Administrative actions

- Document handling actions

- Search and retrieval steps

The diagram gave a clearer view of what each service should do.

Technical Understanding Gained

During the phase I learned:

- How to design scalable distributed applications

- How to split a big system into small microservices

- How a search engine is built and how indexing works

- How to create backend APIs with Spring Boot

- How to analyze requirements and model a system

- How to build parts that can grow and stay reliable

Next Steps

The next phase will start building the Distributed Search Engine.

Planned tasks include:

- Creating the Spring Boot backend structure

- Laying out the microservices framework

- Building the Document Ingestion Service

- Designing database tables, for document data

- Implementing the indexing workflow

- Creating APIs for uploading documents and searching

- Connecting services together

- Looking into distributed storage and caching

This next phase moves from design and research into coding and combining the core parts of the Distributed Search Engine.