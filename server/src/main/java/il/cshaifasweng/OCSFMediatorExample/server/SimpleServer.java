package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.BranchNameData;
import il.cshaifasweng.OCSFMediatorExample.entities.OrderData;
import il.cshaifasweng.OCSFMediatorExample.entities.UserData;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

import java.io.IOException;
import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.entities.Warning;

import il.cshaifasweng.OCSFMediatorExample.server.Catalog;

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
		if (UserData.class.equals(msg.getClass())) {
			App.branches.addUser(((UserData) msg));
		} else if (String.class.equals(msg.getClass())) {
			String msgString = msg.toString();
			if (msgString.startsWith("#warning")) {
				Warning warning = new Warning("Warning from server!");
				SafeSendToClient(warning, client);
			} else if (msgString.startsWith("#update")) {
				String[] args = (msgString.split(":")[1]).split(",");
				switch (args[0]) {
					case "price":
						App.catalog.pullItemsFromCatalog();
						App.catalog.changePrice(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
						break;
					case "user":
						App.branches.editUser(args[1], args[2], args[3], Integer.parseInt(args[4]), args[5], args[6], Integer.parseInt(args[7]), args[8]);
						break;

					case "cancelOrder":

						break;
				}
			} else if (msgString.startsWith("#request")) {
				String[] args = (msgString.split(":")[1]).split(",");
				switch (args[0]) {
					case "Catalog":
						App.catalog.pullItemsFromCatalog();
						SafeSendToClient(App.catalog.getCatalogData(), client);
						break;
					case "Login":
						UserData ret = App.branches.Login(args[1], args[2], args[3]);
						SafeSendToClient(ret, client);
						break;
					case "Branches":
						BranchNameData branchNames =new BranchNameData(App.branches.GetBranchNameList());
						SafeSendToClient(branchNames, client);
						break;
				}
			}
		} else if (OrderData.class.equals(msg.getClass())) {
			App.branches.MakeOrder((OrderData) msg);
		}

		/*
		else if (msgString.startsWith("#")) {
		}
		*/

	}

}
