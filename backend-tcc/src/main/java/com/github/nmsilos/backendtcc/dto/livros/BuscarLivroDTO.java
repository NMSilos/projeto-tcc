package com.github.nmsilos.backendtcc.dto.livros;

public class BuscarLivroDTO {

    private Long id;
    private String titulo;
    private String autor;
    private String imagem;
    private double avaliacao;

    public BuscarLivroDTO(Long id, String titulo, String autor, String imagem, double avaliacao) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.imagem = imagem;
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

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }
}
