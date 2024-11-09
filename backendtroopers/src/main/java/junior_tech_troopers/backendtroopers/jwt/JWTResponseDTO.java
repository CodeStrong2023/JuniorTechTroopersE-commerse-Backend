package junior_tech_troopers.backendtroopers.jwt;

import lombok.*;


@Getter
@AllArgsConstructor
public class JWTResponseDTO {
    private String token;
    private String error;
}
