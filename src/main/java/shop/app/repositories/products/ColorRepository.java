package shop.app.repositories.products;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.app.models.orders.Invoice;
import shop.app.models.products.Color;

import java.util.List;

public interface ColorRepository extends JpaRepository<Color,Long> {
    @Override
    List<Color> findAll();

}
