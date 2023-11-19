package shop.app.repositories.site;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import shop.app.models.site.Nav;

@Repository
public interface NavRepository extends JpaRepository<Nav,Long> {

}
