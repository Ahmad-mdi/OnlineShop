package shop.app.Services.site;

import org.springframework.stereotype.Service;
import shop.app.helper.exceptions.DataNotFoundException;
import shop.app.helper.ui.ServiceResponse;
import shop.app.models.site.Blog;
import shop.app.repositories.site.BlogRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public List<Blog> search(String keyword) {
        return blogRepository.findAllByTitleContainsOrDescriptionContains(keyword);//findFirstByKey method in repository
    }

    public Blog getById(long id) {
        Optional<Blog> data = blogRepository.findById(id);
        if (data.isPresent()) return data.get();
        return null;

    }

    public Blog add(Blog data) throws Exception {
        if (data.getTitle() == null || data.getTitle().equals(""))
            throw new Exception("please fill title field");

        return blogRepository.save(data);
    }

    public Blog update(Blog data) throws DataNotFoundException {
        Blog oldData = getById(data.getId());//getId with db
        if (oldData == null){
            throw new DataNotFoundException("data with id"+data.getId()+"not found");
        }
        oldData.setTitle(data.getTitle());//get title by user and set/update filed title in db
        oldData.setDescription(data.getDescription());
        oldData.setImage(data.getImage());
        oldData.setStatus(data.getStatus());
        oldData.setSubtitle(data.getSubtitle());
        return blogRepository.save(oldData);
    }

    public boolean deleteById(long id) throws DataNotFoundException {
        Blog oldData = getById(id);//getId with db
        if (oldData == null){
            throw new DataNotFoundException("data with id"+id+"not found");
        }
        blogRepository.deleteById(id);
        return true;
    }

    //for visitCount:
    public Blog increaseVisitCount(long id) throws DataNotFoundException {
        Blog oldData = getById(id);//getId with db
        if (oldData == null){
            throw new DataNotFoundException("data with id"+id+"not found");
        }
        oldData.setVisitCount(oldData.getVisitCount()+1);
        return blogRepository.save(oldData);
    }
}
