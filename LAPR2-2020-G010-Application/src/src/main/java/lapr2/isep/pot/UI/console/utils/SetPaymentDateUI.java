package lapr2.isep.pot.UI.console.utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lapr2.isep.pot.controller.ApplicationController;

import java.io.FileNotFoundException;

public class SetPaymentDateUI {

    private ApplicationController applicationController;

    private ManagerMenuUI managerMenuUI;

    @FXML
    private TextField dateToPayBtn;

    @FXML
    private Button xBtn;

    @FXML
    private Button setDateBtn;

    @FXML
    private Button goBackBtn;

    double x = 0;
    double y = 0;

    public SetPaymentDateUI() throws FileNotFoundException {
        this.applicationController = new ApplicationController();
    }

    public void associateParentUI(ManagerMenuUI managerMenuUI) {
        this.managerMenuUI = managerMenuUI;
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
    void SetDateOnAction(ActionEvent event) {

    }

    @FXML
    void GoBackOnAction(ActionEvent event) {
        clearTextFields();
        Stage stage = (Stage) setDateBtn.getScene().getWindow();
        stage.close();
    }

    public void clearTextFields() {
        dateToPayBtn.clear();
    }
}