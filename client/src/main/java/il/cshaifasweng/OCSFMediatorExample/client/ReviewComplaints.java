package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.BasketItemData;
import il.cshaifasweng.OCSFMediatorExample.entities.ComplaintData;
import il.cshaifasweng.OCSFMediatorExample.entities.ComplaintListData;
import il.cshaifasweng.OCSFMediatorExample.entities.UserData;
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

import java.io.IOException;

public class ReviewComplaints {

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
    public ComplaintListData copmlientlist;
    @Subscribe
    public void onReceivedComplientEvent(ReceivedComplientEvent event) {
        System.out.println("Received complient list\n");
        copmlientlist = event.getComplients();
        buildComplaints();
    }

    void buildComplaints(){
        cleanComplaints();
        int id;
        UserData user;
        int price;
        String description;
        HBox pane;
        UserData temp;
        int index = 0;
        if(copmlientlist!=null) {
            for (ComplaintData comp : copmlientlist.getComplaints()) {
                user = comp.issuedBy;
                description = comp.complaintDescription;
                temp = comp.issuedBy;
                pane = generateItem(user, description,temp);
                index++;
                addItem(pane);
            }
        }
        else
        {
            System.out.format("null complients");
        }
    }
    void cleanComplaints(){
        ItemList.getChildren().removeAll();
        ItemList.getChildren().clear();
    }
    HBox generateItem(UserData user, String description,UserData temp){
        HBox ret = new HBox();
        Label l_name = new Label(user.getUsername()),l_desc = new Label(user.getBranchName()), l_price = new Label(description);
        Button refund = new Button();
        Button delete = new Button();
        ret.setAlignment(Pos.CENTER);

        l_name.setUnderline(true);
        refund.setText("Resolve");
        refund.setOnMouseClicked(event ->  {;
            System.out.println("Clicked resolve " );
            try {
                App.compuser=temp;
                App.setRoot("RefundForComplaint");
            } catch (IOException e) {
                e.printStackTrace();
            }


        });
        delete.setText("Delete");
        delete.setOnMouseClicked(event ->  {;
            //delete complaint function

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
        assert ItemList != null : "fx:id=\"ItemList\" was not injected: check your FXML file 'ReviewComplaints.fxml'.";
        assert MainMenuButton != null : "fx:id=\"MainMenuButton\" was not injected: check your FXML file 'ReviewComplaints.fxml'.";
        assert anchor != null : "fx:id=\"anchor\" was not injected: check your FXML file 'ReviewComplaints.fxml'.";
        assert scroll != null : "fx:id=\"scroll\" was not injected: check your FXML file 'ReviewComplaints.fxml'.";
        SimpleClient myclient=SimpleClient.getClient();
        EventBus.getDefault().register(this);
        myclient.requestComplaints(App.userData.branchName);
        buildComplaints();
    }
}
