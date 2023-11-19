package shop.app.repositories.products;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.app.models.orders.Invoice;
import shop.app.models.products.Color;

public interface ColorRepository extends JpaRepository<Color,Long> {
}
