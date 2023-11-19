package shop.app.repositories.site;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.app.models.site.Blog;
import shop.app.models.site.Content;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Long> {

}
