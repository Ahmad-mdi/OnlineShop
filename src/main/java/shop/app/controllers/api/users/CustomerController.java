package shop.app.controllers.api.users;

import org.springframework.web.bind.annotation.*;
import shop.app.Services.users.CustomerService;
import shop.app.helper.ui.ResponseStatus;
import shop.app.helper.ui.ServiceResponse;
import shop.app.models.users.Customer;


@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    public ServiceResponse<Customer> search(@PathVariable long id){
        try {
            Customer result = customerService.getById(id);
            return new  ServiceResponse<>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return new ServiceResponse<>(e);
        }
    }
    @PostMapping("/add")
    public ServiceResponse<Customer> add(@RequestBody Customer data){
        try {
            Customer result = customerService.add(data);
            return new  ServiceResponse<>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return  new ServiceResponse<>(e);
        }
    }
    @PutMapping("/update")
    public ServiceResponse<Customer> update(@RequestBody Customer data){
        try {
            Customer result = customerService.update(data);
            return new  ServiceResponse<>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return  new ServiceResponse<>(e);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ServiceResponse<Boolean> delete(@PathVariable long id){
        try {
            boolean result = customerService.deleteById(id);
            return new  ServiceResponse<>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return  new ServiceResponse<>(e);
        }
    }
}
