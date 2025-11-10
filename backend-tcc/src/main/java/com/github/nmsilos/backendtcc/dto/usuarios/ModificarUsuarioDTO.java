package com.github.nmsilos.backendtcc.dto.usuarios;


public class ModificarUsuarioDTO {

    private Long id;
    private String nome;
    private String username;
    private String password;
    private String nomeImage;
    private String typeImage;

    public ModificarUsuarioDTO(String nome, String username, String password, String nomeImage, String typeImage) {
        this.nome = nome;
        this.username = username;
        this.password = password;
        this.nomeImage = nomeImage;
        this.typeImage = typeImage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNomeImage() {
        return nomeImage;
    }

    public void setNomeImage(String nomeImage) {
        this.nomeImage = nomeImage;
    }

    public String getTypeImage() {
        return typeImage;
    }

    public void setTypeImage(String typeImage) {
        this.typeImage = typeImage;
    }
}
