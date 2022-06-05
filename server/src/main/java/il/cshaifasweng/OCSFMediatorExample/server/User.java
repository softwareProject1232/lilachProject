package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.UserData;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
    public User(String username, String password, String email, int type,String cred,String taz) {
        orders=new ArrayList<Order>();
        this.username = username;
        this.password = password;
        this.Email = email;
        this.type = type;
        this.creditCard=cred;
        this.taz=taz;
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
        UserData ret = new UserData(username, password, Email, type,creditCard,taz);
        return ret;
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
}
