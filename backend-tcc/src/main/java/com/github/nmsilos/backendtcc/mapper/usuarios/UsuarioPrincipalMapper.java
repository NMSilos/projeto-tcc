package com.github.nmsilos.backendtcc.mapper.usuarios;

import com.github.nmsilos.backendtcc.dto.usuarios.UsuarioPrincipalDTO;
import com.github.nmsilos.backendtcc.model.Usuario;

public class UsuarioPrincipalMapper {
    public static UsuarioPrincipalDTO toDto(Usuario usuario) {
        return new UsuarioPrincipalDTO(usuario.getId(), usuario.getUsername(), usuario.getImagem());
    }
}
