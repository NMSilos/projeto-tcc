package com.github.nmsilos.backendtcc.enums;

public enum StatusLeitura {
    LIDO("lidos"),
    LENDO("lendo"),
    ABANDONADO("abandonados"),
    PRETENDO_LER("pretendo-ler");

    private final String valorUrl;

    StatusLeitura(String valorUrl) {
        this.valorUrl = valorUrl;
    }

    public String getValorUrl() {
        return valorUrl;
    }

    public static StatusLeitura fromUrl(String value) {
        for (StatusLeitura status : values()) {
            if (status.valorUrl.equalsIgnoreCase(value) ||
                    status.name().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Status inválido: " + value);
    }

}
