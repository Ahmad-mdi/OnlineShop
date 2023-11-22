package shop.app.repositories.site;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import shop.app.models.site.Blog;
import shop.app.models.site.Content;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Long> {
    @Query("from Blog where title like concat('%',:search,'%') or description like concat('%',:search,'%') ")
    List<Blog> findAllByTitleContainsOrDescriptionContains(String search);
}
