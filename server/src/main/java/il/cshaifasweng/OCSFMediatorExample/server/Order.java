package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.ItemData;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    public List<List<Item>> items;
    public String bracha;

    @ManyToOne(fetch = FetchType.LAZY)
    public User orderedBy;
    public Order()
    {
        items=new ArrayList<List<Item>>();
    }
    public Order(OrderData orderData) {
        items=new ArrayList<List<Item>>();
        for(List<ItemData> list : orderData){
            List<Item> itemList = new ArrayList<Item>();
            for(ItemData itemData : list){
                itemList.add(new Item(itemData));
            }
            items.add(itemList);
        }
        this.bracha = orderData.bracha;
        this.orderedBy = orderData.orderedBy;
    }

    public List<List<Item>> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getBracha() {
        return bracha;
    }

    public void setBracha(String bracha) {
        this.bracha = bracha;
    }

    public User getOrderedBy() {
        return orderedBy;
    }

    public void setOrderedBy(User orderedBy) {
        this.orderedBy = orderedBy;
    }
}
