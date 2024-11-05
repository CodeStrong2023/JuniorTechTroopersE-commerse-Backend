package prueba1.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prueba1.login.DTO.request.TicketRequestDTO;
import prueba1.login.DTO.response.TicketResponseDTO;
import prueba1.login.entity.Hospedaje;
import prueba1.login.entity.Ticket;
import prueba1.login.entity.User;
import prueba1.login.repository.HospedajeRepository;
import prueba1.login.repository.TicketRepository;
import prueba1.login.repository.UserRepository;
import prueba1.login.utils.Mail;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private HospedajeRepository hospedajeRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Mail mail;

    @Transactional
    public void insertarTicket(TicketRequestDTO ticketRequestDTO, String userToken){

        Hospedaje hospedajeParaTicket = hospedajeRepository.obtenerInformacionParaTicket(ticketRequestDTO.getHospedajeToken());
        if (hospedajeParaTicket.getUserToken() == userToken){
            return;
        }
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

        User userOwnerData = userRepository.obtenerPerfilUsuarioPorUserToken(hospedajeParaTicket.getUserToken());
        User userRenterData = userRepository.obtenerPerfilUsuarioPorUserToken(userToken);

        mail.sendEmail(
                userOwnerData.getEmail(),
                "Alquiler confimado",
                mail.mailTicket(
                        userRenterData.getFirstname(),
                        userRenterData.getLastname(),
                        userRenterData.getEmail(),
                        userRenterData.getPhone(),
                        hospedajeParaTicket.getNombreHospedaje(),
                        ticketRequestDTO.getStartDate(),
                        ticketRequestDTO.getEndDate(),
                        totalPago
                ));

        mail.sendEmail(
                userRenterData.getEmail(),
                "Alquiler realizado",
                mail.mailTicket(
                        userOwnerData.getFirstname(),
                        userOwnerData.getLastname(),
                        userOwnerData.getEmail(),
                        userOwnerData.getPhone(),
                        hospedajeParaTicket.getNombreHospedaje(),
                        ticketRequestDTO.getStartDate(),
                        ticketRequestDTO.getEndDate(),
                        totalPago
                )
        );



    }

    public List<TicketResponseDTO> mostrarTicketRegistrados(String userToken){
        List<Ticket> tickets = ticketRepository.ticketConfirmados(userToken);
        List<TicketResponseDTO> ticketResponseDTOS = new ArrayList<>();

        for (Ticket ticket: tickets) {
            TicketResponseDTO ticketResponseDTO = new TicketResponseDTO();
            ticketResponseDTO.setNombreHospedaje(ticket.getHospedajeNombre());
            ticketResponseDTO.setUserNameRenter(ticket.getUsername());
            ticketResponseDTO.setEmailRenter(ticket.getUserEmail());
            ticketResponseDTO.setPhoneRenter(ticket.getUserPhone());
            ticketResponseDTO.setStartDate(ticket.getStartDate());
            ticketResponseDTO.setEndDate(ticket.getEndDate());
            ticketResponseDTO.setTotalAmount(ticket.getTotalAmount());
            ticketResponseDTOS.add(ticketResponseDTO);
        }
        return ticketResponseDTOS;
    }

    public List<TicketResponseDTO> reservasRegistradas(String userToken){
        List<Ticket> tickets = ticketRepository.reservasRealizadas(userToken);
        List<TicketResponseDTO> ticketResponseDTOS = new ArrayList<>();
        for (Ticket ticket: tickets) {
            TicketResponseDTO ticketResponseDTO = new TicketResponseDTO();
            ticketResponseDTO.setNombreHospedaje(ticket.getHospedajeNombre());
            ticketResponseDTO.setUserNameRenter(ticket.getUsername());
            ticketResponseDTO.setEmailRenter(ticket.getUserEmail());
            ticketResponseDTO.setPhoneRenter(ticket.getUserPhone());
            ticketResponseDTO.setStartDate(ticket.getStartDate());
            ticketResponseDTO.setEndDate(ticket.getEndDate());
            ticketResponseDTO.setTotalAmount(ticket.getTotalAmount());
            ticketResponseDTOS.add(ticketResponseDTO);
        }
        return ticketResponseDTOS;
    }
}
