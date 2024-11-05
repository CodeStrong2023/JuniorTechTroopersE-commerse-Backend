package prueba1.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prueba1.login.entity.User;
import prueba1.login.DTO.response.UserPerfilDTO;
import prueba1.login.repository.UserRepository;

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
