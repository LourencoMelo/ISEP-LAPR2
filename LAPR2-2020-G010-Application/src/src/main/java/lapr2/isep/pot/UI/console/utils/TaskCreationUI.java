package lapr2.isep.pot.UI.console.utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lapr2.isep.pot.controller.ApplicationController;
import lapr2.isep.pot.controller.TaskCreationController;
import lapr2.isep.pot.model.*;
import lapr2.isep.pot.model.List.TaskList;

import java.io.IOException;

public class TaskCreationUI {

    private CollaboratorMenuUI collaboratorMenuUI;

    private final TaskCreationController taskCreationController = new TaskCreationController();

    private ApplicationController applicationController = ApplicationController.getApplicationController();

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
            Task task = taskCreationController.newTask(taskId.getText(), taskDescription.getText(), Double.parseDouble(taskTimeDuration.getText()), Double.parseDouble(taskCostPerHour.getText()), taskCategory.getText());
            if (taskCreationController.getTaskValidation(taskId.getText())) {
                taskCreationController.taskCreation(task);
                Alert alert = AlertUI.createAlert(Alert.AlertType.INFORMATION, applicationController.getAppName(), taskId.getText(), "Task added.");
                alert.show();
                tasksListVIew.getItems().setAll(taskCreationController.getTaskList());
            } else {
                Alert alert = AlertUI.createAlert(Alert.AlertType.WARNING, applicationController.getAppName(), "Error", "The task inserted is already in the system.");
                alert.show();
            }
        }catch (IllegalArgumentException exception) {
            Alert alert = AlertUI.createAlert(Alert.AlertType.ERROR, applicationController.getAppName(), "Error", "Arguments must follow the following rules:" +
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
}

