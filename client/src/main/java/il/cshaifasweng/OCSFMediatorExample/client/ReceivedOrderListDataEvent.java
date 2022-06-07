package il.cshaifasweng.OCSFMediatorExample.client;
import il.cshaifasweng.OCSFMediatorExample.entities.OrderListData;

public class ReceivedOrderListDataEvent {
    private OrderListData list;

    public OrderListData getOrders() {
        return list;
    }

    public ReceivedOrderListDataEvent(OrderListData user) {
        this.list = user;
    }
}
