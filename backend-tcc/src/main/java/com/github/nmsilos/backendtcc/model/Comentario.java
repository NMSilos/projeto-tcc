package com.github.nmsilos.backendtcc.model;

import jakarta.persistence.*;

@Entity
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String texto;

    private int nota;

    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;

    @OneToOne
    @JoinColumn(name = "leitura_id")
    private Leitura leitura;

    public Comentario() {}

    public Comentario(Long id, String texto, int nota, Livro livro, Leitura leitura) {
        this.id = id;
        this.texto = texto;
        this.nota = nota;
        this.livro = livro;
        this.leitura = leitura;
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

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Leitura getLeitura() {
        return leitura;
    }

    public void setLeitura(Leitura leitura) {
        this.leitura = leitura;
    }
}
