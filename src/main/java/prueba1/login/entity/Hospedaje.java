package prueba1.login.entity;

import lombok.*;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table("public.hospedajes")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Hospedaje {
    @Column("id")
    private Integer idHospedaje;
    @Column("hospedaje_token")
    private String hospedajeToken;
    @Column("user_token")
    private String userToken;
    @Column("capacity")
    private Integer capacity;
    @Column("description")
    private String description;
    @Column("price")
    private Double price;
    @Column("locality")
    private String locality;
    @Column("wifi")
    private Boolean wifi;
    @Column("tv")
    private Boolean tv;
    @Column("garage")
    private Boolean garage;
    @Column("air_conditioning")
    private Boolean airConditioning;
    @Column("heating")
    private Boolean heating;
    @Column("pool")
    private Boolean pool;
    @Column("created_ad")
    private Date createdAd;
    @Column("active")
    private Boolean active;
    @Column("nombre")
    private String nombreHospedaje;


    // HospedajeImg
    @Column("imgUrlHospedajeImg")
    private String imgUrlHospedajeImg;
    // HospedajeImg

}
