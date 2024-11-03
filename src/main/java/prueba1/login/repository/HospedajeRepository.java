package prueba1.login.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import prueba1.login.entity.Hospedaje;

import java.util.List;

@Repository
public interface HospedajeRepository extends CrudRepository<Hospedaje, String>{

    @Query(
            "SELECT " +
                    "hospedaje.nombre, " +
                    "hospedaje.description, " +
                    "hospedaje.locality, " +
                    "(SELECT img.img_url AS imgUrlHospedajeImg " +
                    "FROM public.hospedajes_img AS img " +
                    "WHERE img.hospedaje_token = hospedaje.hospedaje_token LIMIT 1) " +
                    "FROM public.hospedajes AS hospedaje " +
                    "WHERE hospedaje.active = true;"
    )
    List<Hospedaje> obtenerHospedajesParaHome();

    @Modifying
    @Query("INSERT INTO public.hospedajes " +
            "(hospedaje_token," +
            " user_token," +
            " capacity," +
            " description," +
            " price," +
            " locality, " +
            "wifi, " +
            "tv, " +
            "garage," +
            "air_conditioning," +
            "heating," +
            "pool, " +
            "created_at, " +
            "active, " +
            "nombre) " +
            "VALUES (" +
            ":hospedajeToken, " +
            ":userToken, " +
            ":capacity, " +
            ":description, " +
            ":price, " +
            ":locality, " +
            ":wifi, " +
            ":tv, " +
            ":garage, " +
            ":airConditioning, " +
            ":heating, " +
            ":pool, " +
            "CURRENT_TIMESTAMP, " +
            "true, " +
            ":name)")
    void insertHospedaje(
                         @Param("hospedajeToken") String hospedajeToken,
                         @Param("userToken") String userToken,
                         @Param("capacity") Integer capacity,
                         @Param("description") String description,
                         @Param("price") Double price,
                         @Param("locality") String locality,
                         @Param("wifi") Boolean wifi,
                         @Param("tv") Boolean tv,
                         @Param("garage") Boolean garage,
                         @Param("airConditioning") Boolean airConditioning,
                         @Param("heating") Boolean heating,
                         @Param("pool") Boolean pool,
                         @Param("name") String name);


    @Query("SELECT " +
            " hospedaje_token," +
            "user_token, " +
            "capacity, " +
            "description, " +
            "price, " +
            "locality, " +
            "wifi, " +
            "tv, " +
            "garage," +
            "air_conditioning, " +
            "heating, " +
            "pool, " +
            "created_at, " +
            "active, " +
            "nombre " +
               "FROM public.hospedajes " +
            "WHERE user_token= :userToken")
    List<Hospedaje> findAllHospedajes(@Param("userToken") String userToken);

    @Query(
            "SELECT " +
                    "hospedaje.hospedaje_token, " +
                    "hospedaje.price, " +
                    "hospedaje.nombre, " +
                    "(SELECT img.img_url AS imgUrlHospedajeImg " +
                    "FROM public.hospedajes_img AS img " +
                    "WHERE img.hospedaje_token = hospedaje.hospedaje_token LIMIT 1) " +
                    "FROM public.hospedajes AS hospedaje " +
                    "WHERE hospedaje.active = true " +
                    "ORDER BY hospedaje.created_at DESC " +
                    "limit 16;"
    )
    List<Hospedaje> obtenerDestinosHospedajes();

}
