package com.github.nmsilos.backendtcc.dto.sugestoes;

import com.github.nmsilos.backendtcc.enums.StatusSugestao;
import jakarta.persistence.*;

public class RespostaSugestaoDTO {

    private Long id;
    private String titulo;
    private String autor;
    private String editora;

    @Column(length = 1000)
    private String descricao;

    private StatusSugestao status;

    public RespostaSugestaoDTO(Long id, String titulo, String autor, String editora, String descricao, StatusSugestao status) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.descricao = descricao;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusSugestao getStatus() {
        return status;
    }

    public void setStatus(StatusSugestao status) {
        this.status = status;
    }
}
