package shop.app.models.site;

import shop.app.models.BaseEntity.BaseEntity;

import javax.persistence.Entity;

@Entity
public class Slider extends BaseEntity {
    private String image;
    private String description;
    private Integer order;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
