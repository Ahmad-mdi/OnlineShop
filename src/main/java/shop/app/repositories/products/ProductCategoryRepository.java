package shop.app.repositories.products;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import shop.app.models.products.ProductCategory;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Long> {
    List<ProductCategory> findAllByEnableIsTrue(Sort sort);
}
