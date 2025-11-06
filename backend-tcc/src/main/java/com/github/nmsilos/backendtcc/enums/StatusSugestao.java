package com.github.nmsilos.backendtcc.enums;

public enum StatusSugestao {
    ACEITA("aceita"),
    PENDENTE("pendente");

    private final String valorUrl;

    StatusSugestao(String valorUrl) {
        this.valorUrl = valorUrl;
    }

    public String getValorUrl() {
        return valorUrl;
    }

}
