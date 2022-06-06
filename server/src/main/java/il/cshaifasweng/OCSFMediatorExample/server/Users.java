package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.UserData;
import il.cshaifasweng.OCSFMediatorExample.entities.UserListData;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "userGroup")
    public List<User> users;


    public Users(){
        users=new ArrayList<User>();
    }
    public void generateUsers()
    {
        App.session.beginTransaction();
        User tom= new User("tom123", "tom123", "tom@gmail.com", 4,"123456789","1324567", this);
        User gil= new User("gil123", "gil123", "gil@gmail.com", 1,"123456789","1324567", this);
        User amit= new User("amit123", "amit123", "amit@gmail.com", 2,"123456789","1324567", this);
        User peleg= new User("peleg123", "peleg123", "peleg@gmail.com", 3,"123456789","1324567", this);
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

    public boolean editUser(String username, String password, String email, int type,String cred,String taz, int id)
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
                App.session.flush();
                App.session.getTransaction().commit();
                return true;
            }
        }
        return false;
    }

    public void addUser(UserData userData)
    {
        App.session.beginTransaction();
        User user = new User(userData.username, userData.password, userData.Email, userData.type, userData.getCreditCard(), userData.getId(), this);
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

        return new UserData("", "", "", 0, "", "", 0, "");
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

    public UserListData GetUserList() {
        UserListData userListData = new UserListData();
        for (User user : users) {
            userListData.users.add(user.getUserData());
        }
        return userListData;
    }
}
