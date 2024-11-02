package prueba1.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import prueba1.login.jwt.JwtDTO;
import prueba1.login.jwt.JwtUtil;
import prueba1.login.DTO.response.UserPerfilDTO;
import prueba1.login.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin("https://trooper-stay.web.app")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/perfil")
    public UserPerfilDTO obtenerPerfilUsuario(@RequestHeader("Authorization") String jwt){
        JwtDTO jwtDTO = jwtUtil.getJwtObject(jwt);

        if (jwtDTO.getError() != null) {
            return new UserPerfilDTO(jwtDTO.getError());
        }

        return userService.obtenerPerfilUsuario(jwtDTO.getUserToken());
    }

}