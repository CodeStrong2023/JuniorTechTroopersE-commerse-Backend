package junior_tech_troopers.backendtroopers.DTO.response;

import lombok.*;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class HomeDTO {

    private String hospedajeToken;

    private String nombreHospedaje;
    private String description;
    private String img_url;
    private String locality;
}
