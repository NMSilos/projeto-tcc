package com.github.nmsilos.backendtcc.dto.usuarios;

public class UsuarioPrincipalDTO {

    private Long id;
    private String username;
    private String imagem;

    public UsuarioPrincipalDTO(Long id, String username, String imagem) {
        this.id = id;
        this.username = username;
        this.imagem = imagem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
