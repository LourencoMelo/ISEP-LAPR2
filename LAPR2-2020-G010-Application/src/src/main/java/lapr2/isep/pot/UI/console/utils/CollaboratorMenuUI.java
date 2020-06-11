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
import lapr2.isep.pot.controller.TaskCreationController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CollaboratorMenuUI implements Initializable {

    private LogInUI logInUI;

    private ApplicationController applicationController;

    private TaskCreationController taskCreationController;
    private Stage registFreelancerStage;

    private Stage createTaskStage;

    private Stage createPaymentTransactionStage;


    double x = 0;
    double y = 0;


    @FXML
    private Button statisticsBtn1;

    @FXML
    private Button xBtn;

    @FXML
    private Button CreatePaymentTransactionBtn1;

    @FXML
    private Button goBackBtn;

    @FXML
    private Button registerFreelancerBtn;

    @FXML
    private Button createTaskBtn;

    public CollaboratorMenuUI(){
        this.applicationController = new ApplicationController();
        //this.taskCreationController = new TaskCreationController();
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

    public void associateParentUI(LogInUI logInUI) {
        this.logInUI = logInUI;
    }

    @FXML
    private void RegisterFreelancerOnAction(ActionEvent event) {
        registFreelancerStage.show();
    }

    @FXML
    private void CreateTaskOnAction(ActionEvent event) {
        createTaskStage.show();
    }

    @FXML
    private void CreatePaymentTransactionOnAction(ActionEvent event) {
        createPaymentTransactionStage.show();
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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RegisterFreelancerScene.fxml"));
            Parent root = loader.load();

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

    //---------------------------------------------------------------------------------------------------------------------
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/fxml/CreateTaskScene.fxml"));
            Parent root1 = loader1.load();

            Scene scene1 = new Scene(root1);

            createTaskStage = new Stage();
            createTaskStage.initModality(Modality.APPLICATION_MODAL);
            createTaskStage.getIcons().add(new Image("file:images\\t4j.jpg"));
            createTaskStage.setTitle("Create Task");
            createTaskStage.setResizable(false);
            createTaskStage.setScene(scene1);
            createTaskStage.initStyle(StageStyle.TRANSPARENT);

            TaskCreationUI taskCreationUI = loader1.getController();
            taskCreationUI.associateParentUI(this);

    //----------------------------------------------------------------------------------------------------------------------
            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/fxml/CreatePaymentTransactionScene.fxml"));
            Parent root2 = loader2.load();

            Scene scene2 = new Scene(root2);

            createPaymentTransactionStage = new Stage();
            createPaymentTransactionStage.initModality(Modality.APPLICATION_MODAL);
            createPaymentTransactionStage.getIcons().add(new Image("file:images\\t4j.jpg"));
            createPaymentTransactionStage.setTitle("Create Payment Transaction");
            createPaymentTransactionStage.setResizable(false);
            createPaymentTransactionStage.setScene(scene2);
            createPaymentTransactionStage.initStyle(StageStyle.TRANSPARENT);

            CreatePaymentTransactionUI createPaymentTransactionUI = loader2.getController();
            createPaymentTransactionUI.associateParentUI(this);

        } catch (IOException e) {
            AlertUI.createAlert(Alert.AlertType.INFORMATION, this.applicationController.getAppName() , "IO Exception found ", e.getMessage());
        }

    }
}




