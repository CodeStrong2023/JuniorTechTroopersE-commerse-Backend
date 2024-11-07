package junior_tech_troopers.backendtroopers.controller;

import junior_tech_troopers.backendtroopers.DTO.request.TicketRequestDTO;
import junior_tech_troopers.backendtroopers.DTO.response.TicketResponseDTO;
import junior_tech_troopers.backendtroopers.service.TicketService;
import junior_tech_troopers.backendtroopers.jwt.JwtDTO;
import junior_tech_troopers.backendtroopers.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
@CrossOrigin("https://trooper-stay.web.app")
public class TicketController {
    @Autowired
    private TicketService ticketService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public  void  crearTicket(@RequestHeader("Authorization") String jwt, @RequestBody TicketRequestDTO ticketRequestDTO){
        JwtDTO jwtDTO = jwtUtil.getJwtObject(jwt);

        if (jwtDTO.getError() != null) {
            return ;
        }
        ticketService.insertarTicket(ticketRequestDTO, jwtDTO.getUserToken());
    }
    @GetMapping("/user")
    public List<TicketResponseDTO> ticketVinculados(@RequestHeader("Authorization") String jwt){
        JwtDTO jwtDTO = jwtUtil.getJwtObject(jwt);

        if (jwtDTO.getError() != null) {
            return null;
        }
        return ticketService.mostrarTicketRegistrados(jwtDTO.getUserToken());
    }
    @GetMapping("/reservas")
    public List<TicketResponseDTO> reservasCreadas(@RequestHeader("Authorization") String jwt){
        JwtDTO jwtDTO = jwtUtil.getJwtObject(jwt);

        if (jwtDTO.getError() != null) {
            return null;
        }
        return ticketService.reservasRegistradas(jwtDTO.getUserToken());
    }
}
