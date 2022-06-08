package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderData implements Serializable {
    public String BranchName;
    private int id;
    public List<BasketItemData> items;
    public String bracha;
    public UserData orderedBy;
    public int totalPrice;
    public int totalPriceAfterDiscount;
    public java.time.LocalDateTime date;
    public OrderData(){
        items = new ArrayList<BasketItemData>();
    }

    public OrderData(List<BasketItemData> items, String bracha, UserData orderedBy) {
        this.items = items;
        this.bracha = bracha;
        this.orderedBy = orderedBy;
    }

    public OrderData(List<BasketItemData> items, String bracha, UserData orderedBy, int totalPrice, LocalDateTime date, String branchName) {
        this.items = items;
        this.BranchName = branchName;
        this.bracha = bracha;
        this.orderedBy = orderedBy;
        this.totalPrice = totalPrice;
        this.date = date;
    }

    public String getBranchName() {
        return BranchName;
    }

    public void setBranchName(String branchName) {
        BranchName = branchName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<BasketItemData> getItems() {
        return items;
    }

    public void setItems(List<BasketItemData> items) {
        this.items = items;
    }

    public String getBracha() {
        return bracha;
    }

    public void setBracha(String bracha) {
        this.bracha = bracha;
    }

    public UserData getOrderedBy() {
        return orderedBy;
    }

    public void setOrderedBy(UserData orderedBy) {
        this.orderedBy = orderedBy;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String toString(){
        return "OrderData [id=" + id + ", items=" + items + ", bracha=" + bracha + ", orderedBy=" + orderedBy + ", totalPrice=" + totalPrice + ", date=" + date + "]";
    }
}
