package com.github.nmsilos.backendtcc.repository;

import com.github.nmsilos.backendtcc.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
