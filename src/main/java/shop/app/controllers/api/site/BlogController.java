package shop.app.controllers.api.site;

import org.springframework.web.bind.annotation.*;
import shop.app.Services.site.BlogService;
import shop.app.helper.ui.ResponseStatus;
import shop.app.helper.ui.ServiceResponse;
import shop.app.models.site.Blog;
import shop.app.models.site.Slider;

import java.util.List;


@RestController
@RequestMapping("/api/blog")
public class BlogController {
    private final BlogService blogService;
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public ServiceResponse<Blog> search(@RequestParam String keyword){
        try{
            List<Blog> result = blogService.search(keyword);
            return new ServiceResponse<>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
                return new ServiceResponse<>(e);
        }
    }
    @GetMapping("/{id}")
    public ServiceResponse<Blog> getById(@PathVariable long id){
        try {
            Blog result = blogService.getById(id);
            return new  ServiceResponse<>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return new ServiceResponse<>(e);
        }
    }

    @GetMapping("/getAll")
    public ServiceResponse<Blog> getAll(
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber) {
        try {
            List<Blog> result = blogService.getAll(pageSize, pageNumber);
            long totalCount = blogService.getAllCount();
            return new ServiceResponse<>(result,totalCount, ResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ServiceResponse<>(e);
        }
    }
    @PostMapping("/add")
    public ServiceResponse<Blog> add(@RequestBody Blog data){
        try {
            Blog result = blogService.add(data);
            return new  ServiceResponse<>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return  new ServiceResponse<>(e);
        }
    }
    @PutMapping("/update")
    public ServiceResponse<Blog> update(@RequestBody Blog data){
        try {
            Blog result = blogService.update(data);
            return new  ServiceResponse<>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return  new ServiceResponse<>(e);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ServiceResponse<Boolean> delete(@PathVariable long id){
        try {
            boolean result = blogService.deleteById(id);
            return new  ServiceResponse<>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return  new ServiceResponse<>(e);
        }
    }

    @PutMapping("/increaseVisit/{id}")
    public ServiceResponse<Blog> increase(@RequestBody long id){
        try {
            Blog result = blogService.increaseVisitCount(id);
            return new  ServiceResponse<>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return  new ServiceResponse<>(e);
        }
    }
}
