package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class InspectUser {
    @FXML
    public TextField priceText;

    @FXML
    public Label itemName;


    @FXML
    void changePriceAction(ActionEvent event) throws IOException {
        SimpleClient.getClient().changePrice(Integer.parseInt(priceText.getText()),App.data.itemsdata.get(App.thisitem));
        App.setRoot("PrimaryCatalog");

    }

    @FXML
    void showCatalogWindow(ActionEvent event) throws IOException {
        App.setRoot("PrimaryCatalog");
    }

    @FXML
    void initialize() {
        itemName.setAlignment(Pos.CENTER);
        itemName.setText("Item: " + App.data.itemsdata.get(App.thisitem).getName());
    }

}