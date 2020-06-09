package lapr2.isep.pot.UI.console.utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lapr2.isep.pot.controller.ApplicationController;
import lapr2.isep.pot.controller.CreatePaymentTransactionController;
import lapr2.isep.pot.model.PaymentTransaction;
import lapr2.isep.pot.model.Platform;


public class TransactionsListAndAmountUI {

    private CreatePaymentTransactionUI createPaymentTransactionUI;

    private ApplicationController applicationController = new ApplicationController();

    private CreatePaymentTransactionController createPaymentTransactionController = applicationController.getCreatePaymentTransactionController();

    private Platform platform = createPaymentTransactionController.getPlatform();


    double x = 0;
    double y = 0;

    @FXML
    private ListView<PaymentTransaction> transactionsListVIew;

    @FXML
    private Button xBtn;

    @FXML
    private TextField amountToPayTxtField;

    @FXML
    private Button goBackBtn;

    @FXML
    private Button showBtn;

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
    void showOnAction(ActionEvent event) {
        refreshListView();
        amountToPayTxtField.setText(String.valueOf(createPaymentTransactionController.getTaskCost(createPaymentTransactionUI.getSelectedFreelancer(), createPaymentTransactionUI.getSelectedTask())));
    }


    @FXML
    void GoBackOnAction(ActionEvent event) {
        clearTextFields();
        Stage stage = (Stage) showBtn.getScene().getWindow();
        stage.close();
    }

    public void clearTextFields() {
        transactionsListVIew.getItems().clear();
        amountToPayTxtField.clear();
    }

    public void associateParentUI(CreatePaymentTransactionUI createPaymentTransactionUI) {
        this.createPaymentTransactionUI = createPaymentTransactionUI;
    }

    private void refreshListView() {
        transactionsListVIew.getItems().setAll(createPaymentTransactionController.getTransactionsList());
    }



}
