package com.openclassrooms.escalade.security;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JwtUtils {

    @Value("${escalade.app.jwtSecret}")
    private String jwtSecret;

    @Value("${escalade.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUsernameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error("JWT signature invalide : {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("JWT token invalide : {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token expiré : {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token non supporté : {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("Le JWT attendu est vide : {}", e.getMessage());
        }
        return false;
    }
}
