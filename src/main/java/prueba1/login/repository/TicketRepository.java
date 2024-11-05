package prueba1.login.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import prueba1.login.entity.Hospedaje;
import prueba1.login.entity.Ticket;

import java.util.Date;
import java.util.List;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, String> {

    @Modifying
    @Query("INSERT INTO public.ticket (" +
            "ticket_token, " +
            "user_token_owner, " +
            "user_token_renter, " +
            "hospedaje_token, " +
            "start_date, " +
            "end_date, " +
            "total_amount) " +
            "VALUES (" +
            "gen_random_uuid(), " +
            ":userTokenOwner, " +
            ":userTokenRenter, " +
            ":hospedajeToken, " +
            ":startDate, " +
            ":endDate, " +
            ":totalAmount)"
    )
    void crearTicket (
            @Param("userTokenOwner") String userTokenOwner,
            @Param("userTokenRenter") String userTokenRenter,
            @Param("hospedajeToken") String hospedajeToken,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate,
            @Param("totalAmount") Double totalAmount
            );

    /**
     * Trae vista para los Ticket para ATENDER a nuestros clientes
     * */

    @Query("SELECT " +
            "h.nombre as hospedaje_nombre, " +
            "u.username as user_username, " +
            "u.email as user_email, " +
            "u.phone as user_phone, " +
            "t.start_date, " +
            "t.end_date, " +
            "t.total_amount, " +
            "t.created_at " +
            "FROM public.ticket t " +
            "LEFT JOIN public.users u ON u.user_token = t.user_token_renter " +
            "LEFT JOIN public.hospedajes h ON h.hospedaje_token = t.hospedaje_token " +
            "WHERE t.active = true " +
            "AND t.user_token_owner = :userTokenOwner")
    List<Ticket> ticketConfirmados(@Param("userTokenOwner") String userTokenOwner);


    /**
     * Trae vista para los hospedajes que queremos realizar como clientes
     * */
    @Query("SELECT " +
            "d.username as user_username, " +
            "d.email as user_email, " +
            "d.phone as user_phone, " +
            "h.nombre as hospedaje_nombre, " +
            "t.start_date, " +
            "t.end_date, " +
            "t.total_amount," +
            "t.created_at " +
            "FROM public.ticket t " +
            "LEFT JOIN public.users d ON d.user_token = t.user_token_owner " +
            "LEFT JOIN public.hospedajes h ON h.hospedaje_token = t.hospedaje_token " +
            "WHERE t.active = true " +
            "AND t.user_token_renter = :userTokenRenter")

    List<Ticket> reservasRealizadas(@Param("userTokenRenter") String userTokenRenter);

}