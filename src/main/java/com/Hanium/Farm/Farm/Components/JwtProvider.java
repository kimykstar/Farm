package com.Hanium.Farm.Farm.Components;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {
    private String privateKey;
    private long accessTokenExpireTime;
    private long refreshTokenExpireTime;
    private Key key;

    public JwtProvider(
            @Value("${spring.jwt.token.secret.key}") String privateKey,
            @Value("${spring.jwt.access-token.validation.time}") long accessTokenExpireTime,
            @Value("${spring.jwt.refresh-token.validation.time}") long refreshTokenExpireTime
    ) {
        this.privateKey = privateKey;
        this.accessTokenExpireTime = accessTokenExpireTime;
        this.refreshTokenExpireTime = refreshTokenExpireTime;

        this.key = Keys.hmacShaKeyFor(privateKey.getBytes());
    }

    public String createAccessToken(String id) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + accessTokenExpireTime);

        Claims claims = Jwts.claims()
                .setSubject("farm")
                .setIssuedAt(now)
                .setExpiration(validity);

        return Jwts.builder()
                .setClaims(claims)
                .claim("userId", id)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String createRefreshToken(String id) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + refreshTokenExpireTime);

        Claims claims = Jwts.claims()
                .setSubject(("farm"))
                .setIssuedAt(now)
                .setExpiration(validity);

        return Jwts.builder()
                .setClaims(claims)
                .claim("userId", id)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUserId(String token) {
        return parseClaims(token).get("userId").toString();
    }

    public boolean validateToken(String token) {
        parseClaims(token);
        return true;
    }
}
