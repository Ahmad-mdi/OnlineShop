package shop.app.controllers.api.products;

import org.springframework.web.bind.annotation.*;
import shop.app.Services.products.FeatureService;
import shop.app.helper.ui.ResponseStatus;
import shop.app.helper.ui.ServiceResponse;
import shop.app.models.products.Feature;


@RestController
@RequestMapping("/api/feature")
public class FeatureController {
    private final FeatureService featureService;

    public FeatureController(FeatureService featureService) {
        this.featureService = featureService;
    }
    @GetMapping("/{id}")
    public ServiceResponse<Feature> search(@PathVariable long id){
        try {
            Feature result = featureService.getById(id);
            return new  ServiceResponse<Feature>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return new ServiceResponse<Feature>(e);
        }
    }
    @PostMapping("/add")
    public ServiceResponse<Feature> add(@RequestBody Feature data){
        try {
            Feature result = featureService.add(data);
            return new  ServiceResponse<Feature>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return  new ServiceResponse<Feature>(e);
        }
    }
    @PutMapping("/update")
    public ServiceResponse<Feature> update(@RequestBody Feature data){
        try {
            Feature result = featureService.update(data);
            return new  ServiceResponse<Feature>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return  new ServiceResponse<Feature>(e);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ServiceResponse<Boolean> delete(@PathVariable long id){
        try {
            boolean result = featureService.deleteById(id);
            return new  ServiceResponse<Boolean>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return  new ServiceResponse<Boolean>(e);
        }
    }
}
