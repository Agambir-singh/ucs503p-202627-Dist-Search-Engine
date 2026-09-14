package edu.thapar.shardseek.model;

import java.util.List;

public record SearchResponse(
    String query, List<SearchResult> results, double latencyMs, boolean cacheHit) {}
