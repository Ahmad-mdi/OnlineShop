package shop.app.controllers.api.products;

import org.springframework.web.bind.annotation.*;
import shop.app.Services.products.ProductCategoryService;
import shop.app.helper.ui.ResponseStatus;
import shop.app.helper.ui.ServiceResponse;
import shop.app.models.orders.Invoice;
import shop.app.models.products.ProductCategory;

import java.util.List;


@RestController
@RequestMapping("/api/productCategory")
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;

    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping("")
    public ServiceResponse<ProductCategory> get(){
        try {
            List<ProductCategory> result = productCategoryService.findAllOrderByItemOrder();
            return new  ServiceResponse<ProductCategory>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return new ServiceResponse<ProductCategory>(e);
        }
    }

    @GetMapping("/{id}")
    public ServiceResponse<ProductCategory> search(@PathVariable long id){
        try {
            ProductCategory result = productCategoryService.getById(id);
            return new  ServiceResponse<ProductCategory>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return new ServiceResponse<ProductCategory>(e);
        }
    }
    @PostMapping("/add")
    public ServiceResponse<ProductCategory> add(@RequestBody ProductCategory data){
        try {
            ProductCategory result = productCategoryService.add(data);
            return new  ServiceResponse<ProductCategory>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return  new ServiceResponse<ProductCategory>(e);
        }
    }
    @PutMapping("/update")
    public ServiceResponse<ProductCategory> update(@RequestBody ProductCategory data){
        try {
            ProductCategory result = productCategoryService.update(data);
            return new  ServiceResponse<ProductCategory>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return  new ServiceResponse<ProductCategory>(e);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ServiceResponse<Boolean> delete(@PathVariable long id){
        try {
            boolean result = productCategoryService.deleteById(id);
            return new  ServiceResponse<Boolean>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return  new ServiceResponse<Boolean>(e);
        }
    }
}
