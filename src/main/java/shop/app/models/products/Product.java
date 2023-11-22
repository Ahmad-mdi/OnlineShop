package shop.app.models.products;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    private long price;
    private String image;
    private long visitCount;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductCategory category;
    @ManyToMany
    private List<Color> colors;
    @ManyToMany
    private List<Feature> features;
    @ManyToMany
    private List<Size> sizes;
    private boolean enable;
    private boolean exists;
    private Date addDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(long visitCount) {
        this.visitCount = visitCount;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public List<Color> getColors() {
        return colors;
    }

    public void setColors(List<Color> colors) {
        this.colors = colors;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public List<Size> getSizes() {
        return sizes;
    }

    public void setSizes(List<Size> sizes) {
        this.sizes = sizes;
    }
    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

//    for update relationship tables:
    public void removeColor(long id){
        Color color = getColors().stream().filter(x->x.getId() == id).findFirst().get();
        getColors().remove(color);
    }

    public void addColor(Color color){
        getColors().add(color);
    }
    public void removeFeature(long id){
        Feature feature = getFeatures().stream().filter(x->x.getId() == id).findFirst().get();
        getFeatures().remove(feature);
    }

    public void addFeature(Feature feature){
        getFeatures().add(feature);
    }
    public void removeSize(long id){
        Size size = getSizes().stream().filter(x->x.getId() == id).findFirst().get();
        getSizes().remove(size);
    }

    public void addSize(Size size){
        getSizes().add(size);
    }
}
