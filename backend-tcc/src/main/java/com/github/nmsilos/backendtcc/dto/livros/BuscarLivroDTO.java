package com.github.nmsilos.backendtcc.dto.livros;

import java.time.Year;

public class BuscarLivroDTO {

    private Long id;
    private String titulo;
    private String autor;
    private String imagem;
    private Year ano_publicacao;
    private double avaliacao;

    public BuscarLivroDTO(Long id, String titulo, String autor, String imagem, Year ano_publicacao, double avaliacao) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.imagem = imagem;
        this.ano_publicacao = ano_publicacao;
        this.avaliacao = avaliacao;
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

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Year getAno_publicacao() {
        return ano_publicacao;
    }

    public void setAno_publicacao(Year ano_publicacao) {
        this.ano_publicacao = ano_publicacao;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }
}
