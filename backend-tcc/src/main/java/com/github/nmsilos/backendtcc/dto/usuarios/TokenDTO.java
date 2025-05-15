package com.github.nmsilos.backendtcc.dto.usuarios;

public class TokenDTO {

    String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TokenDTO(String token) {
        this.token = token;
    }

}
