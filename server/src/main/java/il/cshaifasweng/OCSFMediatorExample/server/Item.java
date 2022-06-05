package il.cshaifasweng.OCSFMediatorExample.server;
import il.cshaifasweng.OCSFMediatorExample.entities.ItemData;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int price;
    private String description;

    @ManyToMany(mappedBy = "listItems",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            targetEntity = BasketItem.class)
    private List<BasketItem> BasketsInside;

    public Item()
    {}
    public Item(String n, int p, String d) {
        name=n;
        price=p;
        description=d;
    }
    public Item(ItemData itemData){
        name = itemData.getName();
        price = itemData.getPrice();
        description = itemData.getDescription();
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
    public String toString(){//overriding the toString() method
        return "id: "+id+", name: "+name+",\ndescription: "+description;
    }
}
