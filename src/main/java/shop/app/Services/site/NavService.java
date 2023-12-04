package shop.app.Services.site;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import shop.app.ConstantsMessage;
import shop.app.helper.exceptions.DataNotFoundException;
import shop.app.models.site.Nav;
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
    public List<Nav> getAll(Integer pageSize, Integer pageNumber) {
        Pageable pagination = PageRequest.of(pageNumber, pageSize, Sort.by("itemOrder"));
        Page<Nav> all = navRepository.findAll(pagination);
        return all.toList();
    }

    //totalCount pagination:
    public long getAllCount() {
        return navRepository.count();
    }

    //get Nav by id:
    public Nav getById(long id) {
        Optional<Nav> data = navRepository.findById(id);
        if (data.isPresent()) return data.get();
        return null;

    }

    public Nav changeOrder(long id, int direction) throws Exception {
        Nav item = getById(id);
        if (item == null)
            throw new Exception("item not found!");
        switch (direction) {
            case 1:
                //up
                if (item.getItemOrder() <= 1)
                    return item;
                Nav siblingItem = navRepository.findTopByItemOrder(item.getItemOrder() - 1);
                if (siblingItem == null)
                    item.setItemOrder(item.getItemOrder() - 1);
                else {
                    item.setItemOrder(siblingItem.getItemOrder());
                    siblingItem.setItemOrder(item.getItemOrder() + 1);
                    navRepository.save(siblingItem);
                }
                break;
            case 0:
                //down
                Nav siblingItem2 = navRepository.findTopByItemOrder(item.getItemOrder() + 1);
                if (siblingItem2 == null) {
                    Nav lastOrderItem = navRepository.findTopByOrderByItemOrderDesc();
                    if (item.getItemOrder() < lastOrderItem.getItemOrder())
                        item.setItemOrder(item.getItemOrder() + 1);
                } else {
                    item.setItemOrder(siblingItem2.getItemOrder());
                    siblingItem2.setItemOrder(item.getItemOrder() - 1);
                    navRepository.save(siblingItem2);
                }
                break;
        }
        navRepository.save(item);
        return item;
    }


    //add Navs to db:
    public Nav add(Nav data) throws Exception {
        if (data.getTitle() == null || data.getTitle().isEmpty())
            throw new Exception("please enter your title");
        if (data.getLink() == null || data.getLink().isEmpty())
            throw new Exception("please enter your link");

        Nav lastItem = navRepository.findTopByOrderByItemOrderDesc();
        if (lastItem != null || lastItem.getItemOrder() > 0)
            data.setItemOrder(lastItem.getItemOrder() + 1);
        return navRepository.save(data);
    }

    //update Nav find by id:
    public Nav update(Nav data) throws DataNotFoundException {
        Nav oldData = getById(data.getId());//getId with db
        if (oldData == null) {
            throw new DataNotFoundException("data with id" + data.getId() + "not found");
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
        if (oldData == null) {
            throw new DataNotFoundException("data with id" + id + "not found");
        }
        navRepository.deleteById(id);
        return true;
    }
}
