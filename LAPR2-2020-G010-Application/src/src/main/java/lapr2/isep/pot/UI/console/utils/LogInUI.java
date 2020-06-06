package lapr2.isep.pot.UI.console.utils;

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


public class LogInUI implements Initializable {

    public static final String EMAIL_ADMIN = "admin.t4j@business.com";

    public static final String PASSWORD_ADMIN = "admin123";

    static ApplicationController applicationController;
    private RegistOrganizationController registOrganizationController;
    private Stage administratorMenuStage;
    private Stage collaboratorMenuStage;


    double x = 0;
    double y = 0;

    @FXML
    private TextField emailTxtField;

    @FXML
    private PasswordField passwordField;

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
    void LogInOnAction(ActionEvent event) {                 /** COMMENTED TO BE EASIER TO CHECK OTHER WINDOWS */
//            if (applicationController.userExist(emailTxtField.getText(), passwordField.getText()) || !isAdminLoggingIn(emailTxtField.getText(), passwordField.getText())) {
//                Alert alert = AlertUI.createAlert(Alert.AlertType.WARNING, applicationController.getAppName(), "Something went wrong.", "Email or password incorrect.");
//                alert.show();
//            } else {
//                if (isAdminLoggingIn(emailTxtField.getText(), passwordField.getText())) {
//                    System.out.println("Registe uma organização!");
//                    administratorMenuStage.show();
//                }
//            }
        //administratorMenuStage.show();
        collaboratorMenuStage.show();
    }

    @FXML
    void XOnAction(ActionEvent event) {
        Alert alert = AlertUI.createAlert(Alert.AlertType.CONFIRMATION, applicationController.getAppName(),
                "Action confirmation.", "Do you really want to close the application?");
        if (alert.showAndWait().get() == ButtonType.CANCEL) {
            event.consume();
        } else {
            registOrganizationController.saveInfo();
            System.exit(0);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        applicationController = new ApplicationController();
        registOrganizationController = RegistOrganizationController.getRegistOrganizationController();
        try {
            //<editor-fold desc="AdministratorMenu scene">
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

            applicationController = getApplicationController();
            AdministratorMenuUI administratorMenuUI = loader.getController();
            administratorMenuUI.associateParentUI(this);
            //</editor-fold>"
        }catch (IOException ex) {
            Alert alert = AlertUI.createAlert(Alert.AlertType.ERROR, MainApp.APP_TITLE, "Error.", ex.getMessage());
            alert.show();
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CollaboratorMenuScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            collaboratorMenuStage = new Stage();
            collaboratorMenuStage.initModality(Modality.APPLICATION_MODAL);
            collaboratorMenuStage.getIcons().add(new Image("file:images\\t4j.jpg"));
            collaboratorMenuStage.setTitle("Collaborator Menu");
            collaboratorMenuStage.setResizable(false);
            collaboratorMenuStage.setScene(scene);
            collaboratorMenuStage.initStyle(StageStyle.TRANSPARENT);

            applicationController = getApplicationController();
            CollaboratorMenuUI collaboratorMenuUI = loader.getController();
            collaboratorMenuUI.associateParentUI(this);

        } catch (IOException ioException) {
            Alert alert = AlertUI.createAlert(Alert.AlertType.ERROR, MainApp.APP_TITLE, "Error.", ioException.getMessage());
            alert.show();
        }
    }

    public static ApplicationController getApplicationController() {
        return applicationController;
    }

    public boolean isAdminLoggingIn(String email, String password) {
        return email.equals(EMAIL_ADMIN) && password.equals(PASSWORD_ADMIN);
    }
}
