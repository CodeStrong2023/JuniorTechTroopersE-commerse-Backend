package junior_tech_troopers.backendtroopers.controller;


import junior_tech_troopers.backendtroopers.DTO.request.HospedajeDTO;
import junior_tech_troopers.backendtroopers.DTO.response.DestinoDTO;
import junior_tech_troopers.backendtroopers.DTO.response.DestinoSeleccionadoDTO;
import junior_tech_troopers.backendtroopers.service.HospedajeService;
import junior_tech_troopers.backendtroopers.jwt.JwtDTO;
import junior_tech_troopers.backendtroopers.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospedaje")
@CrossOrigin("https://trooper-stay.web.app")
public class HospedajeController {
    @Autowired
    private HospedajeService hospedajeService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public void crearHospedaje(@RequestHeader("Authorization") String jwt, @RequestBody HospedajeDTO hospedajeDTO){
        JwtDTO jwtDTO = jwtUtil.getJwtObject(jwt);

        if (jwtDTO.getError() != null) {
            return ;
        }

        hospedajeService.insertHospedaje(hospedajeDTO, jwtDTO.getUserToken());
    }
    @GetMapping
    public List<HospedajeDTO> obtenerHospedajes(@RequestHeader("Authorization") String jwt){
        JwtDTO jwtDTO = jwtUtil.getJwtObject(jwt);

        if (jwtDTO.getError() != null) {
            return null;
        }
        return hospedajeService.hospedajesPropios(jwtDTO.getUserToken());
    }

    @GetMapping("/destinos")
    public List<DestinoDTO> destinosHospedajes(@RequestHeader("Authorization") String jwt,
                                               @Param("locality") String locality,
                                               @Param("date") String date){
        JwtDTO jwtDTO = jwtUtil.getJwtObject(jwt);

        if (jwtDTO.getError() != null) {
            return null;
        }
        return hospedajeService.destinosHospedajes(jwtDTO.getUserToken(),locality, date);
    }

    @GetMapping("/destinos/{hospedajeToken}")
    public DestinoSeleccionadoDTO destinoSeleccionadoDTO(@RequestHeader("Authorization") String jwt,
                                                         @PathVariable("hospedajeToken") String hospedajeToken){
        JwtDTO jwtDTO = jwtUtil.getJwtObject(jwt);

        if (jwtDTO.getError() != null) {
            return null;
        }
        return hospedajeService.destinoSeleccionadoDTO(hospedajeToken);
    }
}
