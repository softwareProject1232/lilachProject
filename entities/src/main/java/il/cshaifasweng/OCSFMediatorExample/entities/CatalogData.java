package il.cshaifasweng.OCSFMediatorExample.entities;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.time.LocalTime;
public class CatalogData implements Serializable{
    public List<ItemData> itemsdata;
    public List<CartList> cartList;
    public CatalogData()
    {
        itemsdata=new ArrayList<ItemData>();
    }

}
