package prueba1.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prueba1.login.DTO.response.HomeDTO;
import prueba1.login.entity.Hospedaje;
import prueba1.login.repository.HospedajeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeService {
    @Autowired
    private HospedajeRepository hospedajeRepository;

    public List<HomeDTO> getHome() {
        List<Hospedaje> hospedajesEntity = hospedajeRepository.obtenerHospedajesParaHome();
        List<HomeDTO> homeDTOLista = new ArrayList<>();

        for (Hospedaje hospedaje : hospedajesEntity) {
            HomeDTO homeDTO = new HomeDTO();
            homeDTO.setNombreHospedaje(hospedaje.getNombreHospedaje());
            homeDTO.setDescription(hospedaje.getDescription());
            homeDTO.setLocality(hospedaje.getLocality());
            homeDTO.setImg_url(hospedaje.getImgUrlHospedajeImg());

            homeDTOLista.add(homeDTO);
        }

        return homeDTOLista;
    }

}
