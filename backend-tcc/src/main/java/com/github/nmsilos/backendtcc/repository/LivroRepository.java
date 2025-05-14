package com.github.nmsilos.backendtcc.repository;

import com.github.nmsilos.backendtcc.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
}
