package com.github.nmsilos.backendtcc.exception.custom;

public class LoginInvalidoException extends RuntimeException {
    public LoginInvalidoException(String mensagem) {
        super(mensagem);
    }
}
