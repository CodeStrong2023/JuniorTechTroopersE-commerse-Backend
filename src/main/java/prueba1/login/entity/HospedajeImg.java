package prueba1.login.entity;

import lombok.*;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("public.hospedajes_img")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class HospedajeImg {
    @Column("id")
    private Integer idHospedajeImg;
    @Column("img_token")
    private String imgToken;
    @Column("hospedaje_token")
    private String hopspedajeToken;
    @Column("img_url")
    private String imgUrl;
    @Column("created_at")
    private String createdAt;
    @Column("active")
    private Boolean active;
}
