package shop.app.repositories.products;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.app.models.products.Feature;
import shop.app.models.products.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
