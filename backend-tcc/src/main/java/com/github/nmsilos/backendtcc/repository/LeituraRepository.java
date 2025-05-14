package com.github.nmsilos.backendtcc.repository;

import com.github.nmsilos.backendtcc.model.Leitura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeituraRepository extends JpaRepository<Leitura, Long> {
}
