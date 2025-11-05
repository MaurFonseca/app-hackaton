package com.projeto.app_hackaton.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.projeto.app_hackaton.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String tokenGeneration(Usuario usuario){
        Algorithm algorithm = Algorithm.HMAC256(secret);
        try {
            String token = JWT.create()
                    .withIssuer("app-hackaton")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(generateExpDate())
                    .sign(algorithm);
            return token;
        }catch (JWTCreationException e){
            throw new RuntimeException("Erro na geração de token");
        }
    }

    public String validateToken (String token){
        Algorithm algorithm = Algorithm.HMAC256(secret);
        try {
            String validToken =
                    JWT.require(algorithm)
                            .withIssuer("app-hackaton")
                            .build()
                            .verify(token)
                            .getSubject();
            return validToken;
        }catch (JWTVerificationException e){
            return null;
        }
    }

    private Instant generateExpDate(){
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-3"));
    }
}
