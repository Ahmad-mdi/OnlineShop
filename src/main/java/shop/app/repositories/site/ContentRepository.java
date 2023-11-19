package shop.app.repositories.site;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.app.models.site.Content;
import shop.app.models.site.Slider;

@Repository
public interface ContentRepository extends JpaRepository<Content,Long> {

}
