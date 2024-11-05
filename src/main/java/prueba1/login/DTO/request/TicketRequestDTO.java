package prueba1.login.DTO.request;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class TicketRequestDTO {
    private String hospedajeToken;
    private Date startDate;
    private Date endDate;
}
