package com.github.nmsilos.backendtcc.dto.usuarios;

public class RespostaUsuarioDTO {

    private String nome;
    private String username;
    private String email;

    public RespostaUsuarioDTO(String nome, String username, String email) {
        this.nome = nome;
        this.username = username;
        this.email = email;
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
}
