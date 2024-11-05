package prueba1.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prueba1.login.DTO.request.UserLoginDTO;
import prueba1.login.entity.User;
import prueba1.login.jwt.JWTResponseDTO;
import prueba1.login.jwt.JwtUtil;
import prueba1.login.repository.AuthRepository;
import prueba1.login.repository.UserRepository;
import prueba1.login.utils.BcryptFunctions;


@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthRepository authRepository;
    @Autowired
    private JwtUtil jwtUtil;

    @Transactional
    public void registroUsuario(User user) {

        if (user.getUserName() == null) return;

        String usernameToLowerCase = user.getUserName().toLowerCase();

        authRepository.registrarUsuario(
                usernameToLowerCase,
                BcryptFunctions.hashPassword(user.getPassword()),
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
        // Comodidad de usuario2
        String minuscilaUsername = userLogin.getUsername().toLowerCase();
        userLogin.setUsername(minuscilaUsername);


        User userDB = authRepository.buscarUsuario(userLogin.getUsername());

        if (userDB != null && (BcryptFunctions.comparePassword(userLogin.getPassword(), userDB.getPassword()))) {
            // SI LAS CONTRASEÑAS COINCIDEN, GENERAMOS EL JWT
            return jwtUtil.generateToken(
                    userDB.getUserToken(),
                    userLogin.getUsername(),
                    userDB.getRolTitle()
            );

        } else{
            // DEVOLVEMOS UN ERROR
            return new JWTResponseDTO(
                    "",
                    "CONTRASEÑA INCORRECTA"
            );
        }
    }

}