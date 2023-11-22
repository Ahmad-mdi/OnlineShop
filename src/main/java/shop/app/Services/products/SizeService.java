package shop.app.Services.products;

import org.springframework.stereotype.Service;
import shop.app.helper.exceptions.DataNotFoundException;
import shop.app.models.products.Size;
import shop.app.repositories.products.SizeRepository;

import java.util.Optional;

@Service
public class SizeService {

    private final SizeRepository sizeRepository;

    //construct method:
    public SizeService(SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }

    public Size getById(long id) {
        Optional<Size> data = sizeRepository.findById(id);
        if (data.isPresent()) return data.get();
        return null;

    }

    public Size add(Size data) {
        return sizeRepository.save(data);
    }

    public Size update(Size data) throws DataNotFoundException {
        Size oldData = getById(data.getId());//getId with db
        if (oldData == null){
            throw new DataNotFoundException("data with id"+data.getId()+"not found");
        }
        oldData.setTitle(data.getTitle());
        oldData.setDescription(data.getDescription());
        return sizeRepository.save(oldData);
    }

    public boolean delete(long id) throws DataNotFoundException {
        Size oldData = getById(id);//getId with db
        if (oldData == null){
            throw new DataNotFoundException("data with id"+id+"not found");
        }
        sizeRepository.deleteById(id);
        return true;
    }
}
