# Project Journal – Hitesh

**Project: Distributed Search Engine**

## Work Completed So Far

###Week - 1

### 1. Study of Distributed Search Engine Architecture

Studied the basic architecture and working principles of a **Distributed Search Engine**. The study focused on understanding how a search system can handle a large collection of documents by distributing data and processing across multiple services.

The major concepts studied included:

* Microservices architecture
* Horizontal scaling
* Workload distribution
* Fault tolerance and load balancing
* Data partitioning and replication
* Communication between distributed services

Based on this study, the major components of the proposed system were identified, including **Document Ingestion, Text Analyzer, Indexing, Search Shards, Search Coordinator, Ranking, and Caching**.

###Week 2

### 2. Study of Search Engine Concepts

Studied the basic concepts involved in the internal working of search engines. This included understanding how documents are processed and converted into a searchable format.

The topics covered were:

* Text tokenization and preprocessing
* Stop-word removal
* Inverted index
* TF-IDF and relevance-based ranking
* Distributed query processing

Particular attention was given to the **inverted index**, as it allows the system to efficiently identify documents containing the words present in a search query without scanning the complete document collection.

### 3. Learning and Initial Development Using Spring Boot

Started learning and working with **Spring Boot** for developing the backend of the project. The objective was to understand how individual components of the distributed search engine could be developed as independent services and communicate through REST APIs.

Initial backend structures and APIs were developed as part of this phase.

###Week 3

### 4. System Requirements and Workflow Design

Worked on identifying the functional requirements of the system and designing its overall workflow. The workflow was designed to cover the complete process starting from document submission and text processing to indexing, searching, ranking, and displaying the final results.

This helped in establishing a clear understanding of how the different services would interact with each other.

###Week 4

### 5. Activity Diagram Implementation

Designed and implemented the **Activity Diagram** for the proposed system. The diagram represents the sequential flow of activities involved in the search process.

It covers major activities such as:

* Submission of documents
* Text analysis and preprocessing
* Indexing of processed documents
* Submission of a search query
* Searching across different shards
* Ranking and aggregation of results
* Displaying the final search results

### 6. ER Diagram

Created the initial **Entity Relationship (ER) Diagram** for the project. The diagram was prepared to understand and represent the major entities, their attributes, and the relationships between them.

This provided a basic structure for understanding how the data would be organized within the system.

###Week 5

### 7. Implementation of Text Analyzer Service

Implemented the **Text Analyzer Service** using Spring Boot. The service is responsible for processing the input text before it is sent for indexing.

The implementation includes basic text preprocessing operations such as **tokenization, normalization, and stop-word removal**. This prepares the document content in a consistent form so that it can be efficiently stored in the inverted index and used during searching.

### 8. Implementation of Search Coordinator Service

Implemented the initial **Search Coordinator Service** for managing search requests in the distributed system.

The service is responsible for receiving a user's search query and coordinating the search process across the available search shards. It helps in forwarding the query to the appropriate shards, collecting the responses, and preparing the results for further ranking and aggregation.

This implementation provided a practical understanding of how different services can work together to perform a distributed search operation.
