package il.cshaifasweng.OCSFMediatorExample.client;


import java.util.ArrayList;
import java.util.List;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
import il.cshaifasweng.OCSFMediatorExample.entities.CatalogData;

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
	Pane generateItem(){
		Pane ret = new Pane();
		Label name = new Label("test");
		ret.setPrefSize(50, 50);
		ret.setBorder(new Border(new BorderStroke(Color.BLACK,
				BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		// ret.setOnMouseClicked(); // Integrate with description window
		ret.getChildren().add(name);
		return ret;
	}
	@FXML
	void addFlower(ActionEvent event) {
		int rows = gridCatalog.getRowCount();
		int cols = gridCatalog.getColumnCount();
		if (i == rows){
			gridCatalog.getRowConstraints().add(new RowConstraints());
			i+=1;
			System.out.format("Rows: %s\n", rows);

		}
		Button temp = new Button("1");
		gridCatalog.add(generateItem(), j, i, 1, 1);
		j+=1;
		if (j % cols == 0){
			i+=1;
			j=0;
		}
	}

	@FXML
	void initialize() {
		assert anchor != null : "fx:id=\"anchor\" was not injected: check your FXML file 'primary.fxml'.";
		assert btn != null : "fx:id=\"btn\" was not injected: check your FXML file 'primary.fxml'.";
		assert scroll != null : "fx:id=\"scroll\" was not injected: check your FXML file 'primary.fxml'.";
		assert vbox_main != null : "fx:id=\"vbox_main\" was not injected: check your FXML file 'primary.fxml'.";
		int colsCount = 9;
		for(int i = 0; i<colsCount;i++){
			gridCatalog.getColumnConstraints().add(new ColumnConstraints());
		}
		gridCatalog.setAlignment(Pos.CENTER);
	}
}
