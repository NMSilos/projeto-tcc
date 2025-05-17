package com.github.nmsilos.backendtcc.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;

public class MensagemErroPadrao {

    private String path;
    private String method;
    private int status;
    private String statusMensagem;
    private String mensagem;

    public MensagemErroPadrao() {}

    public MensagemErroPadrao(HttpServletRequest request, HttpStatus status, String mensagem) {
        this.path = request.getRequestURI();
        this.method = request.getMethod();
        this.status = status.value();
        this.statusMensagem = status.getReasonPhrase();
        this.mensagem = mensagem;
    }

    public String getPath() {
        return path;
    }

    public String getMethod() {
        return method;
    }

    public int getStatus() {
        return status;
    }

    public String getStatusMensagem() {
        return statusMensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
