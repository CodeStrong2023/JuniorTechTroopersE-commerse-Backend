package junior_tech_troopers.backendtroopers.service;

import junior_tech_troopers.backendtroopers.DTO.request.UserLoginDTO;
import junior_tech_troopers.backendtroopers.entity.User;
import junior_tech_troopers.backendtroopers.jwt.JWTResponseDTO;
import junior_tech_troopers.backendtroopers.jwt.JwtUtil;
import junior_tech_troopers.backendtroopers.repository.AuthRepository;
import junior_tech_troopers.backendtroopers.repository.UserRepository;
import junior_tech_troopers.backendtroopers.utils.BcryptFunctions;
import junior_tech_troopers.backendtroopers.utils.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Objects;


@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthRepository authRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private Mail mail;

    @Transactional
    public void registroUsuario(User user) {

        if (user.getUserName() == null) return;

        String usernameToLowerCase = user.getUserName().toLowerCase();

        int codigoVerificacion = 100000 + new java.util.Random().nextInt(900000);

        authRepository.registrarUsuario(
                usernameToLowerCase,
                BcryptFunctions.hashPassword(user.getPassword()),
                user.getImgUrl(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getBirthdate(),
                user.getPhone(),
                user.getLocality(),
                codigoVerificacion
        );

        mail.sendEmail(
                user.getEmail(),
                "Validar cuenta",
                mail.generarTemplate(user.getFirstname() + " " + user.getLastname(),
                        codigoVerificacion
                )
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

        } else {
            // DEVOLVEMOS UN ERROR
            return new JWTResponseDTO(
                    "",
                    "CONTRASEÑA INCORRECTA"
            );
        }
    }
}