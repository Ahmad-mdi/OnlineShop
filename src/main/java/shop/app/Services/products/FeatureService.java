package shop.app.Services.products;

import org.springframework.stereotype.Service;
import shop.app.helper.exceptions.DataNotFoundException;
import shop.app.models.products.Feature;
import shop.app.repositories.products.FeatureRepository;

import java.util.Optional;

@Service
public class FeatureService {

    private final FeatureRepository featureRepository;

    //construct method:
    public FeatureService(FeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
    }

    public Feature getById(long id) {
        Optional<Feature> data = featureRepository.findById(id);
        if (data.isPresent()) return data.get();
        return null;

    }

    public Feature add(Feature data) {
        return featureRepository.save(data);
    }

    public Feature update(Feature data) throws DataNotFoundException {
        Feature oldData = getById(data.getId());//getId with db
        if (oldData == null){
            throw new DataNotFoundException("data with id"+data.getId()+"not found");
        }
        oldData.setKey(data.getKey());
        oldData.setValue(data.getValue());
        return featureRepository.save(oldData);
    }

    public boolean delete(long id) throws DataNotFoundException {
        Feature oldData = getById(id);//getId with db
        if (oldData == null){
            throw new DataNotFoundException("data with id"+id+"not found");
        }
        featureRepository.deleteById(id);
        return true;
    }
}
