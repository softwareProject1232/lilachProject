package il.cshaifasweng.OCSFMediatorExample.entities;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

public class ItemData implements Serializable{
    private int id;
    private String name;
    private int price;
    private String description;
    private String imageURL;
    public List<ItemData> zerList;
    public int isZer;

    public ItemData(int i,String n,int p,String d, String imageURL)
    {
        id=i;
        name=n;
        price=p;
        description=d;
        isZer=0;
        this.imageURL=imageURL;
    }
    public ItemData(int i,String n,int p,String d, String imageURL,int isZerin)
    {
        id=i;
        name=n;
        price=p;
        description=d;
        isZer=isZerin;
        this.imageURL=imageURL;
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public List<ItemData> getZerList() {
        return zerList;
    }

    public void setZerList(List<ItemData> zerList) {
        this.zerList = zerList;
    }

    public int getIsZer() {
        return isZer;
    }

    public void setIsZer(int isZer) {
        this.isZer = isZer;
    }
}