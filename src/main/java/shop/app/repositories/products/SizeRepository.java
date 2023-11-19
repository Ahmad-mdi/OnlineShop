package shop.app.repositories.products;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.app.models.products.Color;
import shop.app.models.products.Size;

public interface SizeRepository extends JpaRepository<Size,Long> {
}
