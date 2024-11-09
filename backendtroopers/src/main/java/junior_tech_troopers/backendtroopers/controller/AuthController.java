package prueba1.login.controller;



import junior_tech_troopers.backendtroopers.DTO.request.UserLoginDTO;
import junior_tech_troopers.backendtroopers.entity.User;
import junior_tech_troopers.backendtroopers.jwt.JWTResponseDTO;
import junior_tech_troopers.backendtroopers.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@CrossOrigin("https://trooper-stay.web.app")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public void registroUsuario(@RequestBody User user){

        authService.registroUsuario(user);
    }

    @PostMapping("/login")
    public JWTResponseDTO loginUsuario(@RequestBody UserLoginDTO userLogin){
        return authService.loginUsuario(userLogin);
    }

}
