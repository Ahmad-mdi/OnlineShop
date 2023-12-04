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
    public ServiceResponse<Nav> get() {
        try {
            List<Nav> result = navService.findAllOrderByItemOrder();
            return new ServiceResponse<Nav>(result, ResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ServiceResponse<Nav>(e);
        }
    }

    @GetMapping("/getAll")
    public ServiceResponse<Nav> getAll(
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber) {
        try {
            List<Nav> result = navService.getAll(pageSize, pageNumber);
            long totalCount = navService.getAllCount();
            return new ServiceResponse<>(result,totalCount, ResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ServiceResponse<>(e);
        }
    }

    @GetMapping("/{id}")
    public ServiceResponse<Nav> getById(@PathVariable long id) {
        try {
            Nav result = navService.getById(id);
            return new ServiceResponse<Nav>(result, ResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ServiceResponse<Nav>(e);
        }
    }

    @PostMapping("/add")
    public ServiceResponse<Nav> add(@RequestBody Nav data) {
        try {
            Nav result = navService.add(data);
            return new ServiceResponse<Nav>(result, ResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ServiceResponse<Nav>(e);
        }
    }

    @PostMapping("/changeOrder/{id}/{direction}")
    public ServiceResponse<Nav> changeOrder(@PathVariable long id ,@PathVariable int direction) {
        try {
            Nav result = navService.changeOrder(id,direction);
            return new ServiceResponse<Nav>(result, ResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ServiceResponse<Nav>(e);
        }
    }

    @PutMapping("/update")
    public ServiceResponse<Nav> update(@RequestBody Nav data) {
        try {
            Nav result = navService.update(data);
            return new ServiceResponse<Nav>(result, ResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ServiceResponse<Nav>(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ServiceResponse<Boolean> delete(@PathVariable long id) {
        try {
            boolean result = navService.deleteById(id);
            return new ServiceResponse<Boolean>(result, ResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ServiceResponse<Boolean>(e);
        }
    }
}
