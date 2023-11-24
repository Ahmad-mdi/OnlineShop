package shop.app.controllers.api.site;

import org.springframework.web.bind.annotation.*;
import shop.app.Services.site.BlogService;
import shop.app.helper.ui.ResponseStatus;
import shop.app.helper.ui.ServiceResponse;
import shop.app.models.site.Blog;
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
            return new  ServiceResponse<Blog>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
                return new ServiceResponse<Blog>(e);
        }
    }
    @GetMapping("/{id}")
    public ServiceResponse<Blog> search(@PathVariable long id){
        try {
            Blog result = blogService.getById(id);
            return new  ServiceResponse<Blog>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return new ServiceResponse<Blog>(e);
        }
    }
    @PostMapping("/add")
    public ServiceResponse<Blog> add(@RequestBody Blog data){
        try {
            Blog result = blogService.add(data);
            return new  ServiceResponse<Blog>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return  new ServiceResponse<Blog>(e);
        }
    }
    @PutMapping("/update")
    public ServiceResponse<Blog> update(@RequestBody Blog data){
        try {
            Blog result = blogService.update(data);
            return new  ServiceResponse<Blog>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return  new ServiceResponse<Blog>(e);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ServiceResponse<Boolean> delete(@PathVariable long id){
        try {
            boolean result = blogService.deleteById(id);
            return new  ServiceResponse<Boolean>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return  new ServiceResponse<Boolean>(e);
        }
    }

    @PutMapping("/increaseVisit/{id}")
    public ServiceResponse<Blog> increase(@RequestBody long id){
        try {
            Blog result = blogService.increaseVisitCount(id);
            return new  ServiceResponse<Blog>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return  new ServiceResponse<Blog>(e);
        }
    }


}
