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
import lapr2.isep.pot.controller.ApplicationController;
import lapr2.isep.pot.controller.RegistOrganizationController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdministratorMenuUI implements Initializable {

    private LogInUI logInUI;

    private ApplicationController applicationController;
    private RegistOrganizationController registOrganizationController;

    private Stage registOrganizationStage;

    private Stage graphSceneStage;

    double x = 0;
    double y = 0;

    @FXML
    private Button xBtn;

    @FXML
    private Button registerOrganizationBtn;

    @FXML
    private Button statisticsBtn;

    public AdministratorMenuUI() throws FileNotFoundException {
        this.applicationController = new ApplicationController();
        this.registOrganizationController = new RegistOrganizationController();
    }


    @FXML
    void XOnAction(ActionEvent event) {
        Alert alert = AlertUI.createAlert(Alert.AlertType.CONFIRMATION, applicationController.getAppName(),
                "Action confirmation.", "Do you really want to close the application?");
        if (alert.showAndWait().get() == ButtonType.CANCEL) {
            event.consume();
        } else {
            //registOrganizationController.getPlatform().saveInfo(registerOrganizationBtn.ge);
            System.exit(0);
        }
    }

    @FXML
    void RegisterOrganizationOnAction(ActionEvent event) {
        registOrganizationStage.show();
    }

    @FXML
    void StatisticsOnAction(ActionEvent event) {
        graphSceneStage.show();
    }

    @FXML
    void GoBackOnAction(ActionEvent event) {
        Stage stage = (Stage) registerOrganizationBtn.getScene().getWindow();
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
        try {

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

            RegistOrganizationUI registOrganizationUI = loader.getController();
            registOrganizationUI.associateParentUI(this);

            //======================================================================

            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/fxml/AdministratorStatisticsScene.fxml"));
            Parent root2 = loader2.load();

            Scene scene2 = new Scene(root2);

            graphSceneStage = new Stage();
            graphSceneStage.initModality(Modality.APPLICATION_MODAL);
            graphSceneStage.getIcons().add(new Image("file:images\\t4j.jpg"));
            graphSceneStage.setTitle("Statistics");
            graphSceneStage.setResizable(false);
            graphSceneStage.setScene(scene2);
            graphSceneStage.initStyle(StageStyle.TRANSPARENT);

            AdministratorStatisticsUI administratorStatisticsUI = loader2.getController();
            administratorStatisticsUI.associateParentUI(this);

        }catch (IOException ioException){
            AlertUI.createAlert(Alert.AlertType.INFORMATION, this.applicationController.getAppName() , "IO Exception found ", ioException.getMessage());

        }
    }
}
