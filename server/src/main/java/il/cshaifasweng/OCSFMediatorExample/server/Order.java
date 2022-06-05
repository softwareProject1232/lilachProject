package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.BasketItemData;
import il.cshaifasweng.OCSFMediatorExample.entities.ItemData;
import il.cshaifasweng.OCSFMediatorExample.entities.OrderData;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "tableOrder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "orderIn")
    public List<BasketItem> items;

    public String bracha;

    @ManyToOne(fetch = FetchType.LAZY)
    public User orderedBy;
    public Order()
    {
        items=new ArrayList<BasketItem>();
    }
    public Order(OrderData orderData) {
        App.session.beginTransaction();
        Order order = new Order(orderData);

        items=new ArrayList<BasketItem>();
        for(BasketItemData list : orderData.items){
            BasketItem basketItem = new BasketItem();
            for(ItemData itemData : list.listItems){
                basketItem.listItems.add(App.catalog.SearchItemById(itemData.getId()));
            }
            items.add(basketItem);
            App.session.save(basketItem);
        }
        App.session.flush();
        App.session.getTransaction().commit();

        this.bracha = orderData.bracha;
        this.orderedBy = new User(orderData.orderedBy);
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public void setItems(List<BasketItem> items) {
        this.items = items;
    }
    public int getId()
    {
        return id;
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