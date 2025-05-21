package com.github.nmsilos.backendtcc.dto.usuarios;

import com.github.nmsilos.backendtcc.dto.leituras.RespostaLeituraDTO;
import com.github.nmsilos.backendtcc.model.Comentario;

import java.util.List;

public class RespostaUsuarioDTO {

    private String nome;
    private String username;
    private String email;
    private List<RespostaLeituraDTO> leituras;

    public RespostaUsuarioDTO(String nome, String username, String email) {
        this.nome = nome;
        this.username = username;
        this.email = email;
    }

    public RespostaUsuarioDTO(String nome, String username, String email, List<RespostaLeituraDTO> leituras) {
        this.nome = nome;
        this.username = username;
        this.email = email;
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

    public List<RespostaLeituraDTO> getLeituras() {
        return leituras;
    }

    public void setLeituras(List<RespostaLeituraDTO> leituras) {
        this.leituras = leituras;
    }

}
