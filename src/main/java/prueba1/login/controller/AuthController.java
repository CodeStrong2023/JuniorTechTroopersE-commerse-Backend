package prueba1.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import prueba1.login.entity.User;
import prueba1.login.jwt.JWTResponseDTO;
import prueba1.login.DTO.request.UserLoginDTO;
import prueba1.login.service.AuthService;

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
