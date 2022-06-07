package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class NewItem {

    @FXML
    private TextField DescriptionTF;

    @FXML
    private TextField ImageTF;

    @FXML
    private TextField NameTF;

    @FXML
    private TextField PriceTF;

    @FXML
    private Button addItemButton;

    @FXML
    void addItemAction(ActionEvent event) {
        SimpleClient myclient=SimpleClient.getClient();
        myclient.addItem(NameTF.getText(),DescriptionTF.getText(),Integer.parseInt(PriceTF.getText()),ImageTF.getText(),App.data.itemsdata.get(App.thisitem));
    }

    @FXML
    void goTo(ActionEvent event) throws IOException {
        App.setRoot("PrimaryCatalog");
    }
}
