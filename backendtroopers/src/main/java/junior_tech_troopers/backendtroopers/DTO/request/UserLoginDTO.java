
package junior_tech_troopers.backendtroopers.DTO.request;
import lombok.*;
@Getter
@Setter

@AllArgsConstructor
public class UserLoginDTO {
    private String username;
    private String password;
}
