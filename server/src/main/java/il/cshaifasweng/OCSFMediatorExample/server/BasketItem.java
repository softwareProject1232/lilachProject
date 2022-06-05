package il.cshaifasweng.OCSFMediatorExample.server;


import javax.persistence.*;
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

    public BasketItem() {}
    public BasketItem(List<Item> ilist)
    {
        listItems =ilist;
    }

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
}
