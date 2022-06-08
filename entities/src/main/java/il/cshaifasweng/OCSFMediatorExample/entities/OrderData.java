package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
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
    public java.time.LocalDateTime orderDate;
    public java.time.LocalDateTime supplyDate;
    public int totalPriceAfterDiscount;
    public OrderData(){
        items = new ArrayList<BasketItemData>();
    }

    public OrderData(List<BasketItemData> items, String bracha, UserData orderedBy) {
        this.items = items;
        this.bracha = bracha;
        this.orderedBy = orderedBy;
    }

    public OrderData(List<BasketItemData> items, String bracha, UserData orderedBy, int totalPrice, LocalDateTime orderDate, String branchName, LocalDateTime supplyDate, int totalPriceAfterDiscount) {
        this.items = items;
        this.BranchName = branchName;
        this.bracha = bracha;
        this.orderedBy = orderedBy;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.supplyDate = supplyDate;
        this.totalPriceAfterDiscount = totalPriceAfterDiscount;
    }

    public OrderData(int id, List<BasketItemData> items, String bracha, UserData orderedBy, int totalPrice, LocalDateTime orderDate, String branchName, LocalDateTime supplyDate, int totalPriceAfterDiscount) {
        this.id = id;
        this.items = items;
        this.BranchName = branchName;
        this.bracha = bracha;
        this.orderedBy = orderedBy;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.supplyDate = supplyDate;
        this.totalPriceAfterDiscount = totalPriceAfterDiscount;
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

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDateTime getSupplyDate() {
        return supplyDate;
    }

    public void setSupplyDate(LocalDateTime supplyDate) {
        this.supplyDate = supplyDate;
    }

    public String toString(){
        return "OrderData [id=" + id + ", items=" + items + ", bracha=" + bracha + ", orderedBy=" + orderedBy + ", totalPrice=" + totalPrice + ", date=" + orderDate + "]";
    }
}
