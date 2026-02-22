package com.Hanium.Farm.Farm.Support.Fixture;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;
import java.util.Date;

public class TokenFixture {
    private Key key;
    private Date now;

    public TokenFixture(Key key) {
        this.key = key;
        this.now = new Date();
    }

    public String createValidToken(String userId) {
        Claims claims = Jwts.claims()
                        .setIssuedAt(new Date(now.getTime() - 10000))
                        .setExpiration(new Date(now.getTime() + 100000))
                        .setSubject("farm");

        return Jwts.builder()
                .setClaims(claims)
                .claim("userId", "yk")
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String createExpiredToken(String userId) {
        Claims claims = Jwts.claims()
                .setSubject("farm")
                .setIssuedAt(new Date(now.getTime() - 1000))
                .setExpiration(new Date(now.getTime() - 100000));

        return Jwts.builder()
                .setClaims(claims)
                .claim("userId", "yk")
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
