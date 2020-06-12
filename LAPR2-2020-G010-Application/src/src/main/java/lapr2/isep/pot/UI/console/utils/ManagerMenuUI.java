package lapr2.isep.pot.UI.console.utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lapr2.isep.pot.UI.console.MainApp;
import lapr2.isep.pot.controller.ApplicationController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManagerMenuUI implements Initializable {

    private LogInUI logInUI;

    private ApplicationController applicationController;

    private Stage setPaymentDateStage;

    double x = 0;
    double y = 0;

    @FXML
    private Button xBtn;

    @FXML
    private Button defineHourBtn;

    @FXML
    private Button goBackBtn;

    public ManagerMenuUI() throws FileNotFoundException {
        this.applicationController = new ApplicationController();
    }

    public void associateParentUI(LogInUI logInUI) {
        this.logInUI = logInUI;
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
    void DefineHourOnAction(ActionEvent event) {
        setPaymentDateStage.show();
    }

    @FXML
    void GoBackOnAction(ActionEvent event) {
        Stage stage = (Stage) defineHourBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void StatisticsOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       try {
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/fxml/SetPaymentDateScene.fxml"));
        Parent root2 = loader2.load();

        Scene scene2 = new Scene(root2);

        setPaymentDateStage = new Stage();
        setPaymentDateStage.initModality(Modality.APPLICATION_MODAL);
        setPaymentDateStage.getIcons().add(new Image("file:images\\t4j.jpg"));
        setPaymentDateStage.setTitle("Set payment date");
        setPaymentDateStage.setResizable(false);
        setPaymentDateStage.setScene(scene2);
        setPaymentDateStage.initStyle(StageStyle.TRANSPARENT);


        SetPaymentDateUI setPaymentDateUI = loader2.getController();
        setPaymentDateUI.associateParentUI(this);

    } catch (
    IOException ioException) {
        Alert alert = AlertUI.createAlert(Alert.AlertType.ERROR, MainApp.APP_TITLE, "Error", ioException.getMessage());
        System.out.println(ioException.getLocalizedMessage());
        alert.show();
    }
    }
}
