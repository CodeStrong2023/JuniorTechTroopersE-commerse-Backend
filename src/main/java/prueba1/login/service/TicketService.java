package prueba1.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prueba1.login.DTO.request.TicketRequestDTO;
import prueba1.login.entity.Hospedaje;
import prueba1.login.repository.HospedajeRepository;
import prueba1.login.repository.TicketRepository;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private HospedajeRepository hospedajeRepository;

    @Transactional
    public void insertarTicket(TicketRequestDTO ticketRequestDTO, String userToken){
        Hospedaje hospedajeParaTicket = hospedajeRepository.obtenerInformacionParaTicket(ticketRequestDTO.getHospedajeToken());

        // Calcular la diferencia absoluta en milisegundos
        long diffInMillies = Math.abs(ticketRequestDTO.getEndDate().getTime() - ticketRequestDTO.getStartDate().getTime());

        // Convertir la diferencia de milisegundos a días
        long diasHospedados = diffInMillies / (1000 * 60 * 60 * 24);

        double totalPago = diasHospedados * hospedajeParaTicket.getPrice();

        ticketRepository.crearTicket(
                hospedajeParaTicket.getUserToken(),
                userToken,
                hospedajeParaTicket.getHospedajeToken(),
                ticketRequestDTO.getStartDate(),
                ticketRequestDTO.getEndDate(),
                totalPago
        );
    }
}
