package prueba1.login.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prueba1.login.DTO.response.HomeDTO;
import prueba1.login.service.HomeService;

import java.util.List;


@RestController
@RequestMapping("/home")
@CrossOrigin("https://trooper-stay.web.app")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping()
    public List<HomeDTO> getHome() {
        return homeService.getHome();
    }
}
