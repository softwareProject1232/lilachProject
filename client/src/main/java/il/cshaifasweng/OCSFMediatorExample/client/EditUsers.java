package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.BasketItemData;
import il.cshaifasweng.OCSFMediatorExample.entities.ItemData;
import il.cshaifasweng.OCSFMediatorExample.entities.UserData;
import il.cshaifasweng.OCSFMediatorExample.server.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EditUsers {

	public Button MainMenuButton;
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
	public List<UserData> currentList;
	@Subscribe
	public void onUserListDataRecievedEvent(ReceivedUserListEvent event) {
		System.out.println("Received user list\n");
		currentList = event.getUsers().users;
		buildList();
	}
	@FXML
	void goTo(ActionEvent event) throws IOException {
		App.setRoot("MainMenu");
	}

	void buildList() {
		cleanUpScreen();
		String name;
		VBox pane;
		for (UserData user: currentList){
			name = user.getUsername();
			System.out.println(user.toString());
			pane = generateItem(name, user);
			addUser(pane);
		}
	}
	void cleanUpScreen(){
		gridCatalog = new GridPane();
		i = 0;
		j = 0;
		int colsCount = 4;
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
	VBox generateItem(String un, UserData user){
		VBox ret = new VBox();
		Label l_name = new Label(un);
		ret.setAlignment(Pos.CENTER);
		Button viewUser = new Button("Inspect User");
		viewUser.setOnMouseClicked(event -> {
			App.editingUser = user;
			try {
				App.setRoot("InspectUser");
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			System.out.println("Added User\n");
		});
		ret.getChildren().add(viewUser);
		ret.setPrefSize(150, 60);
		ret.setBorder(new Border(new BorderStroke(Color.BLACK,
				BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		ret.getChildren().addAll(l_name);
		return ret;
	}
	@FXML
	void addUser(VBox item) {
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
		System.out.println("Sending request\n");
		SimpleClient.getClient().requestUsers("network");
		System.out.println("Sent request\n");
	}
}
