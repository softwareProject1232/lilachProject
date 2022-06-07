package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderData implements Serializable {
    public String BranchName;
    private int id;
    public List<BasketItemData> items;
    public String bracha;
    public UserData orderedBy;
    public int totalPrice;
    public java.time.LocalDate date;
    public OrderData(){
        items = new ArrayList<BasketItemData>();
    }

    public OrderData(List<BasketItemData> items, String bracha, UserData orderedBy) {
        this.items = items;
        this.bracha = bracha;
        this.orderedBy = orderedBy;
    }
}
