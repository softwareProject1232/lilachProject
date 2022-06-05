package il.cshaifasweng.OCSFMediatorExample.server;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class Orders {

    public List<Order> orders;
    private static Session session;

    public Orders() {
        this.orders = new ArrayList<Order>();
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
    }

    private static SessionFactory getSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

    public void pullOrdersFromDB()
    {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Order> query = builder.createQuery(Order.class);
        query.from(Order.class);
        List<Order> data = session.createQuery(query).getResultList();
        orders.clear();
        orders.addAll(data);
    }

    public void MakeOrder(OrderData orderData){
        session.beginTransaction();
        Order order = new Order(orderData);
        session.save(order);
        session.flush();
        session.getTransaction().commit();
        orders.add(order);
    }
    public void CancelOrder(int id)
    {

    }
}
