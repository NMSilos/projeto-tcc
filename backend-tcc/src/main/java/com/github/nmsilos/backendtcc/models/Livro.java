package com.github.nmsilos.backendtcc.models;

import jakarta.persistence.*;

import java.time.Year;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String autor;

    @Column(nullable = false)
    private String isbn;

    @Column(nullable = false)
    private String editora;

    @Column(nullable = false)
    private Year ano_publicacao;

    @Column(nullable = false)
    private int paginas;

    private String descricao;

    private double avaliacao;

    public Livro() {}

    public Livro(Long id, String titulo, String autor, String isbn, String editora, Year ano_publicacao, int paginas, String descricao, double avaliacao) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.editora = editora;
        this.ano_publicacao = ano_publicacao;
        this.paginas = paginas;
        this.descricao = descricao;
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public Year getAno_publicacao() {
        return ano_publicacao;
    }

    public void setAno_publicacao(Year ano_publicacao) {
        this.ano_publicacao = ano_publicacao;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

}
