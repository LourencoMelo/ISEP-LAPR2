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
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lapr2.isep.pot.controller.ApplicationController;
import lapr2.isep.pot.controller.TaskCreationController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CollaboratorMenuUI implements Initializable {

    private static final String IMPORT_HEADER = "Import List.";

    private LogInUI logInUI;

    private ApplicationController applicationController;

    private TaskCreationController taskCreationController;
    private Stage registFreelancerStage;

    private Stage createTaskStage;

    private Stage createPaymentTransactionStage;

    private Stage collaboratorStatisticsStage;

    private Stage collaboratorPaymentStatisticsStage;

    double x = 0;
    double y = 0;

    @FXML
    private MenuItem mnuExport;

    @FXML
    private Button statisticsBtn1;

    @FXML
    private Button paymentStatisticsBtn11;

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

    public CollaboratorMenuUI() throws FileNotFoundException {
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
            applicationController.saveInfo();
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
    void paymentStatisticsOnAction(ActionEvent event) {
        collaboratorPaymentStatisticsStage.show();
    }

    @FXML
    void StatisticsOnAction(ActionEvent event) {
        collaboratorStatisticsStage.show();
    }

    @FXML
    void GoBackOnAction(ActionEvent event) {
        Stage stage = (Stage) registerFreelancerBtn.getScene().getWindow();
        stage.close();
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
            //----------------------------------------------------------------------------------------------------------------------
            FXMLLoader loader3 = new FXMLLoader(getClass().getResource("/fxml/CollaboratorStatisticsScene.fxml"));
            Parent root3 = loader3.load();

            Scene scene3 = new Scene(root3);

            collaboratorStatisticsStage = new Stage();
            collaboratorStatisticsStage.initModality(Modality.APPLICATION_MODAL);
            collaboratorStatisticsStage.getIcons().add(new Image("file:images\\t4j.jpg"));
            collaboratorStatisticsStage.setTitle("Task's Execution Times Statistics");
            collaboratorStatisticsStage.setResizable(false);
            collaboratorStatisticsStage.setScene(scene3);
            collaboratorStatisticsStage.initStyle(StageStyle.TRANSPARENT);

            CollaboratorStatisticsUI collaboratorStatisticsUI = loader3.getController();
            collaboratorStatisticsUI.associateParentUI(this);

            //---------------------------------------------------------------------------------------------------------------------
            FXMLLoader loader4 = new FXMLLoader(getClass().getResource("/fxml/CollaboratorPaymentStatisticsScene.fxml"));
            Parent root4 = loader4.load();

            Scene scene4 = new Scene(root4);

            collaboratorPaymentStatisticsStage = new Stage();
            collaboratorPaymentStatisticsStage.initModality(Modality.APPLICATION_MODAL);
            collaboratorPaymentStatisticsStage.getIcons().add(new Image("file:images\\t4j.jpg"));
            collaboratorPaymentStatisticsStage.setTitle("Payment Statistics");
            collaboratorPaymentStatisticsStage.setResizable(false);
            collaboratorPaymentStatisticsStage.setScene(scene4);
            collaboratorPaymentStatisticsStage.initStyle(StageStyle.TRANSPARENT);

            CollaboratorPaymentStatisticsUI collaboratorPaymentStatisticsUI = loader4.getController();
            collaboratorPaymentStatisticsUI.associateParentUI2(this);

        } catch (IOException e) {
            AlertUI.createAlert(Alert.AlertType.INFORMATION, this.applicationController.getAppName(), "IO Exception found ", e.getMessage());
        }

    }


    @FXML
    void mnuTransactionsImportCsvOnAction(ActionEvent event) {
        try {
            FileChooser fileChooser = FileChooserTransactionUI.createFileChooserPaymentListCsv();
            File fileToImport = fileChooser.showOpenDialog(registerFreelancerBtn.getScene().getWindow());
            if (fileToImport != null) {
                AlertUI.createAlert(Alert.AlertType.INFORMATION, applicationController.getAppName(), IMPORT_HEADER, "Transactions imported successfully.").show();
                applicationController.readCsvFile(fileToImport);
            } else {
                AlertUI.createAlert(Alert.AlertType.ERROR, applicationController.getAppName(), IMPORT_HEADER,
                        "It was not selected any file!").show();
            }
        } catch (Exception e) {
            AlertUI.createAlert(Alert.AlertType.ERROR, applicationController.getAppName(), IMPORT_HEADER,
                    "Error importing the file content").show();
        }

    }


    @FXML
    void mnuTransactionsImportTxtOnAction(ActionEvent event) {
        try {
            FileChooser fileChooser = FileChooserTransactionUI.createFileChooserPaymentListTxt();
            File fileToImport = fileChooser.showOpenDialog(registerFreelancerBtn.getScene().getWindow());
            if (fileToImport != null) {
                applicationController.readTxtFile(fileToImport);
                AlertUI.createAlert(Alert.AlertType.INFORMATION, applicationController.getAppName(), IMPORT_HEADER, "Transactions imported successfully.").show();
            } else {
                AlertUI.createAlert(Alert.AlertType.ERROR, applicationController.getAppName(), IMPORT_HEADER,
                        "It was not selected any file!").show();
            }
        } catch (Exception e) {
            AlertUI.createAlert(Alert.AlertType.ERROR, applicationController.getAppName(), IMPORT_HEADER,
                    "Error importing the file content").show();
        }
    }

    @FXML
    void mnuOptionsAboutAction(ActionEvent event) {
        AlertUI.createAlert(Alert.AlertType.INFORMATION, this.applicationController.getAppName(), "About", "@Copyright\nLAPR2 2019/2020" +
                "\nJosé Soares" +
                "\nJoão Beires" +
                "\nJosé Maia" +
                "\nGonçalo Ferreira" +
                "\nLourenço Melo").show();
    }
}




