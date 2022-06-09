package il.cshaifasweng.OCSFMediatorExample.server;


import il.cshaifasweng.OCSFMediatorExample.entities.BasketItemData;
import il.cshaifasweng.OCSFMediatorExample.entities.ItemData;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "BasketItem")
public class BasketItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            targetEntity = Item.class)
    public List<Item> listItems;

    @ManyToOne(fetch = FetchType.LAZY)
    public Order orderIn;

    public BasketItem(Order orderIn) {this.listItems = new ArrayList<Item>(); this.orderIn = orderIn;}
    public BasketItem(List<Item> ilist)
    {
        listItems =ilist;
    }
    public BasketItem(){this.listItems = new ArrayList<Item>();}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Item> getListItems() {
        return listItems;
    }

    public void setListItems(List<Item> listItems) {
        this.listItems = listItems;
    }

    public BasketItemData GetBasketItemData() {
        BasketItemData basketItemData = new BasketItemData();
        basketItemData.listItems = new ArrayList<ItemData>();
        for (Item item : listItems) {
            basketItemData.listItems.add(item.GetItemData());
        }
        return basketItemData;
    }

    public void pullBasketItemFromDB() {
        orderIn = Hibernate.unproxy(orderIn, Order.class);
        for (Item item : listItems) {
            item = Hibernate.unproxy(item, Item.class);
        }
    }
}
