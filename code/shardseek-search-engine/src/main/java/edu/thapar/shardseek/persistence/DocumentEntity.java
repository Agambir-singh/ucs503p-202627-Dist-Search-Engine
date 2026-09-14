package edu.thapar.shardseek.persistence;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "documents")
public class DocumentEntity {
  @Id private String id;

  @Column(nullable = false)
  private String title;

  @Lob
  @Column(nullable = false)
  private String content;

  @Column(nullable = false)
  private String mediaType;

  @Column(nullable = false)
  private Instant createdAt;

  protected DocumentEntity() {}

  public DocumentEntity(
      String id, String title, String content, String mediaType, Instant createdAt) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.mediaType = mediaType;
    this.createdAt = createdAt;
  }

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getContent() {
    return content;
  }

  public String getMediaType() {
    return mediaType;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }
}
