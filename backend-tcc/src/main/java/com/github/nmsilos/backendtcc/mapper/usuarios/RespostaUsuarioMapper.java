package com.github.nmsilos.backendtcc.mapper.usuarios;

import com.github.nmsilos.backendtcc.dto.usuarios.RespostaUsuarioDTO;
import com.github.nmsilos.backendtcc.model.Usuario;
import com.github.nmsilos.backendtcc.security.Cripter;

public class RespostaUsuarioMapper {
    public static RespostaUsuarioDTO toDto(Usuario usuario) {
        return new RespostaUsuarioDTO(usuario.getNome(), usuario.getUsername(), usuario.getEmail());
    }
}
