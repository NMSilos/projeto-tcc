package com.github.nmsilos.backendtcc.exception.custom;

public class UsuarioInvalidoException extends RuntimeException {
    public UsuarioInvalidoException(String mensagem) {
        super(mensagem);
    }
}
