package shop.app.Services.products;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import shop.app.helper.exceptions.DataNotFoundException;
import shop.app.models.products.Color;
import shop.app.repositories.products.ColorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ColorService {

    private final ColorRepository colorRepository;

    //construct method:
    public ColorService(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    public Color getById(long id) {
        Optional<Color> data = colorRepository.findById(id);
        if (data.isPresent()) return data.get();
        return null;

    }

    public Color add(Color data) {
        return colorRepository.save(data);
    }

    public Color update(Color data) throws DataNotFoundException {
        Color oldData = getById(data.getId());//getId with db
        if (oldData == null){
            throw new DataNotFoundException("data with id"+data.getId()+"not found");
        }
        oldData.setName(data.getName());
        oldData.setValue(data.getValue());
        return colorRepository.save(oldData);
    }

    public boolean deleteById(long id) throws DataNotFoundException {
        Color oldData = getById(id);//getId with db
        if (oldData == null){
            throw new DataNotFoundException("data with id"+id+"not found");
        }
        colorRepository.deleteById(id);
        return true;
    }
}
