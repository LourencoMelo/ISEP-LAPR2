package lapr2.isep.pot.UI.console.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lapr2.isep.pot.UI.console.MainApp;
import lapr2.isep.pot.controller.ApplicationController;
import lapr2.isep.pot.controller.RegistOrganizationController;
import lapr2.isep.pot.controller.TaskCreationController;


public class LogInUI implements Initializable {

    public static final String NAME_ADMIN = "a";

    public static final String EMAIL_ADMIN = "a";

    public static final String PASSWORD_ADMIN = "a";

    private ApplicationController applicationController;

    private RegistOrganizationController registOrganizationController;

    private TaskCreationController taskCreationController;
    private Stage administratorMenuStage;
    private Stage collaboratorMenuStage;

    public LogInUI() throws FileNotFoundException {
        this.registOrganizationController = new RegistOrganizationController();
        this.applicationController = new ApplicationController();
        //this.taskCreationController = new TaskCreationController();
    }


    double x = 0;
    double y = 0;

    @FXML
    private TextField emailTxtField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField nameTextField;

    @FXML
    private Button logInBtn;

    @FXML
    private Button xBtn;

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
    void LogInOnAction(ActionEvent event) throws FileNotFoundException {

        if (!this.registOrganizationController.userExist(emailTxtField.getText(), passwordField.getText()) && !isAdminLoggingIn(nameTextField.getText(), emailTxtField.getText(), passwordField.getText())) {
            Alert alert = AlertUI.createAlert(Alert.AlertType.WARNING, this.applicationController.getAppName(), "Something went wrong.", "Name, email or password incorrect.");
            alert.show();
        } else {
            if (isAdminLoggingIn(nameTextField.getText(), emailTxtField.getText(), passwordField.getText())) {
                administratorMenuStage.show();
            } else if (this.registOrganizationController.isCollaboratorLoggingIn(this.registOrganizationController.createUser(nameTextField.getText(), emailTxtField.getText(), passwordField.getText()))) {
                collaboratorMenuStage.show();
            } else if (this.registOrganizationController.isManagerLoggingIn(this.registOrganizationController.createUser(nameTextField.getText(), emailTxtField.getText(), passwordField.getText()))) {
            } else{
                Alert alert = AlertUI.createAlert(Alert.AlertType.WARNING, applicationController.getAppName(), "Something went wrong.", "Name, email or password incorrect.");
                alert.show();
            }

        }

    }

    @FXML
    void XOnAction(ActionEvent event) {
        Alert alert = AlertUI.createAlert(Alert.AlertType.CONFIRMATION, applicationController.getAppName(),
                "Action confirmation.", "Do you really want to close the application?");
        if (alert.showAndWait().get() == ButtonType.CANCEL) {
            event.consume();
        } else {
            //registOrganizationController.getPlatform().saveInfo(registOrganizationController.getPlatform());
            System.exit(0);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AdministratorMenuScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            administratorMenuStage = new Stage();
            administratorMenuStage.initModality(Modality.APPLICATION_MODAL);
            administratorMenuStage.getIcons().add(new Image("file:images\\t4j.jpg"));
            administratorMenuStage.setTitle("Regist Organization");
            administratorMenuStage.setResizable(false);
            administratorMenuStage.setScene(scene);
            administratorMenuStage.initStyle(StageStyle.TRANSPARENT);


            AdministratorMenuUI administratorMenuUI = loader.getController();
            administratorMenuUI.associateParentUI(this);

//----------------------------------------------------------------------------------------------------------------------------------------------

            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/fxml/CollaboratorMenuScene.fxml"));
            Parent root1 = loader1.load();

            Scene scene1 = new Scene(root1);

            collaboratorMenuStage = new Stage();
            collaboratorMenuStage.initModality(Modality.APPLICATION_MODAL);
            collaboratorMenuStage.getIcons().add(new Image("file:images\\t4j.jpg"));
            collaboratorMenuStage.setTitle("Collaborator Menu");
            collaboratorMenuStage.setResizable(false);
            collaboratorMenuStage.setScene(scene1);
            collaboratorMenuStage.initStyle(StageStyle.TRANSPARENT);


            CollaboratorMenuUI collaboratorMenuUI = loader1.getController();
            collaboratorMenuUI.associateParentUI(this);

        } catch (IOException ioException) {
            Alert alert = AlertUI.createAlert(Alert.AlertType.ERROR, MainApp.APP_TITLE, "Error", ioException.getMessage());
            alert.show();
        }
    }


    public boolean isAdminLoggingIn(String name, String email, String password) {
        return name.equals(NAME_ADMIN) && email.equals(EMAIL_ADMIN) && password.equals(PASSWORD_ADMIN);
    }
}
