package shop.app.Services.site;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import shop.app.helper.exceptions.DataNotFoundException;
import shop.app.models.site.Content;
import shop.app.models.site.Nav;
import shop.app.repositories.site.ContentRepository;
import shop.app.repositories.site.ContentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ContentService {

    private final ContentRepository contentRepository;

    public ContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public Content findByKey(String key) {
        return contentRepository.findFirstByKey(key);//findFirstByKey method in repository
    }

    public Content getById(long id) {
        Optional<Content> data = contentRepository.findById(id);
        if (data.isPresent()) return data.get();
        return null;

    }

    public List<Content> getAll(Integer pageSize, Integer pageNumber) {
        Pageable pagination = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<Content> all = contentRepository.findAll(pagination);
        return all.toList();
    }

    //totalCount pagination:
    public long getAllCount() {
        return contentRepository.count();
    }

    public Content add(Content data) {
        return contentRepository.save(data);
    }

    public Content update(Content data) throws DataNotFoundException {
        Content oldData = getById(data.getId());//getId with db
        if (oldData == null){
            throw new DataNotFoundException("data with id"+data.getId()+"not found");
        }
        oldData.setValue(data.getValue());
        return contentRepository.save(oldData);
    }

    public boolean deleteById(long id) throws DataNotFoundException {
        Content oldData = getById(id);//getId with db
        if (oldData == null){
            throw new DataNotFoundException("data with id"+id+"not found");
        }
        contentRepository.deleteById(id);
        return true;
    }
}
