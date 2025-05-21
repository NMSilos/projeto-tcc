package com.github.nmsilos.backendtcc.dto.usuarios;

public class TokenDTO {

    Long id;

    String token;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TokenDTO(Long id, String token) {
        this.id = id;
        this.token = token;
    }

}
