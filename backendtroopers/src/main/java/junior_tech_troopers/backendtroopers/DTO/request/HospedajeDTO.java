package junior_tech_troopers.backendtroopers.DTO.request;

import lombok.*;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HospedajeDTO {
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
    private List<HospedajeImagenesDTO> images;
}
