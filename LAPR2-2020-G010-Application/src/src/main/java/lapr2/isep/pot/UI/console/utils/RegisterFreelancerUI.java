package lapr2.isep.pot.UI.console.utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lapr2.isep.pot.controller.ApplicationController;
import lapr2.isep.pot.controller.RegisterFreelancerController;


public class RegisterFreelancerUI {

    private CollaboratorMenuUI collaboratorMenuUI;

    private final RegisterFreelancerController registerFreelancerController = new RegisterFreelancerController();

    private ApplicationController applicationController = ApplicationController.getApplicationController();

    double x = 0;
    double y = 0;

    @FXML
    private TextField freelancerLvlOfExpertise;

    @FXML
    private TextField freelancerID;

    @FXML
    private ListView<?> freelancersListVIew;

    @FXML
    private Button xBtn;

    @FXML
    private TextField freelancerIBAN;

    @FXML
    private TextField freelancerEmail;

    @FXML
    private Button goBackBtn;

    @FXML
    private TextField freelancerAddress;

    @FXML
    private TextField freelancerName;

    @FXML
    private TextField freelancerNIF;

    @FXML
    private Button registBtn;

    @FXML
    private TextField freelancerCountry;

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
    void RegistOnAction(ActionEvent event) {
        registerFreelancerController.newFreelancer(freelancerID.getText(), freelancerName.getText(), freelancerLvlOfExpertise.getText(), freelancerEmail.getText(), freelancerNIF.getText(), freelancerIBAN.getText(), freelancerAddress.getText(), freelancerCountry.getText());
    }

    public void associateParentUI(CollaboratorMenuUI collaboratorMenuUI) {
        this.collaboratorMenuUI = collaboratorMenuUI;
    }

    @FXML
    void GoBackOnAction(ActionEvent event) {
        clearTextFields();
        Stage stage = (Stage) registBtn.getScene().getWindow();
        stage.close();
    }


    public void clearTextFields() {
        freelancerID.clear();
        freelancerName.clear();
        freelancerLvlOfExpertise.clear();
        freelancerEmail.clear();
        freelancerNIF.clear();
        freelancerIBAN.clear();
        freelancerAddress.clear();
        freelancerCountry.clear();
    }

}
