package junior_tech_troopers.backendtroopers.DTO.response;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class TicketResponseDTO {
    private String nombreHospedaje;
    private String userNameRenter;
    private String emailRenter;
    private String phoneRenter;
    private Date startDate;
    private Date endDate;
    private Double totalAmount;
}
