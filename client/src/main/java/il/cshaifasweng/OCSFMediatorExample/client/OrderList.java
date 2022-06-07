package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.ComplaintData;
import il.cshaifasweng.OCSFMediatorExample.entities.UserData;
import il.cshaifasweng.OCSFMediatorExample.server.Order;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class OrderList {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox ItemList;

    @FXML
    private Button MainMenuButton;

    @FXML
    private AnchorPane anchor;

    @FXML
    private ScrollPane scroll;

    @FXML
    void goToMainMenu(ActionEvent event) throws IOException {
        App.setRoot("MainMenu");
    }
    void buildComplaints(){
        cleanOrders();
        int id;
        UserData user;
        int price;
        String description;
        HBox pane;
        int index = 0;

        for (Order comp: orderlist){
            user = comp.price;
            description = comp.complaintDescription;

            pane = generateItem(user,description);
            index++;
            addItem(pane);
        }
    }
    void cleanOrders(){
        ItemList.getChildren().removeAll();
        ItemList.getChildren().clear();
    }
    HBox generateItem(UserData user, String description){
        HBox ret = new HBox();
        Label l_name = new Label(user.getUsername()),l_desc = new Label(user.getBranchName()), l_price = new Label(description);
        Button refund = new Button();
        ret.setAlignment(Pos.CENTER);

        l_name.setUnderline(true);
        refund.setText("Resolve");
        refund.setOnMouseClicked(event ->  {;
            System.out.println("Clicked resolve " );

        });

        ret.setPrefSize(350, 50);
        ret.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        ret.getChildren().addAll(l_name,l_desc, l_price,refund);
        return ret;
    }
    @FXML
    void addItem(HBox item) {
        ItemList.getChildren().add(item);
    }
    @FXML
    void initialize() {
        assert ItemList != null : "fx:id=\"ItemList\" was not injected: check your FXML file 'OrderList.fxml'.";
        assert MainMenuButton != null : "fx:id=\"MainMenuButton\" was not injected: check your FXML file 'OrderList.fxml'.";
        assert anchor != null : "fx:id=\"anchor\" was not injected: check your FXML file 'OrderList.fxml'.";
        assert scroll != null : "fx:id=\"scroll\" was not injected: check your FXML file 'OrderList.fxml'.";

    }

}
