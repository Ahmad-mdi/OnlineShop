package shop.app.repositories.site;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.app.models.site.Nav;
import shop.app.models.site.Slider;

@Repository
public interface SliderRepository extends JpaRepository<Slider,Long> {

}
