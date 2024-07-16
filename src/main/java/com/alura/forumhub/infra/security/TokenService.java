package com.alura.forumhub.infra.security;

import com.alura.forumhub.domain.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("forumhub") // application identification | sender
                    .withSubject(user.getLogin()) // saves the user login in the Token, for identification in the request
                    .withExpiresAt(genExpirationData()) // expiration time
                    .sign(algorithm); // make the signature and final generation
            return token;
        } catch (JWTCreationException exception) {
throw new RuntimeException("Error when trying to generate the token ", exception);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return  JWT.require(algorithm)
                    .withIssuer("forumhub")
                    .build()
                    .verify(token)
                    .getSubject(); // retrieves the user decrypted in the token
        } catch (JWTVerificationException exception) {
throw new RuntimeException("Token has expired or is invalid!", exception);
        }
    }

    private Instant genExpirationData() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
