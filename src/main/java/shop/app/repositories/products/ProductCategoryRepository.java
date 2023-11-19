package shop.app.repositories.products;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.app.models.products.Product;
import shop.app.models.products.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Long> {
}
