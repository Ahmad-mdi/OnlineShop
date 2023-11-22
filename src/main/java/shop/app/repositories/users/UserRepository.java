package shop.app.repositories.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.app.models.users.Customer;
import shop.app.models.users.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findFirstByUsernameAndPassword(String username,String password);
}
