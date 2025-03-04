package com.homeify.apigateway.APIGateway.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Service
public class JWTService {

    private final String secretKey = "this-is-a-very-longgggg-secret-key";
    private String key = "";
    private final String keyTest = "3H3umgLEmBwNmH3udkM3+l0xkcgahL8oI6cT4ayHe0I=";

    public JWTService() {
        try {
            key = Base64.getEncoder().encodeToString(secretKey.getBytes());
            System.out.println("Secret key: " + key);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private SecretKey getKey() {

        byte[] keyBytes = Decoders.BASE64.decode(keyTest);

        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
        // extract username from token
        return extractClaim(token, Claims::getSubject);
    }

    public List<String> extractRoles(String token) {
        return extractClaim(token, claims -> claims.get("roles", List.class));
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {

        return Jwts.parser()
                .verifyWith(getKey()) //này là phần quan trọng nhất, nó sẽ verify token với secret key
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token) {
        return (!isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
