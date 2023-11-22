package shop.app.Services.products;


import org.springframework.stereotype.Service;
import shop.app.helper.exceptions.DataNotFoundException;
import shop.app.models.products.Product;
import shop.app.repositories.products.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    //construct method:
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllByCategory(long categoryId) {
        return  productRepository.findAllByCategory(categoryId);
    }

    public List<Product> searching(String keyword) {
        return  productRepository.findAllByEnableIsTrueAndTitleContainsOrDescriptionContains(keyword);
    }

    public Product getById(long id) {
        Optional<Product> data = productRepository.findById(id);
        if (data.isPresent()) return data.get();
        return null;

    }

    public Product add(Product data) {
        return productRepository.save(data);
    }

    public Product update(Product data) throws DataNotFoundException {
        Product oldData = getById(data.getId());//getId with db
        if (oldData == null){
            throw new DataNotFoundException("data with id"+data.getId()+"not found");
        }
        oldData.setTitle(data.getTitle());//get title by user and set/update filed title in db
        oldData.setDescription(data.getDescription());
        oldData.setEnable(data.isEnable());//boolean
        oldData.setImage(data.getImage());
        oldData.setExists(data.isExists());
        oldData.setPrice(data.getPrice());
        oldData.setColors(data.getColors());
        oldData.setFeatures(data.getFeatures());
//        oldData.setCategory(data.getCategory());
        oldData.setSizes(data.getSizes());
        return productRepository.save(oldData);
    }

    public boolean delete(long id) throws DataNotFoundException {
        Product oldData = getById(id);//getId with db
        if (oldData == null){
            throw new DataNotFoundException("data with id"+id+"not found");
        }
        productRepository.deleteById(id);
        return true;
    }

    //for visitCount:
    public Product increaseVisitCount(long id) throws DataNotFoundException {
        Product oldData = getById(id);//getId with db
        if (oldData == null){
            throw new DataNotFoundException("data with id"+id+"not found");
        }
        oldData.setVisitCount(oldData.getVisitCount()+1);
        return productRepository.save(oldData);
    }
}
