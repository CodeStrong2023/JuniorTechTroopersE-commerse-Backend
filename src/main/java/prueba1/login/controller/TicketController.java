package prueba1.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import prueba1.login.DTO.request.TicketRequestDTO;
import prueba1.login.entity.Ticket;
import prueba1.login.jwt.JwtDTO;
import prueba1.login.jwt.JwtUtil;
import prueba1.login.service.TicketService;

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
//    @GetMapping
//    public List<TicketRequestDTO> ticketVinculados(@RequestHeader("Authorization") String jwt){
//        JwtDTO jwtDTO = jwtUtil.getJwtObject(jwt);
//
//        if (jwtDTO.getError() != null) {
//            return null;
//        }
//        return;
//    }
}
