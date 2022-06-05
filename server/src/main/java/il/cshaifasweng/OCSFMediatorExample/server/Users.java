package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.UserData;
import org.hibernate.SessionFactory;
import il.cshaifasweng.OCSFMediatorExample.entities.ItemData;
import il.cshaifasweng.OCSFMediatorExample.entities.CatalogData;
import il.cshaifasweng.OCSFMediatorExample.server.Item;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import il.cshaifasweng.OCSFMediatorExample.entities.CatalogData;

import org.hibernate.HibernateException;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(mappedBy = "users")
    public Branch branch;

    public List<User> users;
    public Users(){
        users=new ArrayList<User>();
    }
    public void generateUsers()
    {
        App.session.beginTransaction();
        User tom= new User("tom123", "tom123", "tom@gmail.com", 4,"123456789","1324567");
        User gil= new User("gil123", "gil123", "gil@gmail.com", 1,"123456789","1324567");
        User amit= new User("amit123", "amit123", "amit@gmail.com", 2,"123456789","1324567");
        User peleg= new User("peleg123", "peleg123", "peleg@gmail.com", 3,"123456789","1324567");
        App.session.save(tom);
        App.session.save(gil);
        App.session.save(amit);
        App.session.save(peleg);
        App.session.flush();
        App.session.getTransaction().commit();
    }
    public void pullUsersFromDB()
    {
        CriteriaBuilder builder = App.session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        query.from(User.class);
        List<User> data = App.session.createQuery(query).getResultList();
        users.clear();
        users.addAll(data);
    }

    public User SearchUserById(int id){
        for(User user : users){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }

    public void editUser(String username, String password, String email, int type,String cred,String taz, int id)
    {
        App.session.beginTransaction();
        User temp;
        for (User user :users)
        {
            if(user.getId()==id)
            {
                user.setEmail(email);
                user.setUsername(username);
                user.setPassword(password);
                user.setType(type);
                App.session.save(user);
                break;
            }
        }
        App.session.flush();
        App.session.getTransaction().commit();
    }

    public void addUser(UserData userData)
    {
        App.session.beginTransaction();
        User user = new User(userData.username, userData.password, userData.Email, userData.type, userData.getCreditCard(), userData.getId());
        App.session.save(user);
        App.session.flush();
        App.session.getTransaction().commit();
        users.add(user);
    }

    public UserData Login(String username, String password)
    {
        User temp;
        for (User user :users)
        {
            if(user.getUsername().equals(username) && user.getPassword().equals(password))
            {
                return user.getUserData();
            }
        }

        return new UserData("", "", "", 0, "", "");
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
