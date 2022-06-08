package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.List;

public class ReportOrdersByItems implements Serializable {
    public List<ReportItem> itemsReport;

    public ReportOrdersByItems(List<ReportItem> itemsReport) {
        this.itemsReport = itemsReport;
    }

    public List<ReportItem> getItemsReport() {
        return itemsReport;
    }

    public void setItemsReport(List<ReportItem> itemsReport) {
        this.itemsReport = itemsReport;
    }
}
