package il.cshaifasweng.OCSFMediatorExample.client;


import java.io.IOException;
import java.net.URL;
import java.util5.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.ItemData;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class PrimaryCatalog {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private VBox vbox_main;
	@FXML
	private Button btn;

	@FXML
	private AnchorPane anchor;

	@FXML
	private ScrollPane scroll;
	@FXML
	private GridPane gridCatalog;

	private int i, j;

	@Subscribe
	public void onCatalogRecievedEvent(CatalogRecievedEvent event) {
		System.out.format("Recieved catalog\n");
		App.data = event.getCatalog();
		buildCatalog();
	}

	void buildCatalog(){
		cleanUpCatalog();
		int id;
		String name;
		int price;
		String description;
		VBox pane;
		for (ItemData item: App.data.itemsdata){
			id = item.getId();
			name = item.getName();
			price = item.getPrice();
			description = item.getDescription();
			System.out.format("id: %s\nname: %s\nprice: %s\ndescription: %s\n", id, name, price, description);
			pane = generateItem(id, name, price, description);
			addFlower(pane);
		}
	}
	void cleanUpCatalog(){
		gridCatalog = new GridPane();
		i = 0;
		j = 0;
		int colsCount = 6;
		for(int i_col = 0; i_col<colsCount;i_col++){
			gridCatalog.getColumnConstraints().add(new ColumnConstraints());
		}
		gridCatalog.setAlignment(Pos.CENTER);
		gridCatalog.setHgap(50);
		gridCatalog.setVgap(50);
		gridCatalog.setLayoutX(200);
		gridCatalog.setLayoutY(69);
		anchor.getChildren().clear();
		anchor.getChildren().add(gridCatalog);
	}
	VBox generateItem(int id, String name, int price, String description){
		VBox ret = new VBox();
		Label l_name = new Label(name), l_price = new Label("Price: " + price + "$");
		ret.setAlignment(Pos.CENTER);

		ret.setPrefSize(100, 50);
		ret.setBorder(new Border(new BorderStroke(Color.BLACK,
				BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		ret.setOnMouseClicked(event ->  {
			App.thisitem = id-1;
			System.out.format("Clicked ID: %s\n", id-1);
			if()
			try {
				App.setRoot("UpdateProduct");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		ret.getChildren().addAll(l_name, l_price);
		return ret;
	}
	@FXML
	void addFlower(VBox item) {
		int rows = gridCatalog.getRowCount();
		int cols = gridCatalog.getColumnCount();
		if (i == rows){
			gridCatalog.getRowConstraints().add(new RowConstraints());
			System.out.format("Added Row: %s\n", rows);

		}
		System.out.format("Added Item: (%s, %s)\n", i, j);
		gridCatalog.add(item, j, i, 1, 1);
		j+=1;
		if (j % cols == 0){
			i+=1;
			j=0;
		}
	}

	@FXML
	void initialize() {
		EventBus.getDefault().register(this);
		assert anchor != null : "fx:id=\"anchor\" was not injected: check your FXML file 'PrimaryCatalog.fxml'.";
		assert btn != null : "fx:id=\"btn\" was not injected: check your FXML file 'PrimaryCatalog.fxml'.";
		assert scroll != null : "fx:id=\"scroll\" was not injected: check your FXML file 'PrimaryCatalog.fxml'.";
		assert vbox_main != null : "fx:id=\"vbox_main\" was not injected: check your FXML file 'PrimaryCatalog.fxml'.";


		System.out.format("Sending request\n");
		SimpleClient.getClient().requestCatalog();
		System.out.format("Sent request\n");
	}
}
