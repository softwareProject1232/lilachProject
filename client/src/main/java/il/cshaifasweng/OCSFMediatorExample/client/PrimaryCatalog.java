package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.BasketItemData;
import il.cshaifasweng.OCSFMediatorExample.entities.ItemData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PrimaryCatalog {

	public Button MainMenuButton;
	@FXML
	private ResourceBundle resources;

	@FXML
	private Button addItemButton;
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
	public void onCatalogRecievedEvent(CatalogRecievedEvent event) throws FileNotFoundException, MalformedURLException {
		System.out.println("Received catalog\n");
		App.data = event.getCatalog();
		buildCatalog();
	}
	@FXML
	void goTo(ActionEvent event) throws IOException {
		if (App.userData.type != 0){
			App.setRoot("MainMenu");
		}
		else{
			App.setRoot("LoginAndRegister");
		}
	}

	void buildCatalog() throws FileNotFoundException, MalformedURLException {
		cleanUpCatalog();
		int id;
		String name;
		int price;
		String description;
		VBox pane;
		if (App.userData.type != 4 && App.userData.type != 0) {
			pane = generateCustomFlower();
			addFlower(pane);
		}
		for (ItemData item: App.data.itemsdata){
			id = item.getId();
			name = item.getName();
			price = item.getPrice();
			String path = item.getImageURL();

			Image image = new Image(path, true);
			ImageView imageView = new ImageView(image);
			imageView.setFitHeight(60);
			imageView.setFitWidth(60);
			imageView.setPreserveRatio(false);
			description = item.getDescription();
			System.out.format("id: %s\nname: %s\nprice: %s\ndescription: %s\n", id, name, price, description);
			pane = generateItem(id, name, price, description, imageView);
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
	VBox generateCustomFlower(){
		VBox ret = new VBox();
		Label l_name = new Label("Custom Flower");
		ret.setAlignment(Pos.CENTER);

		ret.setPrefSize(100, 50);
		ret.setBorder(new Border(new BorderStroke(Color.BLACK,
				BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		ret.setOnMouseClicked(event ->  {
			try {
				App.setRoot("CustomSelection");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		ret.getChildren().addAll(l_name);
		return ret;
	}
	VBox generateItem(int id, String name, int price, String description, ImageView im){
		//TODO: use description
		VBox ret = new VBox();
		Label l_name = new Label(name), l_price = new Label("Price: " + price + "$");
		ret.setAlignment(Pos.CENTER);
		if(App.userData.type != 4 && App.userData.type != 0){
			Button addToCart = new Button("Add to cart");
			addToCart.setOnMouseClicked(event -> {
				//initialize an empty list of itemData and push it to App.data.cartList
				List<ItemData> temp = new ArrayList<ItemData>();
				temp.add(App.data.itemsdata.get(id-1));
				BasketItemData cartList = new BasketItemData(temp);
				App.orderData.items.add(cartList);
				System.out.println("Added to cart regular flower\n");
			});
			ret.getChildren().add(addToCart);
		}
		ret.setPrefSize(100, 50);
		ret.setBorder(new Border(new BorderStroke(Color.BLACK,
				BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		ret.setOnMouseClicked(event ->  {
			App.thisitem = id-1;
			System.out.println("Clicked ID: " + (id-1));
			if(App.userData.type == 4){
				try {
					App.setRoot("UpdateProduct");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		ret.getChildren().addAll(l_name, l_price, im);
		return ret;
	}
	@FXML
	void addFlower(VBox item) {
		int rows = gridCatalog.getRowCount();
		int cols = gridCatalog.getColumnCount();
		if (i == rows){
			gridCatalog.getRowConstraints().add(new RowConstraints());
			System.out.println("Added Row: " + rows);

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
		if(App.userData.type == 0){
			MainMenuButton.setText("Back");
		}
		System.out.println("Sending request\n");
		SimpleClient.getClient().requestCatalog();
		System.out.println("Sent request\n");
		if(App.userData.type!=4 && App.userData.type!=5){
			addItemButton.setVisible(true);
		}
		else{
			addItemButton.setVisible(false);
		}
	}

	@FXML
	void addItemAction(ActionEvent event) throws IOException {
		App.setRoot("NewItem");
	}
}
