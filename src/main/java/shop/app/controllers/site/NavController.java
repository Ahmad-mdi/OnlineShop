package shop.app.controllers.site;

import org.springframework.web.bind.annotation.*;
import shop.app.Services.site.NavService;
import shop.app.models.site.Nav;

import java.util.List;

@RestController
@RequestMapping("nav")
public class NavController {

    private final NavService  navService;

    public NavController(NavService navService) {
        this.navService = navService;
    }

    @GetMapping
    public List<Nav> getAllByEnableIsTrue(){
        return navService.findAllOrderByItemOrder();
    }

}
