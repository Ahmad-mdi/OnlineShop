package shop.app.controllers.api.orders;

import org.springframework.web.bind.annotation.*;

import shop.app.Services.orders.OrderItemService;
import shop.app.helper.ui.ResponseStatus;
import shop.app.helper.ui.ServiceResponse;
import shop.app.models.orders.OrderItem;

import java.util.List;


@RestController
@RequestMapping("/api/orderItem")
public class OrderItemController {
    private final OrderItemService orderItemService;
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping("/{id}")
    public ServiceResponse<OrderItem> search(@PathVariable long id){
        try {
            OrderItem result = orderItemService.getById(id);
            return new  ServiceResponse<OrderItem>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return new ServiceResponse<OrderItem>(e);
        }
    }
    @PostMapping("/add")
    public ServiceResponse<OrderItem> add(@RequestBody OrderItem data){
        try {
            OrderItem result = orderItemService.add(data);
            return new  ServiceResponse<OrderItem>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return  new ServiceResponse<OrderItem>(e);
        }
    }
    @PutMapping("/update")
    public ServiceResponse<OrderItem> update(@RequestBody OrderItem data){
        try {
            OrderItem result = orderItemService.update(data);
            return new  ServiceResponse<OrderItem>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return  new ServiceResponse<OrderItem>(e);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ServiceResponse<Boolean> delete(@PathVariable long id){
        try {
            boolean result = orderItemService.deleteById(id);
            return new  ServiceResponse<Boolean>(result, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return  new ServiceResponse<Boolean>(e);
        }
    }
}
