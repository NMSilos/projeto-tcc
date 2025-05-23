package com.github.nmsilos.backendtcc.dto.comentarios;

public class RespostaComentarioDTO {

    private Long id;
    private String texto;
    private int nota;

    public RespostaComentarioDTO(Long id, String texto, int nota) {
        this.id = id;
        this.texto = texto;
        this.nota = nota;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

}
