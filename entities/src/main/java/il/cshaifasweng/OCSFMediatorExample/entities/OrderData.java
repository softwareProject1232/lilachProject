package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.List;

public class OrderData implements Serializable {
    private int id;
    public List<BasketItemData> items;
    public String bracha;
    public UserData orderedBy;
    public OrderData()
    {

    }
}
