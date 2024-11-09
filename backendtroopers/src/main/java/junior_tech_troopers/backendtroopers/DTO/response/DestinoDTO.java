package junior_tech_troopers.backendtroopers.DTO.response;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DestinoDTO {
    private String hospedajeToken;
    private String nombreHospedaje;
    private String img_url;
    private Double price;
}
