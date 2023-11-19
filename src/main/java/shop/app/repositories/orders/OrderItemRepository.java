package shop.app.repositories.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.app.models.orders.Invoice;
import shop.app.models.orders.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
