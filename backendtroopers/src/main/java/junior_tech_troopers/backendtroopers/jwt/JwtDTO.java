package prueba1.login.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
