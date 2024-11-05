package prueba1.login.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import prueba1.login.entity.Ticket;

import java.util.Date;

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

}