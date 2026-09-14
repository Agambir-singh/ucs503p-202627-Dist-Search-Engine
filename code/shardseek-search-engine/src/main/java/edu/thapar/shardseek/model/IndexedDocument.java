package edu.thapar.shardseek.model;

import java.time.Instant;
import java.util.List;

public record IndexedDocument(
    String id,
    String title,
    String content,
    String mediaType,
    List<String> terms,
    Instant createdAt) {}
