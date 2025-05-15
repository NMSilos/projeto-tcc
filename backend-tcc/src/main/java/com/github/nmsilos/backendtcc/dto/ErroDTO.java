package com.github.nmsilos.backendtcc.dto;

public class ErroDTO {

    String mensagem;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public ErroDTO(String mensagem) {
        this.mensagem = mensagem;
    }

}
