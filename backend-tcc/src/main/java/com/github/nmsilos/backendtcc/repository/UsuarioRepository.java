package com.github.nmsilos.backendtcc.repository;

import com.github.nmsilos.backendtcc.model.Admin;
import com.github.nmsilos.backendtcc.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public Usuario findByUsername(String username);
    public Usuario findByEmail(String email);
    public Usuario findByImagem(String imagem);
}
