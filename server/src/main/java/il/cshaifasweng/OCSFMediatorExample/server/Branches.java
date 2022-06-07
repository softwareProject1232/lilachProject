package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.*;

import java.util.ArrayList;
import java.util.List;

public class Branches {
    public List<Branch> branchList;
    public Users networkUsers;
    public Complaints complaints;
    public List<String> loggedUserNames;

    public Branches() {
        branchList = new ArrayList<Branch>();
        loggedUserNames=new ArrayList<String>();
    }

    public void GenerateValues(){
        App.session.beginTransaction();


        //generate branches
        //  generate users for branches
        Users users1 = new Users();
        User user4 = new User("b1yaron123", "b1yaron123", "em@gmail.com", 1, "12345678", "123123123", users1);
        users1.users.add(user4);

        Users users2 = new Users();
        User user24 = new User("b2yaron123", "b2yaron123", "em@gmail.com", 1, "12345678", "123123123", users2);
        users2.users.add(user24);


        Orders orders1 = new Orders();
        Orders orders2 = new Orders();


        Branch b1 = new Branch(users1, orders1, "haifa");
        Branch b2 = new Branch(users2, orders2, "yarkaaaaaa");


        branchList.add(b1);
        branchList.add(b2);

        Users usersg = new Users();
        User userg1 = new User("gtom123", "gtom123", "em@gmail.com", 4, "12345678", "123123123", usersg);
        User userg2 = new User("gamit123", "gamit123", "em@gmail.com", 3, "12345678", "123123123", usersg);
        User userg3 = new User("gpeleg123", "gpeleg123", "em@gmail.com", 2, "12345678", "123123123", usersg);
        usersg.users.add(userg1);
        usersg.users.add(userg2);
        usersg.users.add(userg3);

        this.networkUsers = usersg;


        Complaints complaints = new Complaints();
        this.complaints = complaints;

        App.session.save(user4);
        App.session.save(users1);
        App.session.save(user24);
        App.session.save(users2);
        App.session.save(orders1);
        App.session.save(orders2);
        App.session.save(b1);
        App.session.save(b2);
        App.session.save(userg1);
        App.session.save(userg2);
        App.session.save(userg3);
        App.session.save(usersg);


        App.session.flush();
        App.session.getTransaction().commit();
    }

    public List<String> GetBranchNameList(){
        List<String> branchNames = new ArrayList<String>();
        for(Branch branch : branchList){
            branchNames.add(branch.name);
        }
        return branchNames;
    }

    public Branch GetBranchByName(String name){
        for(Branch branch : branchList){
            if(branch.getName().equals(name)){
                return branch;
            }
        }
        return null;
    }

    public Users getNetworkUsers() {
        return networkUsers;
    }

    public void setNetworkUsers(Users networkUsers) {
        this.networkUsers = networkUsers;
    }

    public List<Branch> getBranchList() {
        return branchList;
    }

    public void setBranchList(List<Branch> branchList) {
        this.branchList = branchList;
    }

    public Complaints getComplaints() {
        return complaints;
    }

    public void setComplaints(Complaints complaints) {
        this.complaints = complaints;
    }

    public UserData Login(String username, String password, String branchName)
    {
        System.out.format(username);
        for(String s:loggedUserNames)
        {
            System.out.format("names: "+s);
            if(s.equals(username))
            {
                return new UserData("", "", "", 0, "", "", 0, "");
            }
        }
        Branch branch = GetBranchByName(branchName);
        UserData user;
        if(branch != null){
            user = branch.getUsers().Login(username, password);
            if(user.getUsername().length() > 0 && user.getPassword().length() > 0 && user.getEmail().length() > 0 && user.getCreditCard().length() > 0 && user.getId().length() > 0 && user.getType() > 0)
            {
                user.branchName = branchName;
                if(user.type!=0) {
                    System.out.format("added : "+username);
                    loggedUserNames.add(username);
                }
                return user;
            }
        }

        user = networkUsers.Login(username, password);
        user.branchName = branchName;
        if(user.type!=0) {
            System.out.format("added : "+username);
            loggedUserNames.add(username);
        }
        return user;
    }

    public void MakeOrder(OrderData data) {
        Branch branch = GetBranchByName(data.BranchName);
        branch.orders.MakeOrder(data);
    }

    public void editUser(String username, String password, String email, int type,String cred,String taz, int id, String branchName) {
        Branch branch = GetBranchByName(branchName);
        if(branch == null || !branch.users.editUser(username, password, email, type, cred, taz, id))
            networkUsers.editUser(username, password, email, type, cred, taz, id);
    }

    public void addUser(UserData userData) {
        if(userData.type == 1) {
            Branch branch = GetBranchByName(userData.branchName);
            branch.users.addUser(userData);
        }
        else{
            networkUsers.addUser(userData);
        }
    }

    public void CancelOrder(int id, String branchName) {
        Branch branch = GetBranchByName(branchName);
        branch.orders.CancelOrder(id);
    }

    public void addComplaint(ComplaintData complaintData) {
        complaints.addComplaint(complaintData);
    }

    public UserListData GetUserList(String branchName) {
        if(branchName.equals("network")) {
            UserListData userListData = networkUsers.GetUserList();
            //add all users from all branches to the list
            for(Branch branch : branchList) {
                userListData.add(branch.getUsers().GetUserList());
            }
            return userListData;
        }
        else{
            Branch branch = GetBranchByName(branchName);
            return branch.users.GetUserList();
        }
    }

    public OrderListData getUserOrders(int id, String branchName) {
        //if branch name is network, get orders from all branches
        if(branchName.equals("network")) {
            OrderListData orderListData = new OrderListData();
            for(Branch branch : branchList) {
                orderListData.addOrderListData(branch.orders.getUserOrders(id));
            }
            return orderListData;
        }
        else{
            Branch branch = GetBranchByName(branchName);
            return branch.orders.getUserOrders(id);
        }
    }
}
