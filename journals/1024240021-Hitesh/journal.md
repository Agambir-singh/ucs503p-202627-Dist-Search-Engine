Project Journal -- Hitesh
Project: Distributed Search Engine
Work Completed So Far
Week 1 -- Study of Distributed Search Engine Architecture
1. Study of Distributed Search Engine Architecture

Studied the basic architecture and working principles of a Distributed
Search Engine. The study focused on understanding how a search system
can handle a large collection of documents by distributing data and
processing across multiple services.

The major concepts studied included:
- Microservices architecture
- Horizontal scaling
- Workload distribution
- Fault tolerance and load balancing
- Data partitioning and replication
- Communication between distributed services
Based on this study, the major components of the proposed system were
identified, including Document Ingestion, Text Analyzer, Indexing,
Search Shards, Search Coordinator, Ranking, and Caching.
Week 2 -- Understanding Search Engine Concepts
2. Study of Search Engine Concepts

Studied the basic concepts involved in the internal working of search
engines. This included understanding how documents are processed and
converted into a searchable format.

The topics covered were:
- Text tokenization and preprocessing
- Stop-word removal
- Inverted index
- TF-IDF and relevance-based ranking
- Distributed query processing
Particular attention was given to the inverted index, as it allows
the system to efficiently identify documents containing the words
present in a search query without scanning the complete document
collection.
Week 3 -- Backend Development and System Design
3. Learning and Initial Development Using Spring Boot

Started learning and working with Spring Boot for developing the
backend of the project. The objective was to understand how individual
components of the distributed search engine could be developed as
independent services and communicate through REST APIs.

Initial backend structures and APIs were developed as part of this
phase.

4. System Requirements and Workflow Design

Worked on identifying the functional requirements of the system and
designing its overall workflow. The workflow was designed to cover the
complete process, starting from document submission and text processing
to indexing, searching, ranking, and displaying the final results.

This helped establish a clear understanding of how the different
services would interact with each other.
Week 4 -- System Modelling
5. Activity Diagram Implementation

Designed and implemented the Activity Diagram for the proposed
system. The diagram represents the sequential flow of activities
involved in the search process.

It covers major activities such as:
- Submission of documents
- Text analysis and preprocessing
- Indexing of processed documents
- Submission of a search query
- Searching across different shards
- Ranking and aggregation of results
- Displaying the final search results
6. ER Diagram

Created the initial Entity Relationship (ER) Diagram for the
project. The diagram was prepared to understand and represent the major
entities, their attributes, and the relationships between them.

This provided a basic structure for understanding how the data would be
organized within the system.
Week 5 -- Initial Microservice Implementation
7. Implementation of Text Analyzer Service

Implemented the Text Analyzer Service using Spring Boot. The service
is responsible for processing the input text before it is sent for
indexing.

The implementation includes basic text preprocessing operations such as
tokenization, normalization, and stop-word removal. This prepares
the document content in a consistent form so that it can be efficiently
stored in the inverted index and used during searching.

8. Implementation of Search Coordinator Service

Implemented the initial Search Coordinator Service for managing
search requests in the distributed system.

The service is responsible for receiving a user's search query and
coordinating the search process across the available search shards. It
helps in forwarding the query to the appropriate shards, collecting the
responses, and preparing the results for further ranking and
aggregation.

This implementation provided a practical understanding of how different
services can work together to perform a distributed search operation.
Week 6 -- Indexing Module Development
9. Development of Inverted Indexing Module

Worked on the implementation of the initial Inverted Indexing
Module. The objective was to organize processed document tokens into a
searchable data structure that maps terms to their corresponding
document references.

The implementation focused on:
- Creating term-to-document mappings
- Storing document identifiers against indexed terms
- Handling processed tokens received from the Text Analyzer Service
- Understanding efficient retrieval using an inverted index
Week 7 -- Search Processing and Service Integration
10. Search Query Processing

Worked on the initial implementation of the search query processing
workflow. The objective was to process incoming search queries and
prepare them for execution against the indexed documents.

The work focused on:
- Accepting user search queries
- Query preprocessing and tokenization
- Forwarding queries for document retrieval
- Understanding result aggregation from multiple search shards
11. Integration of Backend Services

Worked on integrating the developed backend components through REST API
communication.

Focused on understanding how the Text Analyzer and Search Coordinator
services exchange data and how the indexing workflow can be connected
with the overall distributed architecture.
Week 8 -- System Design and Integration Refinement
12. System Integration and Workflow Refinement

Worked on refining the overall system workflow by examining the
interaction between the different modules.

Reviewed the architecture and implementation of the individual services
to identify integration requirements and areas for further development.

This phase helped establish a clearer implementation roadmap for
completing the distributed search engine.

13. Sequence Diagram

Created the Sequence Diagram for the Distributed Search Engine to
represent the interactions and communication between different services
over time.

The diagram illustrates the flow of requests and responses between major
components, including:
- User
- Search Coordinator
- Text Analyzer
- Search Shards
- Ranking Service
- Document Storage
It helped in understanding the order of communication between services
during document ingestion and search query execution.

14. Class Diagram

Created the Class Diagram to represent the structural design of the
proposed system. The diagram illustrates the major classes, their
attributes, methods, and relationships.

It covers the important components involved in document processing,
indexing, query handling, search execution, and result ranking.

This provided a clearer understanding of the object-oriented structure
and relationships within the system.
