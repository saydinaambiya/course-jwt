package com.enigmacamp.jwt.util;

import com.enigmacamp.jwt.exception.UnathorizedException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    @Value("${token.jwt-secret}")
    private String secret;

    @Value("${token.jwt-expiration}")
    private Integer jwtExpiration;

    public String generateToken(String subject){
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, secret)
                .setExpiration(new Date(System.currentTimeMillis()+jwtExpiration))
                .compact();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            throw new UnathorizedException("Token is expired");
        } catch (UnsupportedJwtException e) {
            System.out.println(e.getMessage());
            throw new UnathorizedException("Token is unsupported");
        } catch (MalformedJwtException e) {
            throw new UnathorizedException("Token is malformed");
        } catch (SignatureException e) {
            throw new UnathorizedException("Signature is unknown");
        } catch (IllegalArgumentException e) {
            throw new UnathorizedException("Token is invalid");
        }
    }
}
