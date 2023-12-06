package shop.app.Services.site;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import shop.app.helper.exceptions.DataNotFoundException;
import shop.app.models.site.Nav;
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

    public List<Slider> getAll(Integer pageSize, Integer pageNumber) {
        Pageable pagination = PageRequest.of(pageNumber, pageSize, Sort.by("itemOrder"));
        Page<Slider> all = sliderRepository.findAll(pagination);
        return all.toList();
    }

    //totalCount pagination:
    public long getAllCount() {
        return sliderRepository.count();
    }

    //add sliders to db:
    public Slider add(Slider data) {
        Slider lastItem = sliderRepository.findTopByOrderByItemOrderDesc();
        if (lastItem != null && lastItem.getItemOrder() > 0)
            data.setItemOrder(lastItem.getItemOrder() + 1);
        return sliderRepository.save(data);
    }

    public Slider changeOrder(long id, int direction) throws Exception {
        Slider item = getById(id);
        if (item == null)
            throw new Exception("item not found!");
        switch (direction) {
            case 1:
                //up
                if (item.getItemOrder() <= 1)
                    return item;
                Slider siblingItem = sliderRepository.findTopByItemOrder(item.getItemOrder() - 1);
                if (siblingItem == null)
                    item.setItemOrder(item.getItemOrder() - 1);
                else {
                    item.setItemOrder(siblingItem.getItemOrder());
                    siblingItem.setItemOrder(item.getItemOrder() + 1);
                    sliderRepository.save(siblingItem);
                }
                break;
            case 0:
                //down
                Slider siblingItem2 = sliderRepository.findTopByItemOrder(item.getItemOrder() + 1);
                if (siblingItem2 == null) {
                    Slider lastOrderItem = sliderRepository.findTopByOrderByItemOrderDesc();
                    if (item.getItemOrder() < lastOrderItem.getItemOrder())
                        item.setItemOrder(item.getItemOrder() + 1);
                } else {
                    item.setItemOrder(siblingItem2.getItemOrder());
                    siblingItem2.setItemOrder(item.getItemOrder() - 1);
                    sliderRepository.save(siblingItem2);
                }
                break;
        }
        sliderRepository.save(item);
        return item;
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
