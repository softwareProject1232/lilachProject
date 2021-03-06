package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.server.Report;
import il.cshaifasweng.OCSFMediatorExample.server.User;
import org.greenrobot.eventbus.EventBus;
import javafx.application.Platform;
import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;

import java.io.IOException;
import java.util.List;

public class SimpleClient extends AbstractClient {
	
	private static SimpleClient client = null;

	private SimpleClient(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		if (msg.getClass().equals(Warning.class)) {
			Platform.runLater(() -> EventBus.getDefault().post(new WarningEvent((Warning) msg)));
		}
		else if (msg.getClass().equals(CatalogData.class)) {
			Platform.runLater(() -> EventBus.getDefault().post(new CatalogRecievedEvent((CatalogData) msg)));
		}
		else if(msg.getClass().equals(UserData.class)) {
			Platform.runLater(() -> EventBus.getDefault().post(new LoginReceivedEvent((UserData) msg)));
		}
		else if(msg.getClass().equals(BranchNameData.class)) {
			Platform.runLater(() -> EventBus.getDefault().post(new BranchesReceivedEvent((BranchNameData) msg)));
		}
		else if(msg.getClass().equals(UserListData.class)) {
			Platform.runLater(() -> EventBus.getDefault().post(new ReceivedUserListEvent((UserListData) msg)));
		}
		else if(msg.getClass().equals(ComplaintListData.class)) {
			Platform.runLater(() -> EventBus.getDefault().post(new ReceivedComplientEvent((ComplaintListData) msg)));
		}
		else if(msg.getClass().equals(OrderListData.class)) {
			Platform.runLater(() -> EventBus.getDefault().post(new ReceivedOrderListDataEvent((OrderListData) msg)));
		}
		else if(msg.getClass().equals(HistogramData.class)){
			Platform.runLater(() -> EventBus.getDefault().post(new ReceivedComplaintsReport((HistogramData) msg)));
		}
		else if(msg.getClass().equals((ReportOrdersByItems.class))){
			Platform.runLater(() -> EventBus.getDefault().post(new ReceivedReportOrdersByItemsEvent((ReportOrdersByItems) msg)));
		}
		else if(msg.getClass().equals(IncomeHistogramData.class)){
			Platform.runLater(() -> EventBus.getDefault().post(new ReceivedIncomeReport((IncomeHistogramData) msg)));
		}
		else if(msg.getClass().equals(NewUserBalanceData.class)){
			Platform.runLater(() -> EventBus.getDefault().post(new RecievedNewUserBalanceData((NewUserBalanceData) msg)));
		}
	}
	public void requestComplaintsReport(int days){
		try {
			client.sendToServer("#request:report,complaints," + days);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void changePrice(int price, ItemData item){
		try {
			client.sendToServer("#update:ItemPrice," + item.getId() + "," + Integer.toString(price)); //"update:price,<item id>,<new price>"
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void changePriceAfterDiscount(int priceAfterDiscount, ItemData item){
		try {
			client.sendToServer("#update:ItemDiscount," + item.getId() + "," + Integer.toString(priceAfterDiscount)); //"update:price,<item id>,<new price>"
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void requestCancelOrder(int id){
		try {
			client.sendToServer("#update:cancelOrder," + id); //"update:price,<item id>,<new price>"
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendComplaint(ComplaintData complaint){
		try {
			client.sendToServer(complaint); //"update:price,<item id>,<new price>"
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void changeDescription(String description, ItemData item){
		try {
			client.sendToServer("#update:ItemDescription," + item.getId() + "," + description); //"update:price,<item id>,<new price>"
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void changeName(String name, ItemData item){
		try {
			client.sendToServer("#update:ItemName," + item.getId() + "," + name); //"update:price,<item id>,<new price>"
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void changeBalance(String bal, UserData usr){
		try {
			client.sendToServer("#update:editBalance,"+usr.getDbId()+"," + bal); //"update:price,<item id>,<new price>"
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addItem(String name, String description,int imagePrice,String imageURL, ItemData item){
		try {
			client.sendToServer("#update:ItemCreate," + name + "," + description+","+Integer.toString(imagePrice)+","+imageURL); //"update:price,<item id>,<new price>"
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void removeItem(ItemData item){
		try {
			client.sendToServer("#update:ItemRemove," + item.getId()); //"update:price,<item id>,<new price>"
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void requestLogin(String un, String pass, String branch){
		try {
			client.sendToServer("#request:Login," + un + "," + pass + "," + branch); //"request:Login,<username>,<password>,<branch>"
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void  MakeOrder(OrderData order){
		try {
			client.sendToServer(order);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void updateUser(UserData user){
		try {
			client.sendToServer("#update:user," + user.getUsername() + "," + user.getPassword() + "," + user.getEmail() + "," +
					user.getType() + "," + user.getCreditCard() + "," + user.getId() + "," + user.getDbId() + "," + user.getBranchName()); //"update:user,<username>,<password>,<email>,<type>,<credit card>,<id>,<branch>"
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void requestRegister(UserData user){
		try{
			client.sendToServer(user);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	public void requestBranches(){
		try {
			client.sendToServer("#request:Branches"); //"request:Branches"
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void requestCatalog(){
		try {
			client.sendToServer("#request:Catalog");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void requestUsers(String branch){
		try {
			client.sendToServer("#request:userList," + branch);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void requestComplaints(String branch){
		try {
			client.sendToServer("#request:complaints,"+branch);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void requestOrdersByUser(int dbid, String branch){
		try {
			client.sendToServer("#request:userOrders,"+dbid+","+branch);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void requestLogout(){
		try {
			if(App.userData != null) {
				client.sendToServer("#request:Logout," + App.userData.dbId);
				App.userData = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void requestreomvecmplaint(int id){
		try {
			client.sendToServer("#update:removeComplaint,"+id);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void requestComplaintsReport(){
		try {
			client.sendToServer("#request:report,complaints");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void requestOrdersReport(String branch, int days){
		try {
			client.sendToServer("#request:report,orders," + branch + "," + days); // request orders report #request:report,orders,branchName, int days to look in the past
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void requestIncomeReport(){
		try {
			client.sendToServer("#request:report,income"); // request orders report #request:report,orders,branchName, int days to look in the past
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient(ip_c, port_c);
		}
		return client;
	}
	public static String ip_c;
	public static int port_c;

	public static SimpleClient getClient(String ip,int port) {
		if (client == null) {
			client = new SimpleClient(ip, port);
			ip_c= ip;
			port_c=port;

		}
		return client;
	}
}
