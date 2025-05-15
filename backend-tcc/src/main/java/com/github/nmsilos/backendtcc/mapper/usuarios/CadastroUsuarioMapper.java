package com.github.nmsilos.backendtcc.mapper.usuarios;

import com.github.nmsilos.backendtcc.model.Usuario;
import com.github.nmsilos.backendtcc.security.Cripter;

public class CadastroUsuarioMapper {
    public static Usuario toModel(Usuario usuario) {
        String senhaCriptografada = Cripter.criptografar(usuario.getSenha());
        return new Usuario(usuario.getNome(), usuario.getApelido(), usuario.getEmail(), senhaCriptografada);
    }
}
