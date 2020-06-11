package lapr2.isep.pot.UI.console.utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lapr2.isep.pot.controller.ApplicationController;
import lapr2.isep.pot.controller.TaskCreationController;
import lapr2.isep.pot.model.*;

import java.net.URL;
import java.util.ResourceBundle;

public class TaskCreationUI implements Initializable {

    private CollaboratorMenuUI collaboratorMenuUI;

    private TaskCreationController taskCreationController;

    private ApplicationController applicationController;

    double x = 0;
    double y = 0;

    @FXML
    private TextField taskCostPerHour;

    @FXML
    private Button xBtn;

    @FXML
    private TextField taskDescription;

    @FXML
    private Button createBtn;

    @FXML
    private TextField taskTimeDuration;

    @FXML
    private ListView<Task> tasksListVIew;

    @FXML
    private Button goBackBtn;

    @FXML
    private TextField taskId;

    @FXML
    private TextField taskCategory;

    public TaskCreationUI(){
        this.taskCreationController = new TaskCreationController();
        this.applicationController = new ApplicationController();
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
    void CreateOnAction(ActionEvent event) {
        try {
            this.taskCreationController.newTask(taskId.getText(), taskDescription.getText(), Double.parseDouble(taskTimeDuration.getText()), Double.parseDouble(taskCostPerHour.getText()), taskCategory.getText());
            if (this.taskCreationController.getTaskValidation(taskId.getText())) {
                this.taskCreationController.taskCreation();
                Alert alert = AlertUI.createAlert(Alert.AlertType.INFORMATION, this.applicationController.getAppName(), taskId.getText(), "Task added.");
                alert.show();
                tasksListVIew.getItems().setAll(taskCreationController.getTaskLists());
            } else {
                Alert alert = AlertUI.createAlert(Alert.AlertType.WARNING, this.applicationController.getAppName(), "Error", "The task inserted is already in the system.");
                alert.show();
            }
        }catch (IllegalArgumentException exception) {
            Alert alert = AlertUI.createAlert(Alert.AlertType.ERROR, this.applicationController.getAppName(), "Error", "Arguments must follow the following rules:" +
                                                                                                                                 "\n * Arguments can't be null or empty;" +
                                                                                                                                 "\n * Time duration and Cost per hour must be numbers.");
            alert.show();
        }
    }

    @FXML
    void GoBackOnAction(ActionEvent event) {
        clearTextFields();
        Stage stage = (Stage) createBtn.getScene().getWindow();
        stage.close();
    }

    public void clearTextFields() {
        taskId.clear();
        taskDescription.clear();
        taskTimeDuration.clear();
        taskCostPerHour.clear();
        taskCategory.clear();
    }

    public void associateParentUI(CollaboratorMenuUI collaboratorMenuUI) {
        this.collaboratorMenuUI = collaboratorMenuUI;
    }

    public TaskCreationController getTaskCreationController() {
        return taskCreationController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

