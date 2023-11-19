package shop.app.repositories.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.app.models.site.Blog;
import shop.app.models.users.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

}
