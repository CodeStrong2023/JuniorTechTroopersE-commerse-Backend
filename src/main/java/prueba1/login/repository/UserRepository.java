package prueba1.login.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import prueba1.login.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    @Query(
            "SELECT " +
                    "    img_url, " +
                    "    firstname, " +
                    "    lastname, " +
                    "    email, " +
                    "    birthdate, " +
                    "    phone, " +
                    "    locality, " +
                    "    valid_mail, " +
                    "    created_at " +
                    "FROM public.users " +
                    "WHERE user_token = :userToken " +
                    "    AND active = true"
    )
    User obtenerPerfilUsuarioPorUserToken(@Param("userToken") String userToken);

}
