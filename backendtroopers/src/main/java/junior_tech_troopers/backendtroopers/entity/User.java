package junior_tech_troopers.backendtroopers.entity;

import lombok.*;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table("public.users")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Column("id")
    private Integer idUser;
    @Column("user_rol")
    private String user_rol;

    // Variable Obtenida por LEFT JOIN
    @Column("titulo_rol")
    private String rolTitle;
    // Variable Obtenida por LEFT JOIN

    @Column("user_token")
    private String userToken;
    @Column("username")
    private String userName;
    @Column("password")
    private String password;
    @Column("img_url")
    private String imgUrl;
    @Column("firstname")
    private String firstname;
    @Column("lastname")
    private String lastname;
    @Column("email")
    private String email;
    @Column("birthdate")
    private Date birthdate;
    @Column("phone")
    private String phone;
    @Column("locality")
    private String locality;
    @Column("valid_mail")
    private Boolean validMail;
    @Column("created_at")
    private Date createdAt;
    @Column("active")
    private boolean active;
}

