package il.cshaifasweng.OCSFMediatorExample.entities;

import java.util.List;

public class OrderListData {
    public List<OrderData> orders;

    public OrderListData(List<OrderData> orders) {
        this.orders = orders;
    }

    public List<OrderData> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderData> orders) {
        this.orders = orders;
    }
}
