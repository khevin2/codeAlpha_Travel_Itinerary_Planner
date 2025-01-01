package io.kheven.Main.Services;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class JwtUtil {
    
    private static final Dotenv dotenv = Dotenv.load();
    
    private static final Key key = Keys.hmacShaKeyFor(dotenv.get("JWT_SECRET").getBytes());
    
    private final long JWT_VALIDITY = 5 * 60 * 60;

    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_VALIDITY * 1000);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key)
                .compact();
    }

    public String validateTokenAndGetUsername(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();

        } catch (JwtException e) {
            return null;

        }
    }
}
