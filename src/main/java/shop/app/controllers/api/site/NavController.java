package shop.app.controllers.api.site;

import org.springframework.web.bind.annotation.*;
import shop.app.Services.site.NavService;
import shop.app.helper.ui.ResponseStatus;
import shop.app.helper.ui.ServiceResponse;
import shop.app.models.site.Nav;

import java.util.List;


@RestController
@RequestMapping("/api/nav")
public class NavController {
    private final NavService navService;
    public NavController(NavService navService) {
        this.navService = navService;
    }

    @GetMapping
    public ServiceResponse<Nav> get(){
        try{
            List<Nav> result = navService.findAllOrderByItemOrder();
            return new  ServiceResponse<Nav>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
                return new ServiceResponse<Nav>(e);
        }
        //hello
    }
    @GetMapping("/{id}")
    public ServiceResponse<Nav> search(@PathVariable long id){
        try {
            Nav result = navService.getById(id);
            return new  ServiceResponse<Nav>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return new ServiceResponse<Nav>(e);
        }
    }
    @PostMapping("/add")
    public ServiceResponse<Nav> add(@RequestBody Nav data){
        try {
            Nav result = navService.add(data);
            return new  ServiceResponse<Nav>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return  new ServiceResponse<Nav>(e);
        }
    }
    @PutMapping("/update")
    public ServiceResponse<Nav> update(@RequestBody Nav data){
        try {
            Nav result = navService.update(data);
            return new  ServiceResponse<Nav>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return  new ServiceResponse<Nav>(e);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ServiceResponse<Boolean> delete(@PathVariable long id){
        try {
            boolean result = navService.deleteById(id);
            return new  ServiceResponse<Boolean>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return  new ServiceResponse<Boolean>(e);
        }
    }
}
