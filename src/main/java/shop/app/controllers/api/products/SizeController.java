package shop.app.controllers.api.products;

import org.springframework.web.bind.annotation.*;
import shop.app.Services.products.SizeService;
import shop.app.helper.ui.ResponseStatus;
import shop.app.helper.ui.ServiceResponse;
import shop.app.models.products.Size;


@RestController
@RequestMapping("/api/size")
public class SizeController {
    private final SizeService sizeService;

    public SizeController(SizeService sizeService) {
        this.sizeService = sizeService;
    }

    @GetMapping("/{id}")
    public ServiceResponse<Size> search(@PathVariable long id){
        try {
            Size result = sizeService.getById(id);
            return new  ServiceResponse<Size>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return new ServiceResponse<Size>(e);
        }
    }
    @PostMapping("/add")
    public ServiceResponse<Size> add(@RequestBody Size data){
        try {
            Size result = sizeService.add(data);
            return new  ServiceResponse<Size>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return  new ServiceResponse<Size>(e);
        }
    }
    @PutMapping("/update")
    public ServiceResponse<Size> update(@RequestBody Size data){
        try {
            Size result = sizeService.update(data);
            return new  ServiceResponse<Size>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return  new ServiceResponse<Size>(e);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ServiceResponse<Boolean> delete(@PathVariable long id){
        try {
            boolean result = sizeService.deleteById(id);
            return new  ServiceResponse<Boolean>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return  new ServiceResponse<Boolean>(e);
        }
    }
}
