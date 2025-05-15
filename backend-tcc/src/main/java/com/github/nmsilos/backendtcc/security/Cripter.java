package com.github.nmsilos.backendtcc.security;

public class Cripter {
    public static String criptografar(String texto){
        BCryptPasswordEncoder encoder =
                new BCryptPasswordEncoder(12);
        return encoder.encode(texto);
    }
}
