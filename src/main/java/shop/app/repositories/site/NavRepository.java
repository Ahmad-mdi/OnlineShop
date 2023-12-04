package shop.app.repositories.site;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import shop.app.models.site.Nav;

import java.util.List;

@Repository
public interface NavRepository extends JpaRepository<Nav,Long> {
    List<Nav> findAllByEnableIsTrue(Sort sort);
    Nav findTopByOrderByItemOrderDesc();
    Nav findTopByItemOrder(int itemOrder);

}
