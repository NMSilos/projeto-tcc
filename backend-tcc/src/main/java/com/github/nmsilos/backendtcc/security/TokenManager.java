package com.github.nmsilos.backendtcc.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.github.nmsilos.backendtcc.model.Admin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TokenManager {
    @Value("MinhaSecretKey")
    public String secretKey;

    public String generateToken(Admin usuario) {
        try{
            var algorithm = Algorithm.HMAC256("1231233123");
            return JWT.create()
                    .withIssuer("Minha API")
                    .withSubject(usuario.getUsername())
                    .withClaim("nome", usuario.getNome())
                    .withClaim("role", usuario.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList().get(0))
                    .withExpiresAt(dataExpiracao())
                    .sign(algorithm);
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Aqui");
        }
        return null;
    }

    public String getSubject(String token) {
        try{
            var algorithm = Algorithm.HMAC256("1231233123");
            return JWT.require(algorithm)
                    .withIssuer("Minha API")
                    .build()
                    .verify(token).getSubject();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusMinutes(20)
                .toInstant(ZoneOffset.of("-03:00"));
    }


}

