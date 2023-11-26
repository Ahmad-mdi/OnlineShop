package shop.app.controllers.api.products;

import org.springframework.web.bind.annotation.*;
import shop.app.Services.products.ProductService;
import shop.app.helper.ui.ResponseStatus;
import shop.app.helper.ui.ServiceResponse;
import shop.app.models.products.Product;
import shop.app.models.site.Blog;

import java.util.List;


@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/find")
    public ServiceResponse<Product> find(long categoryId){
        try {
            List<Product> result = productService.getAllByCategory(categoryId);
            return new  ServiceResponse<Product>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return new ServiceResponse<Product>(e);
        }
    }
    @GetMapping("/")
    public ServiceResponse<Product> search(@RequestParam String keyword){
        try {
            List<Product> result = productService.searching(keyword);
            return new  ServiceResponse<Product>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return new ServiceResponse<Product>(e);
        }
    }

    @GetMapping("/{id}")
    public ServiceResponse<Product> search(@PathVariable long id){
        try {
            Product result = productService.getById(id);
            return new  ServiceResponse<Product>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return new ServiceResponse<Product>(e);
        }
    }
    @PostMapping("/add")
    public ServiceResponse<Product> add(@RequestBody Product data){
        try {
            Product result = productService.add(data);
            return new  ServiceResponse<Product>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return  new ServiceResponse<Product>(e);
        }
    }
    @PutMapping("/update")
    public ServiceResponse<Product> update(@RequestBody Product data){
        try {
            Product result = productService.update(data);
            return new  ServiceResponse<Product>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return  new ServiceResponse<Product>(e);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ServiceResponse<Boolean> delete(@PathVariable long id){
        try {
            boolean result = productService.deleteById(id);
            return new  ServiceResponse<Boolean>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return  new ServiceResponse<Boolean>(e);
        }
    }
}
