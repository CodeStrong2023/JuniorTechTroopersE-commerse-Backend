package prueba1.login.DTO.response;

import lombok.*;
import prueba1.login.entity.HospedajeImg;

import java.util.List;

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
