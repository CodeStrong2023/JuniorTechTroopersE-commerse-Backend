package junior_tech_troopers.backendtroopers.jwt;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtDTO {
    private String userToken;
    private String username;
    private String rol;
    private String error;
}
