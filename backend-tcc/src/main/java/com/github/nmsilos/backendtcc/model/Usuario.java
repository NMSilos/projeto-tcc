package com.github.nmsilos.backendtcc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario extends Admin {

    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    private List<Leitura> leituras = new ArrayList<>();

    public Usuario() {}

    public Usuario(String nome, String apelido, String email, String senha) {
        super(nome, apelido, email, senha);
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

}
