package il.cshaifasweng.OCSFMediatorExample.entities;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

public class ItemData implements Serializable{
    private int id;
    private String name;
    private int price;
    private String description;
    public List<ItemData> zerList;
    public int isZer;
    public ItemData(int i,String n,int p,String d)
    {
        id=i;
        name=n;
        price=p;
        description=d;
        isZer=0;
    }
    public ItemData(int i,String n,int p,String d,int isZerin)
    {
        id=i;
        name=n;
        price=p;
        description=d;
        isZer=isZerin;
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
}