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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;
import lapr2.isep.pot.UI.console.utils.AlertUI;
import lapr2.isep.pot.UI.console.utils.LogInUI;
import lapr2.isep.pot.controller.ApplicationController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CollaboratorMenuUI implements Initializable {

    private LogInUI logInUI;

    private ApplicationController applicationController = ApplicationController.getApplicationController();

    private Stage registFreelancerStage;


    double x = 0;
    double y = 0;

    @FXML
    private Button statisticsBtn1;

    @FXML
    private Button xBtn;

    @FXML
    private Button goBackBtn;

    @FXML
    private Button registerFreelancerBtn;

    @FXML
    private Button createTaskBtn;

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

    public void associateParentUI(LogInUI logInUI) {
        this.logInUI = logInUI;
    }

    @FXML
    void RegisterFreelancerOnAction(ActionEvent event) {
        registFreelancerStage.show();
    }

    @FXML
    void CreateTaskOnAction(ActionEvent event) {

    }

    @FXML
    void GoBackOnAction(ActionEvent event) {
        Stage stage = (Stage) registerFreelancerBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void StatisticsOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RegisterFreelancerScene.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);

        registFreelancerStage = new Stage();
        registFreelancerStage.initModality(Modality.APPLICATION_MODAL);
        registFreelancerStage.getIcons().add(new Image("file:images\\t4j.jpg"));
        registFreelancerStage.setTitle("Regist Freelancer");
        registFreelancerStage.setResizable(false);
        registFreelancerStage.setScene(scene);
        registFreelancerStage.initStyle(StageStyle.TRANSPARENT);

        RegisterFreelancerUI registerFreelancerUI = loader.getController();
        registerFreelancerUI.associateParentUI(this);
    }
}




