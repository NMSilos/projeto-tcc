package com.github.nmsilos.backendtcc.repository;

import com.github.nmsilos.backendtcc.models.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}
