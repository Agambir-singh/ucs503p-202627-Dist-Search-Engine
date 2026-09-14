package edu.thapar.shardseek.persistence;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermStatRepository extends JpaRepository<TermStatEntity, Long> {
  List<TermStatEntity> findByTerm(String term);

  long countByTerm(String term);
}
