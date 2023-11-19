package shop.app.Services.site;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.app.repositories.site.SliderRepository;

@Service
public class SliderService {

    private SliderRepository sliderRepository;

    public SliderService(SliderRepository sliderRepository) {
        this.sliderRepository = sliderRepository;
    }
}
