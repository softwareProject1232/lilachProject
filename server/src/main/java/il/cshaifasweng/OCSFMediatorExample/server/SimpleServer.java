package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

import java.io.IOException;

public class SimpleServer extends AbstractServer {
	public SimpleServer(int port) {
		super(port);
	}

	private void SafeSendToClient(Object obj, ConnectionToClient client){
		try {
			client.sendToClient(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		System.out.format("received data\n");
		if (UserData.class.equals(msg.getClass())) { // Register and login | <userData>
			App.branches.addUser(((UserData) msg));
			UserData ret = App.branches.Login(((UserData) msg).username, ((UserData) msg).password, ((UserData) msg).branchName);
			SafeSendToClient(ret, client);
		} else if (String.class.equals(msg.getClass())) {
			String msgString = msg.toString();
			System.out.format("    data: " + msgString + "\n");
			if (msgString.startsWith("#warning")) {
				Warning warning = new Warning("Warning from server!");
				SafeSendToClient(warning, client);
			} else if (msgString.startsWith("#update")) {
				String[] args = (msgString.split(":", -1)[1]).split(",", -1);
				switch (args[0]) {
					case "ItemPrice" -> { // update item price #update:ItemPrice,itemId,newPrice
						App.catalog.changePrice(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
						App.catalog.pullItemsFromCatalog();
					}
					case "ItemDescription" -> { // update item description #update:ItemDescription,itemId,newDescription
						App.catalog.changeDescription(Integer.parseInt(args[1]), args[2]);
						App.catalog.pullItemsFromCatalog();
					}
					case "ItemName" -> { // update item name #update:ItemName,itemId,newName
						App.catalog.changeName(Integer.parseInt(args[1]), args[2]);
						App.catalog.pullItemsFromCatalog();
					}
					case "ItemCreate" -> // create new item #update:ItemCreate,itemName,itemDescription,itemPrice,itemImageURL
							App.catalog.addItem(args[1], Integer.parseInt(args[3]), args[2], args[4]);
					case "ItemRemove" -> // remove item #update:ItemRemove,itemId
							App.catalog.removeItem(Integer.parseInt(args[1]));
					case "user" -> // update user #update:user,username,password,email,type,creditCard,taz,id,branchName
							App.branches.editUser(args[1], args[2], args[3], Integer.parseInt(args[4]), args[5], args[6], Integer.parseInt(args[7]), args[8]);
					case "cancelOrder" -> // cancel order #update:cancelOrder,orderId,branchName
							App.branches.CancelOrder(Integer.parseInt(args[1]), args[2]);
				}
			} else if (msgString.startsWith("#request")) {
				String[] args = (msgString.split(":")[1]).split(",");
				switch (args[0]) {
					case "Catalog" -> { // request catalog #request:Catalog
						App.catalog.pullItemsFromCatalog();
						SafeSendToClient(App.catalog.getCatalogData(), client);
					}
					case "Login" -> { // request login #request:Login,username,password,branchName
						UserData ret = App.branches.Login(args[1], args[2], args[3]);
						SafeSendToClient(ret, client);
					}
					case "Branches" -> { // request branches #request:Branches
						BranchNameData branchNames = new BranchNameData(App.branches.GetBranchNameList());
						SafeSendToClient(branchNames, client);
					}
					case "userList" -> { // request user list #request:userList,branchName | use "network" branchName to get all users
						UserListData userListData = App.branches.GetUserList(args[1]);
						SafeSendToClient(userListData, client);
					}
					case "complaints" -> { // request complaints #request:complaints
						ComplaintListData complaintListData = App.branches.getComplaints().GetComplaintListData();
						SafeSendToClient(complaintListData, client);
					}
					// add a case for requesting reports here
					case "report" -> {
						if(args[1].equals("orders")) {
							SafeSendToClient(Report.getOrdersReport(args[2]), client); // request orders report #request:report,orders,branchName
						} else if(args[1].equals("complaints")) {
							SafeSendToClient(Report.reportComplaints(), client); // request complaints report #request:report,complaints
						} else if(args[1].equals("income")) {
							SafeSendToClient(Report.getIncome(args[2]), client); // request income report #request:report,income,branchName
						}
					}
				}
			}
		} else if (OrderData.class.equals(msg.getClass())) { // Make an order | <OrderData>
			App.branches.MakeOrder((OrderData) msg);
		}
		else if (msg.getClass().equals(ComplaintData.class)) { // Make a complaint | <ComplaintData>
			App.branches.addComplaint((ComplaintData) msg);
		}
	}
}
