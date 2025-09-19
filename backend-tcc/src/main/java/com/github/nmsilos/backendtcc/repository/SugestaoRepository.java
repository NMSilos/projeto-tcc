package com.github.nmsilos.backendtcc.repository;

import com.github.nmsilos.backendtcc.model.Sugestao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SugestaoRepository extends JpaRepository<Sugestao, Long> {
}
