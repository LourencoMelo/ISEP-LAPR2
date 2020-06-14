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
import lapr2.isep.pot.controller.SetDateToPayController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SetPaymentDateUI {

    private ApplicationController applicationController;

    private SetDateToPayController setDateToPayController;

    private ManagerMenuUI managerMenuUI;

    @FXML
    private TextField dateToPayTxtField;

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
        this.setDateToPayController = new SetDateToPayController();
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
            applicationController.saveInfo();
            applicationController.createFileWithPaidTransactions();
            System.exit(0);
        }
    }

    @FXML
    void SetDateOnAction(ActionEvent event) throws ParseException, IOException {
        try {
            setDateToPayController.setDateToPay(Formatter(dateToPayTxtField.getText()));
            Alert alert = AlertUI.createAlert(Alert.AlertType.INFORMATION, applicationController.getAppName(),
                    "Date defined.", "The date was added to the transactions.");
            alert.show();
            applicationController.createFileWithPaidTransactions();
        } catch (Exception exception) {
            Alert alert = AlertUI.createAlert(Alert.AlertType.WARNING, applicationController.getAppName(),
                    "Error.", "The date must follow the following structure: dd/mm/yyyy hh:mm:ss");
            alert.show();
        }
    }

    @FXML
    void GoBackOnAction(ActionEvent event) {
        clearTextFields();
        Stage stage = (Stage) setDateBtn.getScene().getWindow();
        stage.close();
    }

    public void clearTextFields() {
        dateToPayTxtField.clear();
    }

    public static Date Formatter(String date) throws ParseException {
        SimpleDateFormat formmater1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date1 = formmater1.parse(date);
        return date1;
    }
}