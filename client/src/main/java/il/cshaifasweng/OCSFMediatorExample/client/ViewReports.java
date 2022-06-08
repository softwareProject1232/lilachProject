package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.HistogramData;
import il.cshaifasweng.OCSFMediatorExample.entities.IncomeHistogramData;
import il.cshaifasweng.OCSFMediatorExample.entities.ReportOrdersByItems;
import javafx.beans.InvalidationListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class ViewReports {

    public GridPane grid;
    public ComboBox branchesSelectLeft, branchesSelectRight;
    public GridPane innerGrid;
    public Spinner daysOrders;
    public Spinner daysComplaints;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button MainMenuButton;

    @FXML
    private AnchorPane anchor;

    @FXML
    private GridPane gridCatalog;

    @FXML
    private ScrollPane scroll;

    @FXML
    private VBox vbox_main;

    private List<String> branches;

    private BarChart bc_comp,  bc_orders_left, bc_orders_right, bc_income;

    int whatToExpect = 0;
    HistogramData data;
    ReportOrdersByItems ordersData;

    IncomeHistogramData incomeData;
    @Subscribe
    public void onReceivedIncomeReport(ReceivedIncomeReport event) {
        System.out.println("Received income report\n");
        incomeData = event.getData();
        buildIncomeGraph();
    }
    @Subscribe
    public void onReceivedReportOrdersByItemsEvent(ReceivedReportOrdersByItemsEvent event) {
        System.out.println("Received orders report\n");
        ordersData = event.getOrders();
        buildOrdersGraph();
        whatToExpect = 1 - whatToExpect;
    }
    @Subscribe
    public void onReceivedComplaintsReportEvent(ReceivedComplaintsReport event) {
        System.out.println("Received complaints report\n");
        data = event.getData();
        buildComplaintsHistogram();
    }
    private void buildIncomeGraph() {
        List<String> xAxis = new ArrayList<>();
        String name;
        for (int i = 0; i < incomeData.incomeList.size(); i++) {
            xAxis.add(incomeData.branchNameList.getBranchList().get(i));
        }
        CategoryAxis x = new CategoryAxis();
        x.setLabel("Branches");
        x.setTickLabelRotation(0);
        NumberAxis y = new NumberAxis();
        y.setLabel("Income");
        grid.getChildren().remove(bc_income);
        bc_income = new BarChart<>(x, y);
        bc_income.setTitle("Income per branch");

        XYChart.Series series = new XYChart.Series();
        series.setName("Items");
        for (int i = incomeData.incomeList.size()-1 ; i >= 0; i--) {
            series.getData().add(new XYChart.Data(xAxis.get(i), incomeData.incomeList.get(i)));
        }
        bc_income.getData().add(series);
        grid.add(bc_income, 0, 2);
    }
    private void buildOrdersGraph() {
        List<String> xAxis = new ArrayList<>();
        String name;
        for (int i = 0; i < ordersData.itemsReport.size(); i++) {
            name = ordersData.itemsReport.get(i).itemData.getName();
            xAxis.add(name);
        }
        CategoryAxis x = new CategoryAxis();
        x.setLabel("Items");
        x.setTickLabelRotation(0);
        NumberAxis y = new NumberAxis();
        y.setLabel("Count");
        innerGrid.getChildren().remove((whatToExpect == 1) ? bc_orders_right : bc_orders_left);
        if (whatToExpect == 1) {
            bc_orders_right = new BarChart<>(x, y);
            bc_orders_right.setTitle("Orders per item");
        }
        else {
            bc_orders_left = new BarChart<>(x, y);
            bc_orders_left.setTitle("Orders per item");
        }
        XYChart.Series series = new XYChart.Series();
        series.setName("Items");
        for (int i = ordersData.itemsReport.size()-1 ; i >= 0; i--) {
            series.getData().add(new XYChart.Data(xAxis.get(i), ordersData.itemsReport.get(i).timesOrdered));
        }
        if (whatToExpect == 1) {
            bc_orders_right.getData().add(series);
        }
        else {
            bc_orders_left.getData().add(series);
        }
        innerGrid.add((whatToExpect == 1) ? bc_orders_right : bc_orders_left, whatToExpect, 0);
    }
    private void buildComplaintsHistogram() {

        LocalDate today = LocalDate.now();
        List<String> xAxis = new ArrayList<>();
        xAxis.add(today.toString());
        for (int i = 1; i < data.complaintsNumber.size(); i++) {
            today = today.minusDays(1);
            xAxis.add(today.toString());
        }
        CategoryAxis x    = new CategoryAxis();
        x.setLabel("Days");
        x.setTickLabelRotation(0);
        NumberAxis y = new NumberAxis();
        y.setLabel("Complaints Count");
        grid.getChildren().remove(bc_comp);
        bc_comp = new BarChart(x, y);
        bc_comp.setTitle("Complaints per day");

        XYChart.Series series = new XYChart.Series();
        series.setName("Complaints");
        for (int i = data.complaintsNumber.size()-1 ; i >= 0; i--) {
            series.getData().add(new XYChart.Data(xAxis.get(i), data.complaintsNumber.get(i)));
        }
        bc_comp.getData().add(series);
        grid.add(bc_comp, 0, 0);
    }

    @FXML
    void goTo(ActionEvent event) throws IOException {
        App.setRoot("MainMenu");
    }

    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
        daysComplaints.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 30, 30));
        daysOrders.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 30, 30));
        daysComplaints.valueProperty().addListener(refreshComplaints());
        daysOrders.valueProperty().addListener(refreshOrders());
        System.out.println("Sending Branches Request");
        SimpleClient.getClient().requestBranches();
        System.out.println("Sent Branches Request");
    }

    private InvalidationListener refreshOrders() {
        return (observable) -> {
            whatToExpect = 0;
            System.out.println("Sending request to get orders left");
            SimpleClient.getClient().requestOrdersReport(branchesSelectLeft.getSelectionModel().getSelectedItem().toString(), Integer.parseInt(daysOrders.getValue().toString()));
            System.out.println("Sent request to get orders");
            System.out.println("Sending request to get orders right");
            SimpleClient.getClient().requestOrdersReport(branchesSelectRight.getSelectionModel().getSelectedItem().toString(), Integer.parseInt(daysOrders.getValue().toString()));
            System.out.println("Sent request to get orders");
        };
    }

    private InvalidationListener refreshComplaints() {
        return (observable) -> {
            System.out.println("Sending Complaints Request");
            SimpleClient.getClient().requestComplaintsReport(Integer.parseInt(daysComplaints.getValue().toString()));
            System.out.println("Sent Complaints Request");
        };
    }
    @Subscribe
    public void onBranchRecievedEvent(BranchesReceivedEvent event) {
        System.out.println("Received branch\n");
        branches = event.getBranches().getBranchList();
        vbox_main.setVisible(true);
        for (String branch : branches) {
            branchesSelectLeft.getItems().add(branch);
            branchesSelectRight.getItems().add(branch);
        }
        branchesSelectLeft.getItems().add("network");
        branchesSelectLeft.getSelectionModel().select(branches.size());
        branchesSelectRight.getItems().add("network");
        branchesSelectRight.getSelectionModel().select(branches.size());
        System.out.println("Sending request to get complaints");
        SimpleClient.getClient().requestComplaintsReport(Integer.parseInt(daysComplaints.getValue().toString()));
        System.out.println("Sent request to get complaints");
        System.out.println("Sending request to get incomes");
        SimpleClient.getClient().requestIncomeReport();
        System.out.println("Sent request to get incomes");
    }

    public void refreshOrdersLeft(ActionEvent actionEvent) {
        whatToExpect = 0;
        System.out.println("Sending request to get orders left");
        SimpleClient.getClient().requestOrdersReport(branchesSelectLeft.getSelectionModel().getSelectedItem().toString(), Integer.parseInt(daysOrders.getValue().toString()));
        System.out.println("Sent request to get orders");
    }

    public void refreshOrdersRight(ActionEvent actionEvent) {
        whatToExpect = 1;
        System.out.println("Sending request to get orders right");
        SimpleClient.getClient().requestOrdersReport(branchesSelectRight.getSelectionModel().getSelectedItem().toString(), Integer.parseInt(daysOrders.getValue().toString()));
        System.out.println("Sent request to get orders");
    }
}
