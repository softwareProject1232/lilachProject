package il.cshaifasweng.OCSFMediatorExample.client;

import org.greenrobot.eventbus.EventBus;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Warning;

import javax.xml.catalog.Catalog;

public class SimpleClient extends AbstractClient {
	
	private static SimpleClient client = null;

	private SimpleClient(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		if (msg.getClass().equals(Warning.class)) {
			EventBus.getDefault().post(new WarningEvent((Warning) msg));
		}
		else if (msg.getClass().equals(Catalog.class)) {
			EventBus.getDefault().post(new CatalogRecievedEvent((Catalog) msg));
		}
	}

	public void changePrice(int price, Item item){
		client.sendToServer("#update:price," + item.getId() + "," + Integer.toString(price)); //"update:price,<item id>,<new price>"
	}

	public void requestCatalog(){
		client.sendToServer("#request:Catalog");
	}

	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient("localhost", 3000);
		}
		return client;
	}

}
