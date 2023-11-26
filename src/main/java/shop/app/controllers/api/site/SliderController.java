package shop.app.controllers.api.site;

import org.springframework.web.bind.annotation.*;
import shop.app.Services.site.SliderService;
import shop.app.helper.ui.ResponseStatus;
import shop.app.helper.ui.ServiceResponse;
import shop.app.models.site.Slider;

import java.util.List;


@RestController
@RequestMapping("/api/slider")
public class SliderController {
    private final SliderService sliderService;

    public SliderController(SliderService sliderService) {
        this.sliderService = sliderService;
    }

    @GetMapping
    public ServiceResponse<Slider> get() {
        try {
            List<Slider> result = sliderService.findAllOrderByItemOrder();
            return new ServiceResponse<Slider>(result, ResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ServiceResponse<Slider>(e);
        }
    }

    @GetMapping("/{id}")
    public ServiceResponse<Slider> search(@PathVariable long id) {
        try {
            Slider result = sliderService.getById(id);
            return new ServiceResponse<Slider>(result, ResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ServiceResponse<Slider>(e);
        }
    }

    @PostMapping("/add")
    public ServiceResponse<Slider> add(@RequestBody Slider data) {
        try {
            Slider result = sliderService.add(data);
            return new ServiceResponse<Slider>(result, ResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ServiceResponse<Slider>(e);
        }
    }

    @PutMapping("/update")
    public ServiceResponse<Slider> update(@RequestBody Slider data) {
        try {
            Slider result = sliderService.update(data);
            return new ServiceResponse<Slider>(result, ResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ServiceResponse<Slider>(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ServiceResponse<Boolean> delete(@PathVariable long id) {
        try {
            boolean result = sliderService.deleteById(id);
            return new ServiceResponse<Boolean>(result, ResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ServiceResponse<Boolean>(e);
        }
    }
}
