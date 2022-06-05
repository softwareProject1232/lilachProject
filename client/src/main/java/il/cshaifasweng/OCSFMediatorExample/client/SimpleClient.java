package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.CatalogData;
import il.cshaifasweng.OCSFMediatorExample.entities.ItemData;
import il.cshaifasweng.OCSFMediatorExample.entities.UserData;
import org.greenrobot.eventbus.EventBus;
import javafx.application.Platform;
import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Warning;

import java.io.IOException;

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
	}

	public void changePrice(int price, ItemData item){
		try {
			client.sendToServer("#update:price," + item.getId() + "," + Integer.toString(price)); //"update:price,<item id>,<new price>"
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void requestLogin(String un, String pass){
		try {
			client.sendToServer("#request:Login," + un + "," + pass);
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

	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient("192.168.1.63", 3024);
		}
		return client;
	}

}
