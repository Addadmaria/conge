package dz.airalgerie.conge.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;   // <-- add this
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;                                      // <-- add this
import java.util.Map;                                         // <-- add this
import java.util.function.Function;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    // expiration in milliseconds (e.g. 86400000 for 1 day)
    @Value("${jwt.expiration}")
    private long jwtExpirationMs;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                   .setSigningKey(secretKey)          // use your field!
                   .parseClaimsJws(token)
                   .getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // --- Token creation ---
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                   .setClaims(claims)
                   .setSubject(subject)
                   .setIssuedAt(new Date(now))
                   .setExpiration(new Date(now + jwtExpirationMs))  // use your property
                   .signWith(SignatureAlgorithm.HS256, secretKey)   // use your field!
                   .compact();
    }

    // --- Token validation ---
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername())
            && !isTokenExpired(token);
    }
}
