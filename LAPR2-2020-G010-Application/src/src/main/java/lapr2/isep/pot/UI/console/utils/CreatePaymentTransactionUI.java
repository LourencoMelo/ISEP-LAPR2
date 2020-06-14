package lapr2.isep.pot.UI.console.utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lapr2.isep.pot.controller.ApplicationController;
import lapr2.isep.pot.controller.CreatePaymentTransactionController;
import lapr2.isep.pot.controller.RegisterFreelancerController;
import lapr2.isep.pot.controller.TaskCreationController;
import lapr2.isep.pot.model.*;

import java.io.FileNotFoundException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;


public class CreatePaymentTransactionUI implements Initializable {

    private CollaboratorMenuUI collaboratorMenuUI;

    private Stage transactionListAndAmountStage;

    private CreatePaymentTransactionController createPaymentTransactionController;

    private RegisterFreelancerController registerFreelancerController;

    private TaskCreationController taskCreationController;

    private ApplicationController applicationController;

    double x = 0;
    double y = 0;

    @FXML
    private ListView<Task> tasksListListVIew;

    @FXML
    private Button xBtn;

    @FXML
    private TextField delay;

    @FXML
    private Button chooseTaskBtn;

    @FXML
    private TextField endDate;

    @FXML
    private ListView<Freelancer> freelancersListListView;

    @FXML
    private Button createBtn;

    @FXML
    private Button goBackBtn;

    @FXML
    private Button chooseFreeBtn;

    @FXML
    private Button showListsBtn;

    @FXML
    private TextField briefDescription;

    @FXML
    private TextField transactionID;

    private Freelancer selectedFreelancer;

    private Task selectedTask;

    @FXML
    private TextField amountToPayTxtField;

    @FXML
    private Button refreshBtn;

    @FXML
    private ListView<PaymentTransaction> transactionsListListView;

    public CreatePaymentTransactionUI() throws FileNotFoundException {
        this.applicationController = new ApplicationController();
        this.taskCreationController = new TaskCreationController();
        this.createPaymentTransactionController = new CreatePaymentTransactionController();
        this.registerFreelancerController = new RegisterFreelancerController();
    }

    @FXML
    void dragged(MouseEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    @FXML
    void pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    @FXML
    void chooseTaskOnAction(ActionEvent event) {
        Task selectedTask = getChosenTask();
        this.selectedTask = selectedTask;
        Alert alert = AlertUI.createAlert(Alert.AlertType.INFORMATION, applicationController.getAppName(), "Success", "Task has been chosen!!");
        alert.show();
    }

    @FXML
    void chooseFreeOnAction(ActionEvent event) {
        Freelancer selectedFreelancer = getChosenFreelancer();
        this.selectedFreelancer = selectedFreelancer;
        Alert alert = AlertUI.createAlert(Alert.AlertType.INFORMATION, applicationController.getAppName(), "Success", "Freelancer has been chosen!!");
        alert.show();
    }


    @FXML
    void XOnAction(ActionEvent event) {
        Alert alert = AlertUI.createAlert(Alert.AlertType.CONFIRMATION, applicationController.getAppName(),
                "Action confirmation.", "Do you really want to close the application?");
        if (alert.showAndWait().get() == ButtonType.CANCEL) {
            event.consume();
        } else {
            applicationController.saveInfo();
            System.exit(0);
        }
    }

    @FXML
    void createOnAction(ActionEvent event) {
        try {
            PaymentTransaction paymentTransaction = createPaymentTransactionController.newPaymentTransaction(transactionID.getText(), Formatter(endDate.getText()), Double.parseDouble(delay.getText()), briefDescription.getText(), getChosenFreelancer(), getChosenTask());
            if (createPaymentTransactionController.getValidationPaymentTransaction(paymentTransaction)) {
                createPaymentTransactionController.registPaymentTransaction();
                amountToPayTxtField.setText(String.format("%.02f€",this.createPaymentTransactionController.getTaskCost(getChosenFreelancer(), getChosenTask())));
                //freelancersListListView.getSelectionModel().getSelectedItem().addDelayToFreelancer(Integer.parseInt(delay.getText()));
                refreshListViewTransactions();
            } else {
                Alert alert = AlertUI.createAlert(Alert.AlertType.WARNING, applicationController.getAppName(), "Error", "The transaction inserted is already in the system.");
                alert.show();
            }
        } catch (IllegalArgumentException | ParseException | NullPointerException exception) {
            Alert alert = AlertUI.createAlert(Alert.AlertType.ERROR, applicationController.getAppName(), "Error", "You must select a freelancer and a task. Also arguments must follow the following rules:" +
                    "\n * Arguments can't be null or empty;" +
                    "\n * Delay needs to be in numbers" +
                    "\n * A freelancer and a task needs to be chosen");
            alert.show();
        }
    }

    @FXML
    void GoBackOnAction(ActionEvent event) {
        clearTextFields();
        Stage stage = (Stage) createBtn.getScene().getWindow();
        stage.close();
    }

    public void clearTextFields() {
        delay.clear();
        endDate.clear();
        briefDescription.clear();
        transactionID.clear();
        freelancersListListView.getItems().clear();
        tasksListListVIew.getItems().clear();
        transactionsListListView.getItems().clear();
        amountToPayTxtField.clear();
    }

    public void associateParentUI(CollaboratorMenuUI collaboratorMenuUI) {
        this.collaboratorMenuUI = collaboratorMenuUI;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void showListsOnAction(ActionEvent event) {
        refreshListViews();
    }

    private void refreshListViews() {
        freelancersListListView.getItems().setAll(registerFreelancerController.getRegistFreelancer().getFreelancerList());
        tasksListListVIew.getItems().setAll(taskCreationController.getTaskLists());
    }

    public Freelancer getChosenFreelancer() {
        return freelancersListListView.getSelectionModel().getSelectedItem();
    }

    public Task getChosenTask() {
        return tasksListListVIew.getSelectionModel().getSelectedItem();
    }

    public CreatePaymentTransactionController getCreatePaymentTransactionController() {
        return createPaymentTransactionController;
    }

    private void refreshListViewTransactions() {
        transactionsListListView.getItems().setAll(this.createPaymentTransactionController.getTotalPaymentTransactionList());
    }

    public static Date Formatter(String date) throws ParseException {
        SimpleDateFormat formmater1 = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = formmater1.parse(date);
        return date1;
    }

    @FXML
    void RefreshOnAction(ActionEvent event) {
        refreshListViewTransactions();
    }
}
