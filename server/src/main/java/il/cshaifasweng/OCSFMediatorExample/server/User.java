package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.UserData;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    public String username;
    public String password;
    public String Email;
    public int type;
    public String taz;
    public String creditCard;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "orderedBy")
    public List<Order> orders;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "issuedBy")
    public List<Complaint> complaints;

    @ManyToOne(fetch = FetchType.LAZY)
    public Users userGroup;

    public User()
    {
        orders=new ArrayList<Order>();
        username="";
        password="";
        Email="";
        type=-1;
        taz="";
        creditCard="";
    }
    public User(String username, String password, String email, int type,String cred,String taz, Users userGroup) {
        orders=new ArrayList<Order>();
        this.username = username;
        this.password = password;
        this.Email = email;
        this.type = type;
        this.creditCard=cred;
        this.taz=taz;
        this.userGroup = userGroup;
    }
    public User(UserData userData){
        this.username = userData.username;
        this.password = userData.password;
        this.Email = userData.Email;
        this.type = userData.type;
        this.creditCard=userData.getCreditCard();
        this.taz=userData.getId();
    }

    public UserData getUserData(){
        return new UserData(username, password, Email, type,creditCard,taz,id,(userGroup!=null && userGroup.branch != null)?userGroup.branch.name:"network");
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaz() {
        return taz;
    }

    public void setTaz(String taz) {
        this.taz = taz;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Complaint> getComplaints() {
        return complaints;
    }

    public void setComplaints(List<Complaint> complaints) {
        this.complaints = complaints;
    }

    public Users getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(Users userGroup) {
        this.userGroup = userGroup;
    }
}
