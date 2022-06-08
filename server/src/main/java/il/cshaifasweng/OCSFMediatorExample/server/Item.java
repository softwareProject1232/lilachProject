package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.ItemData;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    public String name;
    public int price;
    public String description;
    public String imageUrl;
    public int priceAfterDiscount;

    @ManyToMany(mappedBy = "listItems",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            targetEntity = BasketItem.class)
    public List<BasketItem> BasketsInside;

    public Item(){this.BasketsInside = new ArrayList<BasketItem>();}



    public Item(String name, int price, String description, String imageUrl) {
        this.name=name;
        this.price=price;
        this.description=description;
        this.imageUrl=imageUrl;
        this.priceAfterDiscount=price;
        this.BasketsInside = new ArrayList<BasketItem>();
    }
    public Item(ItemData itemData){
        name = itemData.getName();
        price = itemData.getPrice();
        description = itemData.getDescription();
        imageUrl = itemData.getImageURL();
        this.priceAfterDiscount = price;
        this.BasketsInside = new ArrayList<BasketItem>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<BasketItem> getBasketsInside() {
        return BasketsInside;
    }

    public void setBasketsInside(List<BasketItem> basketsInside) {
        BasketsInside = basketsInside;
    }

    public String toString(){//overriding the toString() method
        return "id: "+id+", name: "+name+",\ndescription: "+description;
    }

    public ItemData GetItemData() {
        return new ItemData(id, name, price, description, imageUrl, priceAfterDiscount);
    }

    public int getPriceAfterDiscount() {
        return priceAfterDiscount;
    }

    public void setPriceAfterDiscount(int priceAfterDiscount) {
        this.priceAfterDiscount = priceAfterDiscount;
    }

    public Item(int id, String name, int price, String description, String imageUrl, int priceAfterDiscount, List<BasketItem> basketsInside) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.priceAfterDiscount = priceAfterDiscount;
        BasketsInside = basketsInside;
    }
}
