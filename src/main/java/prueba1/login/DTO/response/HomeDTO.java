package prueba1.login.DTO.response;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class HomeDTO {
    private String nombreHospedaje;
    private String description;
    private String img_url;
    private String locality;
}
