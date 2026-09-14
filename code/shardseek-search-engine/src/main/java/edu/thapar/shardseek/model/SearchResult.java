package edu.thapar.shardseek.model;

import java.util.Set;

public record SearchResult(
    String documentId, String title, String snippet, double score, Set<String> matchedTerms) {}
