package com.github.nmsilos.backendtcc.mapper.usuarios;

import com.github.nmsilos.backendtcc.dto.usuarios.CadastroUsuarioDTO;
import com.github.nmsilos.backendtcc.dto.usuarios.ModificarUsuarioDTO;
import com.github.nmsilos.backendtcc.model.Usuario;
import com.github.nmsilos.backendtcc.security.Cripter;

public class CadastroUsuarioMapper {
    public static Usuario toModel(CadastroUsuarioDTO usuario) {
        String senhaCriptografada = Cripter.criptografar(usuario.getPassword());
        return new Usuario(usuario.getNome(), usuario.getUsername(), usuario.getEmail(), senhaCriptografada);
    }

    public static Usuario toModel(ModificarUsuarioDTO usuario) {
        return new Usuario(usuario.getId(), usuario.getNome(), usuario.getUsername(), usuario.getEmail());
    }
}
