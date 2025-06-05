package com.github.nmsilos.backendtcc.dto.livros;

public class BuscarLivroDTO {

    private Long id;
    private String titulo;
    private String autor;
    private double avaliacao;

    public BuscarLivroDTO(Long id, String titulo, String autor, double avaliacao) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
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

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }
}
