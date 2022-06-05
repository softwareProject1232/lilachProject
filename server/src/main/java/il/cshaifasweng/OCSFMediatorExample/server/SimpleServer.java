package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

import java.io.IOException;

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
		switch (msg.getClass((UserData) msg)) {
			case UserData.class:
				App.users.addUser(((UserData) msg));
				break;
			case String.class:
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
							App.users.editUser(args[1], args[2], args[3], Integer.parseInt(args[4]), args[5], args[6], Integer.parseInt(args[7]));
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
							UserData ret = App.users.Login(args[1], args[2]);
							SafeSendToClient(ret, client);
							break;
					}
				}
				break;
			case OrderData.class:
				App.orders.MakeOrder((OrderData) msg);
				break;
		}

		/*
		else if (msgString.startsWith("#")) {
		}
		*/

	}

}
