package shop.app.repositories.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import shop.app.models.products.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("from Product where category.id =:categoryId")
    List<Product> findAllByCategory(long categoryId);

    @Query("from Product where enable=true and (title like concat('%',:search,'%') or description like concat('%',:search,'%') ) ")
    List<Product> findAllByEnableIsTrueAndTitleContainsOrDescriptionContains(String search);
}
