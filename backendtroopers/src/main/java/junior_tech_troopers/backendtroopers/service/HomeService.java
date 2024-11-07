package junior_tech_troopers.backendtroopers.service;

import junior_tech_troopers.backendtroopers.DTO.response.HomeDTO;
import junior_tech_troopers.backendtroopers.entity.Hospedaje;
import junior_tech_troopers.backendtroopers.repository.HospedajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            homeDTO.setHospedajeToken(hospedaje.getHospedajeToken());
            homeDTO.setNombreHospedaje(hospedaje.getNombreHospedaje());
            homeDTO.setDescription(hospedaje.getDescription());
            homeDTO.setLocality(hospedaje.getLocality());
            homeDTO.setImg_url(hospedaje.getImgUrlHospedajeImg());
            homeDTOLista.add(homeDTO);
        }

        return homeDTOLista;
    }

}
