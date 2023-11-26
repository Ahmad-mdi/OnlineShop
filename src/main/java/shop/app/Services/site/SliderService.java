package shop.app.Services.site;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import shop.app.helper.exceptions.DataNotFoundException;
import shop.app.models.site.Slider;
import shop.app.repositories.site.SliderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SliderService {

    private final SliderRepository sliderRepository;

    //construct method:
    public SliderService(SliderRepository sliderRepository) {
        this.sliderRepository = sliderRepository;
    }

    //get all sliders sort by itemOrder:
    public List<Slider> findAllOrderByItemOrder() {
        return  sliderRepository.findAllByEnableIsTrue(Sort.by("itemOrder"));
    }

    //get slider by id:
    public Slider getById(long id) {
        Optional<Slider> data = sliderRepository.findById(id);
        if (data.isPresent()) return data.get();
        return null;

    }

    //add sliders to db:
    public Slider add(Slider data) {
        return sliderRepository.save(data);
    }

    //update slider find by id:
    public Slider update(Slider data) throws DataNotFoundException {
        Slider oldData = getById(data.getId());//getId with db
        if (oldData == null){
            throw new DataNotFoundException("data with id"+data.getId()+"not found");
        }
        oldData.setTitle(data.getTitle());//get title by user and set/update filed title in db
        oldData.setDescription(data.getDescription());
        oldData.setEnable(data.isEnable());//boolean
        oldData.setImage(data.getImage());
        oldData.setItemOrder(data.getItemOrder());
        oldData.setLink(data.getLink());
        return sliderRepository.save(oldData);
    }

    //delete slider find by id:
    public boolean deleteById(long id) throws DataNotFoundException {
        Slider oldData = getById(id);//getId with db
        if (oldData == null){
            throw new DataNotFoundException("data with id"+id+"not found");
        }
        sliderRepository.deleteById(id);
        return true;
    }
}
