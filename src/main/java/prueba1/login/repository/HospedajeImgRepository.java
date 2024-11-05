package prueba1.login.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import prueba1.login.entity.HospedajeImg;

import java.util.List;

@Repository
public interface HospedajeImgRepository extends CrudRepository<HospedajeImg, String> {
    @Modifying
    @Query("INSERT INTO public.hospedajes_img (" +
            "img_token, " +
            "hospedaje_token, " +
            "img_url) " +
            "VALUES (gen_random_uuid(), " +
            ":hospedajeToken, " +
            ":imgUrl)")
    void insertHospedajeImg(@Param("hospedajeToken") String hospedajeToken,
                            @Param("imgUrl") String imgUrl);

    
    @Query("SELECT " +
            "img_token, " +
            "img_url " +
            "FROM public.hospedajes_img " +
            "WHERE hospedaje_token = :hospedajeToken " +
            "AND active = true;")
    List<HospedajeImg> obtenerImagenesDeUnHospedaje(@Param("hospedajeToken") String hospedajeToken);
}
