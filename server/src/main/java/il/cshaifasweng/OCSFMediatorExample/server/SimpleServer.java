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

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		String msgString = msg.toString();
		if (msgString.startsWith("#warning")) {
			Warning warning = new Warning("Warning from server!");
			try {
				client.sendToClient(warning);
				System.out.format("Sent warning to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (msgString.startsWith("#update")) {
			String[] args = (msgString.split(":")[1]).split(",");
			switch (args[0]){
				case "price":
					Catalog catalog = new Catalog();
					catalog.pullItemsFromCatalog();
					catalog.changePrice(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
					break;
			}
		}
		else if (msgString.startsWith("#request")) {
			String[] args = (msgString.split(":")[1]).split(",");
			switch (args[0]){
				case "Catalog":
					Catalog catalog = new Catalog();
					catalog.pullItemsFromCatalog();
					try {
						client.sendToClient(catalog.getCatalogData());
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
			}
		}

		/*
		else if (msgString.startsWith("#")) {
		}
		*/

	}

}
