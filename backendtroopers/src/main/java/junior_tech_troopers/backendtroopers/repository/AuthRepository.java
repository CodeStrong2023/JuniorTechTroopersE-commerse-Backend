package junior_tech_troopers.backendtroopers.repository;

import junior_tech_troopers.backendtroopers.entity.User;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface AuthRepository extends CrudRepository<User,Integer> {
    @Modifying
    @Query("INSERT INTO public.users( " +
            "  id, " +                   // Asumiendo que el ID se genera automáticamente o se asigna un valor
            "  user_token, " +
            "  user_rol, " +
            "  username, " +
            "  password, " +
            "  img_url, " +
            "  firstname, " +
            "  lastname, " +
            "  email, " +
            "  birthdate, " +
            "  phone, " +
            "  locality, " +             // Se agregó la columna locality
            "  valid_mail, " +          // Se agregó la columna valid_mail
            "  created_at, " +
            "  active, " +                // Se agregó la columna active
            "  cod_verification " +
            ") VALUES ( " +
            "  DEFAULT, " +              // Asumiendo que el id se genera automáticamente
            "  gen_random_uuid(), " +    // UUID generado automáticamente
            "  (SELECT rol_token FROM public.roles where title = 'admin'), " +             // Mapeo de rol desde la variable
            "  :username, " +            // Mapeo del nombre de usuario
            "  :password, " +            // Mapeo de la contraseña
            "  :imgUrl, " +              // Mapeo de la URL de la imagen
            "  :firstname, " +           // Mapeo del primer nombre
            "  :lastname, " +            // Mapeo del apellido
            "  :email, " +               // Mapeo del email
            "  :birthdate, " +           // Mapeo de la fecha de nacimiento
            "  :phone, " +               // Mapeo del número de teléfono
            "  :locality, " +            // Mapeo de la localidad
            "  false, " +                 // Asumiendo que valid_mail es un booleano y se inicializa en true
            "  CURRENT_TIMESTAMP, " +    // Fecha y hora actuales
            "  true, " +                   // Asumiendo que active es un booleano y se inicializa en true
            "  :cod_verification" +
            ");")
    void registrarUsuario(
            @Param("username") String username,
            @Param("password") String password,
            @Param("imgUrl") String imgUrl,
            @Param("firstname") String firstname,
            @Param("lastname") String lastname,
            @Param("email") String email,
            @Param("birthdate") Date birthdate,
            @Param("phone") String phone,
            @Param("locality") String locality,  // Se agregó el parámetro para la localidad
            @Param("cod_verification") Integer codVerification
    );


    @Query("SELECT " +
            "u.user_token AS user_token, " +
            "r.title AS titulo_rol, " +
            "u.password AS password " +
            "FROM public.users AS u " +
            "LEFT JOIN public.roles AS r ON r.rol_token = u.user_rol " +
            "WHERE u.username = :username")
    User buscarUsuario(@Param("username") String username);


}
