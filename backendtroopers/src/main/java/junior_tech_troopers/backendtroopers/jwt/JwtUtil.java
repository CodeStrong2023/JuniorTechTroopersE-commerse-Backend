package junior_tech_troopers.backendtroopers.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.expiration}")
    private long EXPIRATION_TIME;

    // CREAR JWT:
    public JWTResponseDTO generateToken(String userToken, String username, String rol) {
        Map<String, Object> objJWT = new HashMap<>();
        objJWT.put("userToken", userToken);
        objJWT.put("username", username);
        objJWT.put("rol", rol);

        JWTResponseDTO jwtToken = new JWTResponseDTO(
                Jwts.builder()
                        .setClaims(objJWT)
                        .setIssuedAt(new Date(System.currentTimeMillis()))
                        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME * 60000))
                        .signWith(getSigningKey(), SignatureAlgorithm.HS256) // Cambiado aquí
                        .compact(),
                null
        );

        return jwtToken;
    }

    // LEER JWT:
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token.replace("Bearer ", ""))
                .getBody();
    }

    public JwtDTO getJwtObject(String token) {

        if (isTokenExpired(token)) {
            JwtDTO jwtDTO = new JwtDTO();
            jwtDTO.setError("TOKEN EXPIRADO");
            return jwtDTO;
        }

        Claims claims = extractAllClaims(token);

        if (claims == null) {
            JwtDTO jwtDTO = new JwtDTO();
            jwtDTO.setError("TOKEN ADULTERADO");
            return jwtDTO;
        }

        return new JwtDTO(
                claims.get("userToken", String.class),
                claims.get("username", String.class),
                claims.get("rol", String.class),
                null
        );
    }
}
