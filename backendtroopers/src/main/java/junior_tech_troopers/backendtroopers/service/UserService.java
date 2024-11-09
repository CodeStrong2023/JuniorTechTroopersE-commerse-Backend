package junior_tech_troopers.backendtroopers.service;

import junior_tech_troopers.backendtroopers.DTO.response.UserPerfilDTO;
import junior_tech_troopers.backendtroopers.entity.User;
import junior_tech_troopers.backendtroopers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public UserPerfilDTO obtenerPerfilUsuario(String userToken){
        User perfilDB = userRepository.obtenerPerfilUsuarioPorUserToken(userToken);

        if (perfilDB == null) return new UserPerfilDTO();

        return new UserPerfilDTO(
                perfilDB.getImgUrl(),
                perfilDB.getFirstname(),
                perfilDB.getLastname(),
                perfilDB.getEmail(),
                perfilDB.getBirthdate(),
                perfilDB.getPhone(),
                perfilDB.getLocality(),
                perfilDB.getValidMail(),
                perfilDB.getCreatedAt(),
                null
        );
    }

}

