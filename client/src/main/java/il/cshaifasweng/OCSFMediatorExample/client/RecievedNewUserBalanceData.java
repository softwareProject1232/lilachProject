package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.NewUserBalanceData;

public class RecievedNewUserBalanceData {
    public NewUserBalanceData newUserBalanceData;

    public RecievedNewUserBalanceData(NewUserBalanceData newUserBalanceData) {
        this.newUserBalanceData = newUserBalanceData;
    }

    public NewUserBalanceData getNewUserBalanceData() {
        return newUserBalanceData;
    }

    public void setNewUserBalanceData(NewUserBalanceData newUserBalanceData) {
        this.newUserBalanceData = newUserBalanceData;
    }
}
