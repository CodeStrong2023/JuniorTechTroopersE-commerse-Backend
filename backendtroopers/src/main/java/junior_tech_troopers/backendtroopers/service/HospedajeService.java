package junior_tech_troopers.backendtroopers.service;

import junior_tech_troopers.backendtroopers.DTO.request.HospedajeDTO;
import junior_tech_troopers.backendtroopers.DTO.request.HospedajeImagenesDTO;
import junior_tech_troopers.backendtroopers.DTO.response.DestinoDTO;
import junior_tech_troopers.backendtroopers.DTO.response.DestinoSeleccionadoDTO;
import junior_tech_troopers.backendtroopers.entity.Hospedaje;
import junior_tech_troopers.backendtroopers.entity.HospedajeImg;
import junior_tech_troopers.backendtroopers.repository.HospedajeImgRepository;
import junior_tech_troopers.backendtroopers.repository.HospedajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class HospedajeService {
    @Autowired
    private HospedajeRepository hospedajeRepository;
    @Autowired
    private HospedajeImgRepository hospedajeImgRepository;


    @Transactional
    public void insertHospedaje(HospedajeDTO hospedajeDTO, String userToken) {
        String hospedajeToken = UUID.randomUUID().toString();

        hospedajeRepository.insertHospedaje(
                hospedajeToken,
                userToken,
                hospedajeDTO.getCapacity(),
                hospedajeDTO.getDescription(),
                hospedajeDTO.getPrice(),
                hospedajeDTO.getLocality(),
                hospedajeDTO.getWifi(),
                hospedajeDTO.getTv(),
                hospedajeDTO.getGarage(),
                hospedajeDTO.getAirConditioning(),
                hospedajeDTO.getHeating(),
                hospedajeDTO.getPool(),
                hospedajeDTO.getName()
        );
        for (HospedajeImagenesDTO imagen : hospedajeDTO.getImages()) {
            hospedajeImgRepository.insertHospedajeImg(
                    hospedajeToken,
                    imagen.getImgUrl()
            );
        }
    }



    public List<HospedajeDTO> hospedajesPropios(String userToken) {
        List<Hospedaje> hospedajes = hospedajeRepository.findAllHospedajes(userToken);
        List<HospedajeDTO> hospedajesDTO = new ArrayList<>();

        for (Hospedaje hospedaje : hospedajes) {
            List<HospedajeImg> hospedajeImgsList = hospedajeImgRepository.obtenerImagenesDeUnHospedaje(hospedaje.getHospedajeToken());
            HospedajeDTO hospedajeDTO = new HospedajeDTO();
            hospedajeDTO.setImages(new ArrayList<>());

            hospedajeDTO.setName(hospedaje.getNombreHospedaje());
            hospedajeDTO.setCapacity(hospedaje.getCapacity());
            hospedajeDTO.setDescription(hospedaje.getDescription());
            hospedajeDTO.setPrice(hospedaje.getPrice());
            hospedajeDTO.setLocality(hospedaje.getLocality());
            hospedajeDTO.setWifi(hospedaje.getWifi());
            hospedajeDTO.setTv(hospedaje.getTv());
            hospedajeDTO.setGarage(hospedaje.getGarage());
            hospedajeDTO.setAirConditioning(hospedaje.getAirConditioning());
            hospedajeDTO.setHeating(hospedaje.getHeating());
            hospedajeDTO.setPool(hospedaje.getPool());

            for (HospedajeImg image : hospedajeImgsList) {
                HospedajeImagenesDTO hospedajeImagenesDTO = new HospedajeImagenesDTO();
                hospedajeImagenesDTO.setImgUrl(image.getImgUrl());
                hospedajeDTO.getImages().add(hospedajeImagenesDTO);
            }
            hospedajesDTO.add(hospedajeDTO);

        }

        return hospedajesDTO;
    }
    public List<DestinoDTO> destinosHospedajes(String usertoken, String locality, String date) {
        List<Hospedaje> hospedajes = hospedajeRepository.obtenerDestinosHospedajes(usertoken, locality, date);
        List<DestinoDTO> destinosDTO = new ArrayList<>();

        for (Hospedaje hospedaje : hospedajes) {
            DestinoDTO destinoDTO = new DestinoDTO();
            destinoDTO.setHospedajeToken(hospedaje.getHospedajeToken());
            destinoDTO.setNombreHospedaje(hospedaje.getNombreHospedaje());
            destinoDTO.setPrice(hospedaje.getPrice());
            destinoDTO.setImg_url(hospedaje.getImgUrlHospedajeImg());
            destinosDTO.add(destinoDTO);
        }
        return destinosDTO;
    }

    public DestinoSeleccionadoDTO destinoSeleccionadoDTO(String hospedajeToken){
        Hospedaje hospedaje = hospedajeRepository.obtenerDestinoSeleccionado(hospedajeToken);
        DestinoSeleccionadoDTO destinoSeleccionadoDTO = new DestinoSeleccionadoDTO();
        destinoSeleccionadoDTO.setHospedajeToken(hospedaje.getHospedajeToken());
        destinoSeleccionadoDTO.setName(hospedaje.getNombreHospedaje());
        destinoSeleccionadoDTO.setCapacity(hospedaje.getCapacity());
        destinoSeleccionadoDTO.setDescription(hospedaje.getDescription());
        destinoSeleccionadoDTO.setPrice(hospedaje.getPrice());
        destinoSeleccionadoDTO.setLocality(hospedaje.getLocality());
        destinoSeleccionadoDTO.setWifi(hospedaje.getWifi());
        destinoSeleccionadoDTO.setTv(hospedaje.getTv());
        destinoSeleccionadoDTO.setGarage(hospedaje.getGarage());
        destinoSeleccionadoDTO.setAirConditioning(hospedaje.getAirConditioning());
        destinoSeleccionadoDTO.setHeating(hospedaje.getHeating());
        destinoSeleccionadoDTO.setPool(hospedaje.getPool());
        destinoSeleccionadoDTO.setUrlImg(hospedaje.getImgUrlHospedajeImg());

        return destinoSeleccionadoDTO;
    }

}
