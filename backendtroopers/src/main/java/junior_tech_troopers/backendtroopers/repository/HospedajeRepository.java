package junior_tech_troopers.backendtroopers.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface HospedajeRepository extends CrudRepository<Hospedaje,String> {
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
}
