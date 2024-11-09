package junior_tech_troopers.backendtroopers.entity;

import lombok.*;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table("public.tickets")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @Column("id")
    private Integer idTickets;
    @Column("ticket_token")
    private String ticketToken;
    @Column("user_token_owner")
    private String userTokenOwner;
    @Column("user_token_renter")
    private String userTokenRenter;
    @Column("hospedaje_token")
    private String hospedajeToken;
    @Column("start_date")
    private Date startDate;
    @Column("end_date")
    private Date endDate;
    @Column("total_amount")
    private Double totalAmount;
    @Column("created_at")
    private Date createdAt;
    @Column("active")
    private Boolean active;

    @Column("hospedaje_nombre")
    private String hospedajeNombre;
    @Column("user_username")
    private String username;
    @Column("user_email")
    private String userEmail;
    @Column("user_phone")
    private String userPhone;
}
