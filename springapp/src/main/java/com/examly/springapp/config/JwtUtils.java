package com.examly.springapp.config;
 
import java.util.Date;
 
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
 
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
@Component
public class JwtUtils {
    private String secretKey="Java";
    public String generateToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return Jwts.builder()
        .setSubject(userDetails.getUsername())
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 30*60*1000))
        .signWith(SignatureAlgorithm.HS256, secretKey)
        .compact();
    }
    public String extractUserName(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }
    public Date extractExpiration(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getExpiration();
    }
    public boolean isTokenExpire(String token){
        Date expireDate =  extractExpiration(token);
        return expireDate.before(new Date());
    }
    public String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer ")){
            return header.substring(7);
        }
        return null;
    }
    public boolean validateToken(String token) {
        return !isTokenExpire(token);
    }
}