package com.github.nmsilos.backendtcc.model;

import com.github.nmsilos.backendtcc.enums.StatusLeitura;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusLeitura status;

    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Admin usuario;

    @OneToOne(mappedBy = "leitura",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true
    )
    private Comentario comentario;

    @OneToMany(mappedBy = "leitura", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Anotacao> anotacoes = new ArrayList<>();

    public Leitura() {}

    public Leitura(Date data_inicio, Date data_termino, int pagina_atual, StatusLeitura status) {
        this.data_inicio = data_inicio;
        this.data_termino = data_termino;
        this.pagina_atual = pagina_atual;
        this.status = status;
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

    public StatusLeitura getStatus() {
        return status;
    }

    public void setStatus(StatusLeitura status) {
        this.status = status;
    }

    public int getPagina_atual() {
        return pagina_atual;
    }

    public void setPagina_atual(int pagina_atual) {
        this.pagina_atual = pagina_atual;
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

    public List<Anotacao> getAnotacoes() {
        return anotacoes;
    }

}
