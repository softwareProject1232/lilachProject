package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderListData implements Serializable {
    public List<OrderData> orders;

    public OrderListData(List<OrderData> orders) {
        this.orders = orders;
    }

    public OrderListData() {
        this.orders = new ArrayList<OrderData>();
    }

    public List<OrderData> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderData> orders) {
        this.orders = orders;
    }

    public void addOrderListData(OrderListData orderListData)
    {
        this.orders.addAll(orderListData.getOrders());
    }
}
