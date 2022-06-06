package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.UserData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InspectUser {
    public TextField username_label;
    public TextField password_label;
    public TextField email_label;
    public ComboBox type_dropdown;
    public TextField id_label;
    public TextField cc_label;

    private UserData curr;
    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
        curr = App.editingUser;
        username_label.setPromptText(curr.getUsername());
        password_label.setPromptText(curr.getPassword());
        email_label.setPromptText(curr.getEmail());
        List<String> types = new ArrayList<String>();
        types.add("Branch");
        types.add("Newtwork");
        types.add("Subcription");
        type_dropdown.setPromptText(curr.getType() == 4 ? "Manager" : types.get(curr.getType() + 1));
        id_label.setPromptText(curr.getId());
        cc_label.setPromptText(curr.getCreditCard());
        type_dropdown.getItems().addAll(
                types.get(0),
                types.get(1),
                types.get(2)
        );
    }

    public void SwapToMainMenu(ActionEvent actionEvent) throws IOException {
        App.setRoot("MainMenu");
    }
}