package com.bridgelabz.bookstore.security.jwt;

import com.bridgelabz.bookstore.service.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//JwtUtils provides methods for generating, parsing, validating JWT
@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${bezkoder.app.jwtSecret}")
    private String jwtSecret;

    @Value("${bezkoder.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        Map userDataMap = new  HashMap<>();
        userDataMap.put("userId", userPrincipal.getId());
        userDataMap.put("userName", userPrincipal.getUsername());
        return Jwts.builder()
                //.setSubject((userPrincipal.getUsername()))
        		.addClaims(userDataMap)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
    	
    	Claims claims =  Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    	return claims.get("userName", String.class);
    	
    }
    
    public int getUserIdFromJwtToken(String token) {
    	
    	Claims claims =  Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    	return claims.get("userId", Integer.class);
    	
    }
    
    public long getUserIdFromJwtTokenLong(String token) {
    	
    	Claims claims =  Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    	return claims.get("userId", Long.class);
    	
    }
    
    
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
