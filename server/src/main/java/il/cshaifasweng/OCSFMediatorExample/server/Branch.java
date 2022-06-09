package il.cshaifasweng.OCSFMediatorExample.server;


import org.hibernate.Hibernate;

import javax.persistence.*;

@Entity
@Table(name = "branch")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne()
    public Users users;

    @OneToOne()
    public Orders orders;

    public String name;

    public int income;


    public Branch() {
    }

    public Branch(Users users, Orders orders, String name) {
        this.users = users;
        this.users.branch = this;
        this.orders = orders;
        this.orders.branch = this;
        this.name = name;
        this.income = 0;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void pullBranchFromDB() {
        users = Hibernate.unproxy(users, Users.class);
        orders = Hibernate.unproxy(orders, Orders.class);

        users.pullUsersFromDB(this);
        orders.pullOrdersFromDB(this);
    }
}
