package com.github.nmsilos.backendtcc.dto.usuarios;

public class GoogleLoginRequest {

    private String credential;

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    @Override
    public String toString() {
        return "GoogleLoginRequest{credential='" + credential + "'}";
    }

}
