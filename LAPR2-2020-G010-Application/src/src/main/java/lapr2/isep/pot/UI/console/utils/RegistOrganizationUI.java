package lapr2.isep.pot.UI.console.utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lapr2.isep.pot.controller.ApplicationController;
import lapr2.isep.pot.controller.ApplicationPOT;
import lapr2.isep.pot.controller.RegistOrganizationController;
import lapr2.isep.pot.model.Collaborator;
import lapr2.isep.pot.model.Manager;
import lapr2.isep.pot.model.Organization;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistOrganizationUI implements Initializable {

    private AdministratorMenuUI administratorMenuUI;


    private ApplicationController applicationController;

    private RegistOrganizationController registOrganizationController;

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

    public RegistOrganizationUI() throws FileNotFoundException {
        this.applicationController = new ApplicationController();
        this.registOrganizationController = new RegistOrganizationController();
    }

    @FXML
    void XOnAction(ActionEvent event) {
        Alert alert = AlertUI.createAlert(Alert.AlertType.CONFIRMATION, this.applicationController.getAppName(),
                "Action confirmation.", "Do you really want to close the application?");
        if (alert.showAndWait().get() == ButtonType.CANCEL) {
            event.consume();
        } else {
            applicationController.saveInfo();
            System.exit(0);
        }
    }

    /**
     * 1 -> Collaborator role
     * 2 -> Manager role
     * @param event
     */
    @FXML
    void RegistOnAction(ActionEvent event) {
        try {
                if (this.registOrganizationController.hasOrganization(new Organization(organizationName.getText(), organizationNIF.getText()))) {
                    Alert alert = AlertUI.createAlert(Alert.AlertType.WARNING, this.applicationController.getAppName(), "Error", "The organization inserted is already in the system.");
                    alert.show();
                } else {
                    this.registOrganizationController.addOrganization(new Organization(organizationName.getText(), organizationNIF.getText(), new Collaborator(collaboratorName.getText(), collaboratorEmail.getText()), new Manager(managerName.getText(), managerEmail.getText())));
                    Alert alert = AlertUI.createAlert(Alert.AlertType.INFORMATION, this.applicationController.getAppName(), organizationName.getText() , "Organization added.");
                    alert.show();
                    organizationsListVIew.getItems().setAll(this.registOrganizationController.getListOrganizations());
                }
        } catch (IllegalArgumentException | IOException exception) {
            Alert alert = AlertUI.createAlert(Alert.AlertType.ERROR, this.applicationController.getAppName(), "Error", "The arguments cant be null or empty.");
            alert.show();
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

    public void associateParentUI(AdministratorMenuUI administratorMenuUI) {
        this.administratorMenuUI = administratorMenuUI;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO
        //applicationController = new ApplicationController();
        organizationsListVIew.getItems().setAll(registOrganizationController.getListOrganizations());

    }

    public void clearTextFields() {
        organizationName.clear();
        organizationNIF.clear();
        managerName.clear();
        managerEmail.clear();
        collaboratorName.clear();
        collaboratorEmail.clear();
    }

    public RegistOrganizationController getRegistOrganizationController() {
        return this.registOrganizationController;
    }
}
