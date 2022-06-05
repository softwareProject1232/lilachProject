package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BasketItemData implements Serializable {
    public List<ItemData> listItems;

    public BasketItemData(List<ItemData> listItems) {
        this.listItems = listItems;
    }
    public BasketItemData()
    {
        this.listItems =new ArrayList<ItemData>();
    }

    public List<ItemData> getListItems() {
        return listItems;
    }

    public void setListItems(List<ItemData> listItems) {
        this.listItems = listItems;
    }
}
