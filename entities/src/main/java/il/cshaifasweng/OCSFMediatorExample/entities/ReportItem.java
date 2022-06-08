package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

public class ReportItem implements Serializable{
    public ItemData itemData;
    public int timesOrdered;

    public ReportItem(ItemData itemData, int timesOrdered) {
        this.itemData = itemData;
        this.timesOrdered = timesOrdered;
    }

    public ItemData getItemData() {
        return itemData;
    }

    public void setItemData(ItemData itemData) {
        this.itemData = itemData;
    }

    public int getTimesOrdered() {
        return timesOrdered;
    }

    public void setTimesOrdered(int timesOrdered) {
        this.timesOrdered = timesOrdered;
    }
}
