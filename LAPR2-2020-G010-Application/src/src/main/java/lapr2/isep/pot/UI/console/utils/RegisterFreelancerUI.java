package lapr2.isep.pot.UI.console.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lapr2.isep.pot.controller.ApplicationController;
import lapr2.isep.pot.controller.RegisterFreelancerController;
import lapr2.isep.pot.model.Freelancer;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;


public class RegisterFreelancerUI implements Initializable {

    private CollaboratorMenuUI collaboratorMenuUI;

    private RegisterFreelancerController registerFreelancerController;

    private ApplicationController applicationController;

    double x = 0;
    double y = 0;

    @FXML
    private ComboBox<String> levelOfExpertiseCmb;

    @FXML
    private TextField freelancerID;

    @FXML
    private ListView<Freelancer> freelancersListVIew;

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
    private Button refreshListBtn;

    private ObservableList<String> listLevels = FXCollections.observableArrayList("Junior", "Senior");

    public RegisterFreelancerUI() throws FileNotFoundException {
        this.registerFreelancerController = new RegisterFreelancerController();
        this.applicationController = new ApplicationController();
    }

    public RegisterFreelancerController getController() {
        return this.registerFreelancerController;
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
    void RegistOnAction(ActionEvent event) {
        try{
        Freelancer freelancer = this.registerFreelancerController.newFreelancer(freelancerID.getText(), freelancerName.getText(), levelOfExpertiseCmb.getValue(), freelancerEmail.getText(), freelancerNIF.getText(), freelancerIBAN.getText(), freelancerAddress.getText(), freelancerCountry.getText());
        if (this.registerFreelancerController.getValidationFreelancer(freelancer)) {
            this.registerFreelancerController.registFreelancer();
            Alert alert = AlertUI.createAlert(Alert.AlertType.INFORMATION, this.applicationController.getAppName(), freelancerID.getText() , "Freelancer added.");
            alert.show();
            freelancersListVIew.getItems().setAll(this.registerFreelancerController.getListFreelancer());
        }else {
            Alert alert = AlertUI.createAlert(Alert.AlertType.WARNING, this.applicationController.getAppName(), "Error", "The freelancer inserted is already in the system.");
            alert.show();
        }
        }catch (IllegalArgumentException exception) {
            Alert alert = AlertUI.createAlert(Alert.AlertType.ERROR, this.applicationController.getAppName(), "Error", "Arguments must follow the following rules:" +
                    "\n * Arguments can't be null or empty;");
            alert.show();
        }
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
        freelancerEmail.clear();
        freelancerNIF.clear();
        freelancerIBAN.clear();
        freelancerAddress.clear();
        freelancerCountry.clear();
        freelancersListVIew.getItems().clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        levelOfExpertiseCmb.setItems(listLevels);
    }

    public RegisterFreelancerController getRegisterFreelancerController() {
        return this.registerFreelancerController;
    }

    @FXML
    void RefreshOnAction(ActionEvent event) {
        freelancersListVIew.getItems().setAll(this.registerFreelancerController.getListFreelancer());
    }
}
