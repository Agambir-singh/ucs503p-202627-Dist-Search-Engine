package edu.thapar.shardseek.service;

import java.util.*;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

@Component
public class TextAnalyzer {
  private static final Pattern TOKEN = Pattern.compile("[a-z0-9][a-z0-9_-]*");
  private static final Set<String> STOP =
      Set.of(
          "a", "an", "and", "are", "as", "at", "be", "by", "for", "from", "has", "have", "if", "in",
          "into", "is", "it", "its", "of", "on", "or", "that", "the", "to", "was", "were", "will",
          "with", "you", "your", "this", "these", "those", "not", "no");

  public List<String> tokenize(String text) {
    var matcher = TOKEN.matcher(text.toLowerCase(Locale.ROOT));
    var output = new ArrayList<String>();
    while (matcher.find()) {
      String token = matcher.group();
      if (!STOP.contains(token)) output.add(token);
    }
    return output;
  }
}
