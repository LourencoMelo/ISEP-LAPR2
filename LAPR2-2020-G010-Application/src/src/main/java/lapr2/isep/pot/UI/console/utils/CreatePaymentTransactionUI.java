package lapr2.isep.pot.UI.console.utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lapr2.isep.pot.controller.ApplicationController;
import lapr2.isep.pot.controller.CreatePaymentTransactionController;
import lapr2.isep.pot.model.Freelancer;
import lapr2.isep.pot.model.PaymentTransaction;
import lapr2.isep.pot.model.Platform;
import lapr2.isep.pot.model.Task;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;


public class CreatePaymentTransactionUI implements Initializable {

    private CollaboratorMenuUI collaboratorMenuUI;

    private Stage transactionListAndAmountStage;

    private static final CreatePaymentTransactionController createPaymentTransactionController = new CreatePaymentTransactionController();

//    private Platform platform = createPaymentTransactionController.getPlatform();

    double x = 0;
    double y = 0;

    private ApplicationController applicationController;

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
            System.exit(0);
        }
    }

    @FXML
    void createOnAction(ActionEvent event) {
        try {
            PaymentTransaction paymentTransaction = createPaymentTransactionController.newPaymentTransaction(transactionID.getText(), Formatter(endDate.getText()), Integer.parseInt(delay.getText()), briefDescription.getText(), selectedFreelancer, selectedTask);
            if (createPaymentTransactionController.getValidationPaymentTransaction(paymentTransaction)) {
                createPaymentTransactionController.registPaymentTransaction();
                transactionListAndAmountStage.show();
            } else {
                Alert alert = AlertUI.createAlert(Alert.AlertType.WARNING, applicationController.getAppName(), "Error", "The transaction inserted is already in the system.");
                alert.show();
            }
        } catch (IllegalArgumentException | ParseException exception) {
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
    }

    public void associateParentUI(CollaboratorMenuUI collaboratorMenuUI) {
        this.collaboratorMenuUI = collaboratorMenuUI;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        applicationController = ApplicationController.getApplicationController();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TransactionsListAndAmount.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            transactionListAndAmountStage = new Stage();
            transactionListAndAmountStage.initModality(Modality.APPLICATION_MODAL);
            transactionListAndAmountStage.getIcons().add(new Image("file:images\\t4j.jpg"));
            transactionListAndAmountStage.setTitle("Create Payment Transaction");
            transactionListAndAmountStage.setResizable(false);
            transactionListAndAmountStage.setScene(scene);
            transactionListAndAmountStage.initStyle(StageStyle.TRANSPARENT);

            TransactionsListAndAmountUI transactionsListAndAmountUI = loader.getController();
            transactionsListAndAmountUI.associateParentUI(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showListsOnAction(ActionEvent event) {
        refreshListViews();
    }

    private void refreshListViews() {
        freelancersListListView.getItems().setAll(createPaymentTransactionController.getListFreelancers());
        tasksListListVIew.getItems().setAll(createPaymentTransactionController.getTaskList());
    }

    public Freelancer getChosenFreelancer() {
        return freelancersListListView.getSelectionModel().getSelectedItem();
    }

    public Task getChosenTask() {
        return tasksListListVIew.getSelectionModel().getSelectedItem();
    }

    public static CreatePaymentTransactionController getCreatePaymentTransactionController(){
        return createPaymentTransactionController;
    }

    public Freelancer getSelectedFreelancer() {
        Freelancer selectedFreelancer = getChosenFreelancer();
        this.selectedFreelancer = selectedFreelancer;
        //platform.setSelectedFreelancer(selectedFreelancer);
        return selectedFreelancer;
    }

    public Task getSelectedTask() {
        Task selectedTask = getChosenTask();
        this.selectedTask = selectedTask;
        //platform.setSelectedTask(selectedTask);
        return selectedTask;
    }

    public static Date Formatter(String date) throws ParseException {
        SimpleDateFormat formmater1 = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = formmater1.parse(date);
        return date1;
    }
}
