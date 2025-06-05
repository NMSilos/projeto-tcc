package com.github.nmsilos.backendtcc.dto.livros;

public class BuscarLivroDTO {

    private String titulo;
    private String autor;
    private double avaliacao;

    public BuscarLivroDTO(String titulo, String autor, double avaliacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.avaliacao = avaliacao;
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
