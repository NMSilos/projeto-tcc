package com.github.nmsilos.backendtcc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Usuario extends Admin {

    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    private List<Leitura> leituras = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    private List<Sugestao> sugestoes = new ArrayList<>();

    @Column(nullable = true)
    private Date ultima_leitura;

    public Usuario() {}

    public Usuario(String nome, String apelido, String email, String senha, String imagem, String contentType) {
        super(nome, apelido, email, senha);
        this.imagem = imagem;
        this.contentType = contentType;
    }

    public Usuario(Long id, String nome, String apelido, String email, String senha) {
        super(id, nome, apelido, email, senha);
    }

    public List<Leitura> getLeituras() {
        return leituras;
    }

    public void setLeituras(List<Leitura> leituras) {
        this.leituras = leituras;
    }

    public List<Sugestao> getSugestoes() {
        return sugestoes;
    }

    public void setSugestoes(List<Sugestao> sugestoes) {
        this.sugestoes = sugestoes;
    }

    public Date getUltima_leitura() {
        return ultima_leitura;
    }

    public void setUltima_leitura(Date ultima_leitura) {
        this.ultima_leitura = ultima_leitura;
    }

}
