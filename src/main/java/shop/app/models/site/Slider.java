package shop.app.models.site;

import shop.app.models.BaseEntity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Slider extends BaseEntity {
    private String image;
    private String description;
    @Column(nullable = true ,name = "item_order")
    private int itemOrder;

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

    public int getItemOrder() {
        return itemOrder;
    }

    public void setItemOrder(int itemOrder) {
        this.itemOrder = itemOrder;
    }
}
