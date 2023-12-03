package shop.app.Services.site;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import shop.app.helper.exceptions.DataNotFoundException;
import shop.app.models.products.Product;
import shop.app.models.site.Nav;
import shop.app.repositories.site.NavRepository;
import shop.app.repositories.site.NavRepository;

import java.util.List;
import java.util.Optional;

@Service
public class NavService {

    private final NavRepository navRepository;

    //construct method:

    public NavService(NavRepository navRepository) {
        this.navRepository = navRepository;
    }


    //get all Navs sort by itemOrder:
    public List<Nav> findAllOrderByItemOrder() {
        return navRepository.findAllByEnableIsTrue(Sort.by("itemOrder"));
    }
//getAll by pagination
    public List<Nav> getAll(Integer pageSize,Integer pageNumber){
        Pageable pagination = PageRequest.of(pageNumber,pageSize,Sort.by("itemOrder"));
        Page<Nav> all = navRepository.findAll(pagination);
        return all.toList();
    }

    //get Nav by id:
    public Nav getById(long id) {
        Optional<Nav> data = navRepository.findById(id);
        if (data.isPresent()) return data.get();
        return null;

    }

    //add Navs to db:
    public Nav add(Nav data) {
        return navRepository.save(data);
    }

    //update Nav find by id:
    public Nav update(Nav data) throws DataNotFoundException {
        Nav oldData = getById(data.getId());//getId with db
        if (oldData == null){
            throw new DataNotFoundException("data with id"+data.getId()+"not found");
        }
        oldData.setTitle(data.getTitle());//get title by user and set/update filed title in db
        oldData.setEnable(data.isEnable());//boolean
        oldData.setItemOrder(data.getItemOrder());
        oldData.setLink(data.getLink());
        return navRepository.save(oldData);
    }

    //delete Nav find by id:
    public boolean deleteById(long id) throws DataNotFoundException {
        Nav oldData = getById(id);//getId with db
        if (oldData == null){
            throw new DataNotFoundException("data with id"+id+"not found");
        }
        navRepository.deleteById(id);
        return true;
    }
}
