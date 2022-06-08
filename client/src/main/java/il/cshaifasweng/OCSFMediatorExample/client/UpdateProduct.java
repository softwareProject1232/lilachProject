package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class UpdateProduct {

    @FXML
    private Label DescriptionLabel;

    @FXML
    private Button GoBackButton;

    @FXML
    private Label NameLabel;

    @FXML
    private Label priceText;

    @FXML
    private Button updateButton;

    @FXML
    void changeToPriceWindow(ActionEvent event) throws IOException {
        try {
            System.out.println("changing root to changepricewindow");
            App.setRoot("ChangePriceWindow");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void switchToCatalogWindow(ActionEvent event) throws IOException {
        App.setRoot("PrimaryCatalog");
    }

    @FXML
    void initialize() {
        NameLabel.setText(App.data.itemsdata.get(App.thisitem).getName());//insert name from data base
        DescriptionLabel.setText(App.data.itemsdata.get(App.thisitem).getDescription());//insert descriptioon from data base
        priceText.setText(Integer.toString(App.data.itemsdata.get(App.thisitem).getPrice()));//insert price
    }



}