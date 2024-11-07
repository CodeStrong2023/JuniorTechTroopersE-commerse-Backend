package junior_tech_troopers.backendtroopers.controller;


import junior_tech_troopers.backendtroopers.DTO.response.HomeDTO;
import junior_tech_troopers.backendtroopers.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

