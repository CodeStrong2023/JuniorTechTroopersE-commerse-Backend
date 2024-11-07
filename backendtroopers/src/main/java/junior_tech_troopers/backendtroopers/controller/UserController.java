package junior_tech_troopers.backendtroopers.controller;


import junior_tech_troopers.backendtroopers.DTO.response.UserPerfilDTO;
import junior_tech_troopers.backendtroopers.service.UserService;
import junior_tech_troopers.backendtroopers.jwt.JwtDTO;
import junior_tech_troopers.backendtroopers.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
