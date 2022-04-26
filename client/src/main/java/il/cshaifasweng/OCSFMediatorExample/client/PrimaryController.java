package il.cshaifasweng.OCSFMediatorExample.client;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ScrollPane;
public class PrimaryController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btn;


	@FXML
	private ScrollPane scroll;
	@FXML
	private GridPane gridCatalog;

	private int i = 0;
	private int j = 0;
	@FXML
	void addFlower(ActionEvent event) {
		int rows = gridCatalog.getRowCount();
		int cols = gridCatalog.getColumnCount();
		gridCatalog.add(new Button("lol"), j, i, 1, 1);
		j+=1;
		if (j % cols == 0){
			i+=1;
			j=0;
		}
		if (i == rows){
			gridCatalog.addRow(rows, new Button("new row"));
			j+=1;
			System.out.format("Rows: %s\n", rows);
		}
	}

	@FXML
	void initialize() {
		assert btn != null : "fx:id=\"btn\" was not injected: check your FXML file 'primary.fxml'.";
		assert gridCatalog != null : "fx:id=\"gridCatalog\" was not injected: check your FXML file 'primary.fxml'.";
		scroll.pannableProperty().set(true);

	}
// TODO fix scroll
}
