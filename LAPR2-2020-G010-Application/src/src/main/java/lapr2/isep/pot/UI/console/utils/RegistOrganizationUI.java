package lapr2.isep.pot.UI.console.utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lapr2.isep.pot.UI.console.MainApp;
import lapr2.isep.pot.controller.ApplicationController;
import lapr2.isep.pot.controller.RegistOrganizationController;
import lapr2.isep.pot.model.Collaborator;
import lapr2.isep.pot.model.Manager;
import lapr2.isep.pot.model.Organization;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistOrganizationUI implements Initializable {

    private LogInUI logInUI;

    private ApplicationController applicationController;

    private final RegistOrganizationController registOrganizationController = new RegistOrganizationController();

    double x = 0;
    double y = 0;

    @FXML
    private TextField managerEmail;

    @FXML
    private Button xBtn;

    @FXML
    private TextField organizationName;

    @FXML
    private TextField organizationNIF;

    @FXML
    private TextField collaboratorEmail;

    @FXML
    private TextField managerName;

    @FXML
    private Button goBackBtn;

    @FXML
    private TextField collaboratorName;

    @FXML
    private Button registBtn;

    @FXML
    private ListView<Organization> organizationsListVIew;

    @FXML
    void XOnAction(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    void RegistOnAction(ActionEvent event) {
        if (organizationName.getText() == null || organizationNIF.getText() == null || managerName.getText() == null || managerEmail.getText() == null || collaboratorName.getText() == null || collaboratorEmail.getText() == null ) {
            Alert alert = AlertUI.createAlert(Alert.AlertType.INFORMATION, applicationController.getAppName(), "Error", "Incorrect data. Verify again the insert data.");
            alert.show();
        } else {
            if (registOrganizationController.hasOrganization(new Organization(organizationName.getText(), organizationNIF.getText()))) {
                Alert alert = AlertUI.createAlert(Alert.AlertType.INFORMATION, applicationController.getAppName(), "Error", "Incorrect data. The organization is already in the system.");
                alert.show();
            } else {
                registOrganizationController.addOrganization(new Organization(organizationName.getText(), organizationNIF.getText(), new Collaborator(collaboratorName.getText(), collaboratorEmail.getText()), new Manager(managerName.getText(), managerEmail.getText())));
                System.out.println("Adicionada");
                organizationsListVIew.getItems().setAll(registOrganizationController.getListOrganizations());
            }
        }
    }

    @FXML
    void GoBackOnAction(ActionEvent event) {
        clearTextFields();
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

    public void clearTextFields() {
        organizationName.clear();
        organizationNIF.clear();
        managerName.clear();
        managerEmail.clear();
        collaboratorName.clear();
        collaboratorEmail.clear();
    }
}
