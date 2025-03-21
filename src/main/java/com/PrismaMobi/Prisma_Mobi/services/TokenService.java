package com.PrismaMobi.Prisma_Mobi.services;

import com.PrismaMobi.Prisma_Mobi.entities.Users;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${alg.password}")
    private String secret;

    public String createToken(Users users){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withExpiresAt(expiration())
                    .withIssuer("Prisma-mobi-api")
                    .withSubject(users.getLogin())
                    .withClaim("id", users.getId())
                    .sign(algorithm);
        }catch (JWTCreationException exception){
            throw new RuntimeException("Error! JWT Token are not created");
        }
    }

    public String getSubject (String tokenJWT){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("Prisma-mobi-api")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (
                JWTVerificationException exception) {
            throw new RuntimeException("Invalid or expired JWT Token");
        }
    }

    private Instant expiration() {
        return LocalDateTime.now().plusHours(2L).toInstant(ZoneOffset.of("-03:00"));
    }
}
