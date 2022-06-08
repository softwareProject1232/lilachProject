package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

public class NewUserBalanceData implements Serializable {
    public int balance;

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public NewUserBalanceData(int balance) {
        this.balance = balance;
    }
}
