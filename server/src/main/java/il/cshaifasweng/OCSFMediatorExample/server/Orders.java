package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.NewUserBalanceData;
import il.cshaifasweng.OCSFMediatorExample.entities.OrderData;
import il.cshaifasweng.OCSFMediatorExample.entities.OrderListData;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.time.LocalDateTime;
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

    public NewUserBalanceData MakeOrder(OrderData orderData){
        App.SafeStartTransaction();
        Order order = new Order(orderData, this);
        orderList.add(order);
        User user = App.branches.SearchUserById(orderData.orderedBy.dbId);
        user.DeductPayment(order.price);
        branch.income += order.price;
        App.session.save(order);
        App.session.saveOrUpdate(this);
        App.session.flush();
        App.SafeCommit();
        return new NewUserBalanceData(user.balance);
    }
    public int CancelOrder(int id)
    {
        int ret = -1;
        for(Order or: orderList)
        {
            if(or.getId()==id)
            {
                App.SafeStartTransaction();
                User user = App.branches.SearchUserById(or.orderedBy.getId());
                if(LocalDateTime.now().isBefore(or.supplyDate.minusHours(3)))
                {
                    user.GetRefund(or, 1);
                    branch.income -= or.price;
                    ret = user.balance;
                }
                else if(LocalDateTime.now().isBefore(or.supplyDate.minusHours(1)))
                {
                    user.GetRefund(or, 0.5);
                    branch.income -= or.price/2;
                    ret = user.balance;
                }
                ret = user.balance;
                orderList.remove(or);
                App.session.delete(or);
                App.session.saveOrUpdate(branch);
                App.session.flush();
                App.SafeCommit();
            }
        }
        return ret;
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

    public OrderListData getUserOrders(int id) {
        List<OrderData> list = new ArrayList<OrderData>();
        for (Order or : orderList) {
            if (or.getOrderedBy().getId() == id) {
                OrderData t = or.GetOrderData();
                list.add(t);
            }
        }
        return new OrderListData(list);
    }
}
