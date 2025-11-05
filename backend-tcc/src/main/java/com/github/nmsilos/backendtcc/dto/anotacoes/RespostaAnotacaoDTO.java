package com.github.nmsilos.backendtcc.dto.anotacoes;

import jakarta.persistence.Column;

import java.util.Date;

public class RespostaAnotacaoDTO {

    private Long id;
    private String titulo;

    @Column(length = 3000)
    private String descricao;

    private int capitulo;
    private int pagina;
    private Date data;

    public RespostaAnotacaoDTO(Long id, String titulo, String descricao, int capitulo, int pagina, Date data) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.capitulo = capitulo;
        this.pagina = pagina;
        this.data = data;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCapitulo() {
        return capitulo;
    }

    public void setCapitulo(int capitulo) {
        this.capitulo = capitulo;
    }

    public int getPagina() {
        return pagina;
    }

    public void setPagina(int pagina) {
        this.pagina = pagina;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
