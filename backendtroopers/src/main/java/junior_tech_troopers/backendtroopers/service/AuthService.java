package prueba1.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prueba1.login.jwt.JWTResponseDTO;
import prueba1.login.jwt.JwtUtil;
import prueba1.login.entity.User;
import prueba1.login.DTO.request.UserLoginDTO;
import prueba1.login.repository.AuthRepository;

import java.util.Objects;


@Service
public class AuthService {
    // Instancia al repositorio
    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Transactional
    public void registroUsuario(User user) {

        authRepository.registrarUsuario(
                user.getUserName(),
                user.getPassword(),
                user.getImgUrl(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getBirthdate(),
                user.getPhone(),
                user.getLocality()
        );
    }

    public JWTResponseDTO loginUsuario(UserLoginDTO userLogin) {
        User userDB = authRepository.buscarUsuario(userLogin.getUsername());

        if (Objects.equals(userLogin.getPassword(), userDB.getPassword())) {
            // SI LAS CONTRASEÑAS COINCIDEN, GENERAMOS EL JWT
            return jwtUtil.generateToken(
                    userDB.getUserToken(),
                    userLogin.getUsername(),
                    userDB.getRolTitle()
            );

        } else {
           // DEVOLVEMOS UN ERROR
            return new JWTResponseDTO(
                "",
                "CONTRASEÑA INCORRECTA"
            );
        }
    }
}