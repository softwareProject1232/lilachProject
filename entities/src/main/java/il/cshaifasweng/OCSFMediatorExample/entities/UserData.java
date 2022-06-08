package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

public class UserData implements Serializable {
    public String username;
    public String password;
    public String Email;
    public int type;
    public String CreditCard;
    public String id;
    public String branchName;
    public int dbId;
    public int balance;

    public UserData(){
        this.username = "";
        this.password = "";
        this.Email = "";
        this.type = 0;
        this.CreditCard = "";
        this.id = "";
        this.dbId = 0;
        this.branchName = "";
        this.balance = 0;
    }
    public UserData(String username, String password, String email, int type, String creditCard, String id, int dbId, String branchName , int balance) {
        this.username = username;
        this.password = password;
        Email = email;
        this.type = type;
        CreditCard = creditCard;
        this.id = id;
        this.dbId = dbId;
        this.branchName = branchName;
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCreditCard() {
        return CreditCard;
    }

    public void setCreditCard(String creditCard) {
        CreditCard = creditCard;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDbId() {
        return dbId;
    }

    public void setDbId(int dbId) {
        this.dbId = dbId;
    }

    public void setBranchName(String branchName){
    	this.branchName = branchName;
    }

    public String getBranchName(){
    	return branchName;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String toString(){
    	return "UserData [username=" + username + ", password=" + password + ", Email=" + Email + ", type=" + type + ", CreditCard=" + CreditCard + ", id=" + id + ", dbId=" + dbId + ", branchName=" + branchName + "]";
    }
}
