package junior_tech_troopers.backendtroopers.DTO.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DestinoSeleccionadoDTO {
    private String hospedajeToken;
    private String name;
    private Integer capacity;
    private String description;
    private Double price;
    private String locality;
    private Boolean wifi;
    private Boolean tv;
    private Boolean garage;
    private Boolean airConditioning;
    private Boolean heating;
    private Boolean pool;
    private String urlImg;
}
