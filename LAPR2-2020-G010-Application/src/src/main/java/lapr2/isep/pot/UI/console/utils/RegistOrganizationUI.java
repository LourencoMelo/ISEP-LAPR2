package lapr2.isep.pot.UI.console.utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lapr2.isep.pot.UI.console.MainApp;
import lapr2.isep.pot.controller.ApplicationController;
import lapr2.isep.pot.controller.RegistOrganizationController;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistOrganizationUI implements Initializable {

    private LogInUI logInUI;

    private ApplicationController applicationController;

    private final RegistOrganizationController registOrganizationController;

    double x = 0;
    double y = 0;

    @FXML
    private Button xBtn;

    @FXML
    private Button goBackBtn;

    @FXML
    private Button registBtn;

    public RegistOrganizationUI() {
        registOrganizationController = new RegistOrganizationController();
    }

    @FXML
    void XOnAction(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    void RegistOnAction(ActionEvent event) {

    }

    @FXML
    void GoBackOnAction(ActionEvent event) {
        Stage stage = (Stage) registBtn.getScene().getWindow();
        stage.close();
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

    public void associateParentUI(LogInUI logInUI) {
        this.logInUI = logInUI;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO
        //applicationController = new ApplicationController();
    }
}
