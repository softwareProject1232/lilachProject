package il.cshaifasweng.OCSFMediatorExample.client;


import java.util.ArrayList;
import java.util.List;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.ItemData;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import il.cshaifasweng.OCSFMediatorExample.entities.CatalogData;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class PrimaryController {

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

	private int i = 0;
	private int j = 0;

	private CatalogData data;
	private Pane templateItem;

	@Subscribe
	public void onCatalogRecievedEvent(CatalogRecievedEvent event) {
		data = event.getCatalog();
		buildCatalog();
	}

	void buildCatalog(){
		cleanUpCatalog();
		int id;
		String name;
		int price;
		String description;
		for (ItemData item: data.itemsdata){
			id = item.getId();
			name = item.getName();
			price = item.getPrice();
			description = item.getDescription();

		}
	}
	void cleanUpCatalog(){
		gridCatalog = new GridPane();
	}
	Pane generateItem(int id, String name, int price, String description){
		Pane ret = new Pane();
		Label l_id = new Label("ID: " + id);
		Label l_name = new Label("Name: " + name);
		Label l_price = new Label("Price: " + price);
		Label l_description = new Label("Description: " + description);

		ret.setPrefSize(100, 50);
		ret.setBorder(new Border(new BorderStroke(Color.BLACK,
				BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		// ret.setOnMouseClicked(); // Integrate with description window
		ret.getChildren().addAll(l_id, l_name, l_price, l_description);
		return ret;
	}
	@FXML
	void addFlower(Pane item) {
		int rows = gridCatalog.getRowCount();
		int cols = gridCatalog.getColumnCount();
		if (i == rows){
			gridCatalog.getRowConstraints().add(new RowConstraints());
			i+=1;
			System.out.format("Rows: %s\n", rows);

		}
		Button temp = new Button("1");
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
		assert anchor != null : "fx:id=\"anchor\" was not injected: check your FXML file 'primary.fxml'.";
		assert btn != null : "fx:id=\"btn\" was not injected: check your FXML file 'primary.fxml'.";
		assert scroll != null : "fx:id=\"scroll\" was not injected: check your FXML file 'primary.fxml'.";
		assert vbox_main != null : "fx:id=\"vbox_main\" was not injected: check your FXML file 'primary.fxml'.";
		int colsCount = 6;
		for(int i = 0; i<colsCount;i++){
			gridCatalog.getColumnConstraints().add(new ColumnConstraints());
		}
		gridCatalog.setAlignment(Pos.CENTER);
	}
}
