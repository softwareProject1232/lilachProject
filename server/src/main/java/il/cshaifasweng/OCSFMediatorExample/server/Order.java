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
    public java.time.LocalDate date;
    public int price;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "orderIn")
    public List<BasketItem> items;

    public String bracha;

    @ManyToOne(fetch = FetchType.LAZY)
    public Orders orderGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    public User orderedBy;
    public Order()
    {
        items=new ArrayList<BasketItem>();
    }
    public Order(OrderData orderData, Orders orderGroup) {
        App.SafeStartTransaction();
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
        App.SafeCommit();

        this.bracha = orderData.bracha;
        this.orderedBy = App.branches.SearchUserById(orderData.orderedBy.dbId);
        this.orderGroup = orderGroup;
        this.date=orderData.date;
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    public OrderData GetOrderData()
    {
        List<BasketItemData> list= new ArrayList<BasketItemData>();
        for(BasketItem or: items)
        {
            BasketItemData t=or.GetBasketItemData();
            list.add(t);
        }
        return new OrderData(list,bracha,orderedBy.getUserData());
    }
}
