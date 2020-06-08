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
import lapr2.isep.pot.model.Freelancer;
import lapr2.isep.pot.model.Task;

import java.net.URL;
import java.util.ResourceBundle;


public class CreatePaymentTransactionUI implements Initializable {

    private CollaboratorMenuUI collaboratorMenuUI;

    private static final CreatePaymentTransactionController createPaymentTransactionController = new CreatePaymentTransactionController();

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

    }

    @FXML
    void chooseFreeOnAction(ActionEvent event) {

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
        clearTextFields();
        Stage stage = (Stage) createBtn.getScene().getWindow();
        stage.close();
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
    }
    @FXML
    void showListsOnAction(ActionEvent event) {
        refreshListViews();
    }

    private void refreshListViews() {
        freelancersListListView.getItems().setAll(createPaymentTransactionController.getListFreelancers());
        tasksListListVIew.getItems().setAll(createPaymentTransactionController.getTaskList());
    }
}
