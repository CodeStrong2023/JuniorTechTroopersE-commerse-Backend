package prueba1.login.DTO.response;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserPerfilDTO {
    private String imgUrl;
    private String firstname;
    private String lastname;
    private String email;
    private Date birthdate;
    private String phone;
    private String locality;
    private Boolean validMail;
    private Date createdAt;
    private String error;

    public UserPerfilDTO(String error) {
        this.error = error;
    }
}
