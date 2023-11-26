package shop.app.controllers.api.products;

import org.springframework.web.bind.annotation.*;
import shop.app.Services.products.ColorService;
import shop.app.helper.ui.ResponseStatus;
import shop.app.helper.ui.ServiceResponse;
import shop.app.models.products.Color;



@RestController
@RequestMapping("/api/color")
public class ColorController {
    private final ColorService colorService;

    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }
    
    @GetMapping("/{id}")
    public ServiceResponse<Color> search(@PathVariable long id){
        try {
            Color result = colorService.getById(id);
            return new  ServiceResponse<Color>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return new ServiceResponse<Color>(e);
        }
    }
    @PostMapping("/add")
    public ServiceResponse<Color> add(@RequestBody Color data){
        try {
            Color result = colorService.add(data);
            return new  ServiceResponse<Color>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return  new ServiceResponse<Color>(e);
        }
    }
    @PutMapping("/update")
    public ServiceResponse<Color> update(@RequestBody Color data){
        try {
            Color result = colorService.update(data);
            return new  ServiceResponse<Color>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return  new ServiceResponse<Color>(e);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ServiceResponse<Boolean> delete(@PathVariable long id){
        try {
            boolean result = colorService.deleteById(id);
            return new  ServiceResponse<Boolean>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return  new ServiceResponse<Boolean>(e);
        }
    }
}
