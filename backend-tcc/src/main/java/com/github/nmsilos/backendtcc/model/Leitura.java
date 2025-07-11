package com.github.nmsilos.backendtcc.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "leituras")
public class Leitura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date data_inicio;

    private Date data_termino;

    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 1")
    private int pagina_atual;

    @Column(nullable = false)
    private boolean abandonado;

    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Admin usuario;

    @OneToOne(mappedBy = "leitura",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private Comentario comentario;

    public Leitura() {}

    /*
    public Leitura(Long id, Date data_inicio, Date data_termino, int pagina_atual, boolean abandonado, Livro livro, Usuario usuario, Comentario comentario) {
        this.id = id;
        this.data_inicio = data_inicio;
        this.data_termino = data_termino;
        this.pagina_atual = pagina_atual;
        this.abandonado = abandonado;
        this.livro = livro;
        this.usuario = usuario;
        this.comentario = comentario;
    }
     */

    public Leitura(Date data_inicio, Date data_termino, int pagina_atual, boolean abandonado) {
        this.data_inicio = data_inicio;
        this.data_termino = data_termino;
        this.pagina_atual = pagina_atual;
        this.abandonado = abandonado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }

    public Date getData_termino() {
        return data_termino;
    }

    public void setData_termino(Date data_termino) {
        this.data_termino = data_termino;
    }

    public int getPagina_atual() {
        return pagina_atual;
    }

    public void setPagina_atual(int pagina_atual) {
        this.pagina_atual = pagina_atual;
    }

    public boolean isAbandonado() {
        return abandonado;
    }

    public void setAbandonado(boolean abandonado) {
        this.abandonado = abandonado;
    }

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Admin getUsuario() {
        return usuario;
    }

    public void setUsuario(Admin usuario) {
        this.usuario = usuario;
    }
}
