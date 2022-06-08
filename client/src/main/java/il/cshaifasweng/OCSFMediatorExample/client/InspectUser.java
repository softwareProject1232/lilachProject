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
        //EventBus.getDefault().register(this);
        curr = App.editingUser;
        username_label.setPromptText(curr.getUsername());
        password_label.setPromptText(curr.getPassword());
        email_label.setPromptText(curr.getEmail());
        List<String> types = new ArrayList<String>();
        types.add("Branch");
        types.add("Newtwork");
        types.add("Subcription");
        type_dropdown.setPromptText(curr.getType() == 4 ? "Manager" : types.get(curr.getType() - 1));
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

    public void updateUser(ActionEvent actionEvent) throws IOException {
        UserData newUser = new UserData(
                curr.getUsername(),
                curr.getPassword(),
                curr.getEmail(),
                curr.getType(),
                curr.getCreditCard(),
                curr.getId(),
                curr.getDbId(),
                curr.getBranchName(),
                curr.getBalance()
        );
        boolean changed = false;
        if(!username_label.getText().equals(newUser.getUsername()) && !username_label.getText().isEmpty()) {
            newUser.setUsername(username_label.getText());
            changed = true;
        }
        if(!password_label.getText().equals(newUser.getPassword()) && !password_label.getText().isEmpty()) {
            newUser.setPassword(password_label.getText());
            changed = true;
        }
        if(!email_label.getText().equals(newUser.getEmail()) && !email_label.getText().isEmpty()) {
            newUser.setEmail(email_label.getText());
            changed = true;
        }
        if(type_dropdown.getValue() != null && !type_dropdown.getValue().equals(newUser.getType() == 4 ? "Manager" : type_dropdown.getPromptText())) {
            newUser.setType(type_dropdown.getValue().equals("Manager") ? 4 : type_dropdown.getValue().equals("Branch") ? 0 : type_dropdown.getValue().equals("Network") ? 1 : 2);
            changed = true;
        }
        if(!id_label.getText().equals(newUser.getId()) && !id_label.getText().isEmpty()) {
            newUser.setId(id_label.getText());
            changed = true;
        }
        if(!cc_label.getText().equals(newUser.getCreditCard()) && !cc_label.getText().isEmpty()) {
            newUser.setCreditCard(cc_label.getText());
            changed = true;
        }
        System.out.println("Sending update request to server");
        SimpleClient.getClient().updateUser(newUser);
        System.out.println("Update request sent");
        if(changed) {
            App.editingUser = newUser;
            App.setRoot("EditUsers");
        }
    }

    public void Freeze(ActionEvent actionEvent) {
    }
}