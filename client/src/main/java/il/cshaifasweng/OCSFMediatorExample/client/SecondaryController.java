package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.Objects;

public class SecondaryController {

    @FXML
    private Label DescriptionLabel;

    @FXML
    private Button GoBackButton;

    @FXML
    private Label NameLabel;

    @FXML
    private Label priceText;

    @FXML
    private Button updatePrice;

    @FXML
    void changeToPriceWindow(ActionEvent event) throws IOException {
        App.setRoot("ChangePriceWindow");
    }

    @FXML
    void switchToCatalogWindow(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }

    @FXML
    void initialize() {
        NameLabel.setText(App.data.itemsdata.get(App.thisitem).getName());//insert name from data base
        DescriptionLabel.setText(App.data.itemsdata.get(App.thisitem).getDescription());//insert descriptioon from data base
        priceText.setText(Integer.toString(App.data.itemsdata.get(App.thisitem).getPrice()));//insert price
    }

}