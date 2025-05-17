package com.github.nmsilos.backendtcc.dto.usuarios;

import com.github.nmsilos.backendtcc.model.Comentario;
import com.github.nmsilos.backendtcc.model.Leitura;

import java.util.List;

public class RespostaUsuarioDTO {

    private String nome;
    private String username;
    private String email;
    private List<Comentario> comentarios;
    private List<Leitura> leituras;

    public RespostaUsuarioDTO(String nome, String username, String email) {
        this.nome = nome;
        this.username = username;
        this.email = email;
    }

    public RespostaUsuarioDTO(String nome, String username, String email, List<Comentario> comentarios, List<Leitura> leituras) {
        this.nome = nome;
        this.username = username;
        this.email = email;
        this.comentarios = comentarios;
        this.leituras = leituras;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public List<Leitura> getLeituras() {
        return leituras;
    }

    public void setLeituras(List<Leitura> leituras) {
        this.leituras = leituras;
    }

}
