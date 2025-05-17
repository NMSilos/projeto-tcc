package com.github.nmsilos.backendtcc.exception.custom;

public class ErroServidorException extends RuntimeException {
    public ErroServidorException(String mensagem) {
        super(mensagem);
    }
}
