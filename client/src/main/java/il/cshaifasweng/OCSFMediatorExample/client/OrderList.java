package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.OrderData;
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
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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

    public List<OrderData> currentList;
    @Subscribe
    public void onUserOrderDataRecievedEvent(ReceivedOrderListDataEvent event) {
        System.out.println("Received user order list\n");
        currentList = event.getOrders().orders;
        buildGrid();
    }
    @FXML
    void goToMainMenu(ActionEvent event) throws IOException {
        App.setRoot("MainMenu");
    }
    void buildGrid(){
        cleanOrders();
        int id;
        LocalDate date;
        int price;
        HBox pane;
        int index = 0;

        for (OrderData comp: currentList){
            price = comp.getTotalPrice();
            date = comp.getDate();
            pane = generateItem(date, price);
            index++;
            addItem(pane);
        }
    }
    void cleanOrders(){
        ItemList.getChildren().removeAll();
        ItemList.getChildren().clear();
    }
    HBox generateItem(LocalDate date, int price){
        HBox ret = new HBox();
        Label l_date = new Label(date.toString()), l_price = new Label(String.valueOf(price) + "$");
        Button refund = new Button();
        ret.setAlignment(Pos.CENTER);

        l_date.setUnderline(true);
        refund.setText("Resolve");
        refund.setOnMouseClicked(event ->  {;
            System.out.println("Clicked resolve " );

        });

        ret.setPrefSize(350, 50);
        ret.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        ret.getChildren().addAll(l_date,l_price,refund);
        return ret;
    }
    @FXML
    void addItem(HBox item) {
        ItemList.getChildren().add(item);
    }
    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
        System.out.println("Sending request\n");
        SimpleClient.getClient().requestOrdersByUser(App.userData.getDbId(), App.userData.getBranchName());
        System.out.println("Sent request\n");
    }

}
