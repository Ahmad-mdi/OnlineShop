package shop.app.Services.orders;

import org.springframework.stereotype.Service;
import shop.app.helper.exceptions.DataNotFoundException;
import shop.app.models.orders.OrderItem;
import shop.app.repositories.orders.OrderItemRepository;
import java.util.Optional;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public OrderItem getById(long id) {
        Optional<OrderItem> data = orderItemRepository.findById(id);
        if (data.isPresent()) return data.get();
        return null;

    }

    public OrderItem add(OrderItem data) {
        return orderItemRepository.save(data);
    }

    public OrderItem update(OrderItem data) throws DataNotFoundException {
        OrderItem oldData = getById(data.getId());//getId with db
        if (oldData == null){
            throw new DataNotFoundException("data with id"+data.getId()+"not found");
        }
        oldData.setCount(data.getCount());
        oldData.setPrice(data.getPrice());
        return orderItemRepository.save(oldData);
    }

    public boolean deleteById(long id) throws DataNotFoundException {
        OrderItem oldData = getById(id);//getId with db
        if (oldData == null){
            throw new DataNotFoundException("data with id"+id+"not found");
        }
        orderItemRepository.deleteById(id);
        return true;
    }

}
