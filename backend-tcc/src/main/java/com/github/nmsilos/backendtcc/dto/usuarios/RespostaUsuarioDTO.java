package com.github.nmsilos.backendtcc.dto.usuarios;

import com.github.nmsilos.backendtcc.dto.leituras.RespostaLeituraDTO;
import com.github.nmsilos.backendtcc.model.Comentario;

import java.util.List;

public class RespostaUsuarioDTO {

    private String nome;
    private String username;
    private String email;
    private List<RespostaLeituraDTO> leituras;
    private int streaks;

    public RespostaUsuarioDTO(String nome, String username, String email, int streaks) {
        this.nome = nome;
        this.username = username;
        this.email = email;
        this.streaks = streaks;
    }

    public RespostaUsuarioDTO(String nome, String username, String email, List<RespostaLeituraDTO> leituras, int streaks) {
        this.nome = nome;
        this.username = username;
        this.email = email;
        this.leituras = leituras;
        this.streaks = streaks;
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

    public int getStreaks() {
        return streaks;
    }

    public void setStreaks(int streaks) {
        this.streaks = streaks;
    }
}
