package il.cshaifasweng.OCSFMediatorExample.client;
import il.cshaifasweng.OCSFMediatorExample.entities.ReportOrdersByItems;

public class ReceivedReportOrdersByItemsEvent {
    private ReportOrdersByItems list;

    public ReportOrdersByItems getOrders() {
        return list;
    }

    public ReceivedReportOrdersByItemsEvent(ReportOrdersByItems user) {
        this.list = user;
    }
}
