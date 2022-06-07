package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
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
	}

	public void changePrice(int price, ItemData item){
		try {
			client.sendToServer("#update:ItemPrice," + item.getId() + "," + Integer.toString(price)); //"update:price,<item id>,<new price>"
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendComplaint(ComplaintData complaint, ItemData item){
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

	public void addItem(String name, String description,int imagePrice,String imageURL, ItemData item){
		try {
			client.sendToServer("#update:ItemCreate," + name + "," + description+","+Integer.toString(imagePrice)); //"update:price,<item id>,<new price>"
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
	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient("192.168.1.30", 3024);
		}
		return client;
	}

	public void requestOrdersByUser(int dbid, String branch){
		try {
			client.sendToServer("#request:userOrders,"+dbid+","+branch);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
