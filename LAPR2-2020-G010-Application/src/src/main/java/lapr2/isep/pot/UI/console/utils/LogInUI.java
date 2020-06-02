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
import lapr2.isep.pot.model.AdminUser;


public class LogInUI implements Initializable {

    static ApplicationController applicationController;

    private Stage registOrganizationStage;

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
        //if (emailTxtField.getText().equals(AdminUser.emailAdmin) && passwordField.getText().equals(AdminUser.passwordAdmin)) {
            //System.out.println("Registe uma organização!");
            registOrganizationStage.show();
        //} else {
          //  Alert alert = AlertUI.createAlert(Alert.AlertType.WARNING, MainApp.APP_TITLE, "Something went wrong.", "Email or password incorrect.");
          // alert.show();
        //}
    }

    @FXML
    void XOnAction(ActionEvent event) {
        Alert alert = AlertUI.createAlert(Alert.AlertType.CONFIRMATION, MainApp.APP_TITLE,
                "Action confirmation.", "Do you really want to close the application?");
        if (alert.showAndWait().get() == ButtonType.CANCEL) {
            event.consume();
        } else {
            System.exit(0);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        applicationController = new ApplicationController();
        try {
            //<editor-fold desc="RegistOrganization scene">
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RegistOrganizationScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            registOrganizationStage = new Stage();
            registOrganizationStage.initModality(Modality.APPLICATION_MODAL);
            registOrganizationStage.getIcons().add(new Image("file:images\\t4j.jpg"));
            registOrganizationStage.setTitle("Regist Organization");
            registOrganizationStage.setResizable(false);
            registOrganizationStage.setScene(scene);
            registOrganizationStage.initStyle(StageStyle.TRANSPARENT);

            applicationController = getApplicationController();
            RegistOrganizationUI registOrganizationUI = loader.getController();
            registOrganizationUI.associateParentUI(this);
            //</editor-fold>"
        }catch (IOException ex) { AlertUI.createAlert(Alert.AlertType.ERROR, MainApp.APP_TITLE, "Error.", ex.getMessage());
        }
    }

    public static ApplicationController getApplicationController() {
        return applicationController;
    }
}
