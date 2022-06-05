package il.cshaifasweng.OCSFMediatorExample.server;

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

import javax.persistence.Query;
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

public class Users {
    public List<User> users;
    public Users(){
        users=new ArrayList<User>();
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();

    }
    private static Session session;

    private static SessionFactory getSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        return configuration.buildSessionFactory(serviceRegistry);
    }
    public void pullUsersFromDB()
    {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        query.from(User.class);
        List<User> data = session.createQuery(query).getResultList();
        users.clear();
        users.addAll(data);
    }

    public void editUser(String username, String password, String email, int type,String cred,String taz, int id)
    {
        session.beginTransaction();
        User temp;
        for (User user :users)
        {
            if(user.getId()==id)
            {
                user.setEmail(email);
                user.setUsername(username);
                user.setPassword(password);
                user.setType(type);
                session.save(user);
                break;
            }
        }
        session.flush();
        session.getTransaction().commit();
    }

    public void addUser(UserData userData)
    {
        session.beginTransaction();
        User user = new User(userData.username, userData.password, userData.email, userData.type, userData.cred, userData.taz);
        session.save(user);
        session.flush();
        session.getTransaction().commit();
        users.add(user);
    }

    public int Login(String username, String password)
    {
        User temp;
        for (User user :users)
        {
            if(user.getUsername() == username && user.getPassword() == password)
            {
                return user.getType();
            }
        }

        return 0;
    }

}
