package junior_tech_troopers.backendtroopers.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,String>{
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
