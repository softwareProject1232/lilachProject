package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import il.cshaifasweng.OCSFMediatorExample.entities.CatalogData;
import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;

import java.io.IOException;

public class PriceWindowController {

    @FXML
    private Label nameLabel;

    @FXML
    private Button priceButton;

    @FXML
    private Button showCatalog;

    @FXML
    private TextField textField;

    @FXML
    void changePriceAction(ActionEvent event) {
        SimpleClient myclient=SimpleClient.getClient();
        myclient.changePrice(Integer.parseInt(textField.getText()),App.data.itemsdata.get(App.thisitem));
    }

    @FXML
    void showCatalolgWindow(ActionEvent event) throws IOException {
        App.setRoot("Primary");
    }

}
