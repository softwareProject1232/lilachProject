package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.OrderData;
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

    public Orders() {
        this.orders = new ArrayList<Order>();
    }

    public void pullOrdersFromDB()
    {
        CriteriaBuilder builder = App.session.getCriteriaBuilder();
        CriteriaQuery<Order> query = builder.createQuery(Order.class);
        query.from(Order.class);
        List<Order> data = App.session.createQuery(query).getResultList();
        orders.clear();
        orders.addAll(data);
    }

    public void MakeOrder(OrderData orderData){
        App.session.beginTransaction();
        Order order = new Order(orderData);
        App.session.save(order);
        App.session.flush();
        App.session.getTransaction().commit();
        orders.add(order);
    }
    public void CancelOrder(int id)
    {
        for(Order or:orders)
        {
            if(or.getId()==id)
            {
                App.session.delete(or);
                orders.remove(or);
            }
        }
    }
}
