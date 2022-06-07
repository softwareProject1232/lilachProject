package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.OrderData;
import il.cshaifasweng.OCSFMediatorExample.entities.OrderListData;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "orderGroup")
    public List<Order> orderList;

    @OneToOne(mappedBy = "orders")
    public Branch branch;

    public Orders() {
        this.orderList = new ArrayList<Order>();
    }

    public void pullOrdersFromDB()
    {
        CriteriaBuilder builder = App.session.getCriteriaBuilder();
        CriteriaQuery<Order> query = builder.createQuery(Order.class);
        query.from(Order.class);
        List<Order> data = App.session.createQuery(query).getResultList();
        orderList.clear();
        orderList.addAll(data);
    }

    public void MakeOrder(OrderData orderData){
        App.session.beginTransaction();
        Order order = new Order(orderData, this);
        App.session.save(order);
        App.session.flush();
        App.session.getTransaction().commit();
        orderList.add(order);
    }
    public void CancelOrder(int id)
    {
        for(Order or: orderList)
        {
            if(or.getId()==id)
            {
                App.session.delete(or);
                orderList.remove(or);
            }
        }
    }

    //get order list data
    public OrderListData GetOrderListData()
    {
        List <OrderData> list= new ArrayList<OrderData>();
        for(Order or: orderList)
        {
            OrderData t=or.GetOrderData();
            list.add(t);
        }
        return new OrderListData(list);
    }
}
