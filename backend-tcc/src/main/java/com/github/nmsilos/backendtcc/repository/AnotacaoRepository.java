package com.github.nmsilos.backendtcc.repository;

import com.github.nmsilos.backendtcc.model.Anotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnotacaoRepository extends JpaRepository<Anotacao, Long> {
}
