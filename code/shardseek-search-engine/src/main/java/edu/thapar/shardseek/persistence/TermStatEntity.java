package edu.thapar.shardseek.persistence;

import jakarta.persistence.*;

@Entity
@Table(
    name = "document_terms",
    uniqueConstraints = @UniqueConstraint(columnNames = {"document_id", "term"}))
public class TermStatEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "document_id")
  private DocumentEntity document;

  @Column(nullable = false)
  private String term;

  @Column(nullable = false)
  private int termFrequency;

  protected TermStatEntity() {}

  public TermStatEntity(DocumentEntity document, String term, int termFrequency) {
    this.document = document;
    this.term = term;
    this.termFrequency = termFrequency;
  }

  public DocumentEntity getDocument() {
    return document;
  }

  public String getTerm() {
    return term;
  }

  public int getTermFrequency() {
    return termFrequency;
  }
}
