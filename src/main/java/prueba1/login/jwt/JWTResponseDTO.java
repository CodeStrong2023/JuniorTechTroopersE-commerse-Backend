package prueba1.login.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JWTResponseDTO {
    private String token;
    private String error;
}
