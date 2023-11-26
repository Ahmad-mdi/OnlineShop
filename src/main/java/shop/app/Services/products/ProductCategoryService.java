package shop.app.Services.products;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import shop.app.helper.exceptions.DataNotFoundException;
import shop.app.models.products.ProductCategory;
import shop.app.repositories.products.ProductCategoryRepository;


import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    //construct method:
    public ProductCategoryService(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }


    public List<ProductCategory> findAllOrderByItemOrder() {
        return  productCategoryRepository.findAllByEnableIsTrue(Sort.by("id"));
    }

    public ProductCategory getById(long id) {
        Optional<ProductCategory> data = productCategoryRepository.findById(id);
        if (data.isPresent()) return data.get();
        return null;

    }

    public ProductCategory add(ProductCategory data) {
        return productCategoryRepository.save(data);
    }

    public ProductCategory update(ProductCategory data) throws DataNotFoundException {
        ProductCategory oldData = getById(data.getId());//getId with db
        if (oldData == null){
            throw new DataNotFoundException("data with id"+data.getId()+"not found");
        }
        oldData.setTitle(data.getTitle());//get title by user and set/update filed title in db
        oldData.setDescription(data.getDescription());
        oldData.setEnable(data.isEnable());//boolean
        oldData.setImage(data.getImage());
        return productCategoryRepository.save(oldData);
    }

    public boolean deleteById(long id) throws DataNotFoundException {
        ProductCategory oldData = getById(id);//getId with db
        if (oldData == null){
            throw new DataNotFoundException("data with id"+id+"not found");
        }
        productCategoryRepository.deleteById(id);
        return true;
    }
}
