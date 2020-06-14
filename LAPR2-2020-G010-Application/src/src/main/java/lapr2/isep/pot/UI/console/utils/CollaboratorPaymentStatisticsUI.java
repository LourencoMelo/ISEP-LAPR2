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
import lapr2.isep.pot.controller.CollaboratorStatisticsController;
import lapr2.isep.pot.model.Freelancer;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class CollaboratorPaymentStatisticsUI implements Initializable {

    private CollaboratorMenuUI collaboratorMenuUI;


    @FXML
    private ListView<Freelancer> freelancersListView;

    @FXML
    private Button xBtn;

    @FXML
    private TextField standardDeviationTxtField;

    @FXML
    private Button SeeStatisticsForFreelancerBtn;

    @FXML
    private Button showFreelancersBtn;

    @FXML
    private TextField meanTxtField;

    @FXML
    private Button goBackBtn;

    @FXML
    private ComboBox<String> sortTypesCmb;

    private ObservableList<String> typesToSort = FXCollections.observableArrayList("Name", "Payment Value");

    @FXML
    private Button clearStatsBtn;

    private ApplicationController applicationController;

    private List<String> freelancerChoosedBefore = new ArrayList<>();

    private CollaboratorStatisticsController collaboratorStatisticsController;

    double xwindow = 0;
    double ywindow = 0;

    public CollaboratorPaymentStatisticsUI() throws FileNotFoundException {
        this.applicationController = new ApplicationController();
        this.collaboratorStatisticsController = new CollaboratorStatisticsController();
    }

    @FXML
    void dragged(MouseEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        stage.setX(event.getScreenX() - xwindow);
        stage.setY(event.getScreenY() - ywindow);
    }

    @FXML
    void pressed(MouseEvent event) {
        xwindow = event.getSceneX();
        ywindow = event.getSceneY();
    }

    @FXML
    void ShowFreelancersOnAction(ActionEvent event) {
        if (collaboratorStatisticsController.getListFreelancersToListView().size() == 0) {
            Alert alert = AlertUI.createAlert(Alert.AlertType.WARNING, this.applicationController.getAppName(), "Error", "There are no freelancers in the system.");
            alert.show();
        } else {
            freelancersListView.getItems().setAll(collaboratorStatisticsController.getListFreelancersToListView());
        }
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

    @FXML
    void GoBackOnAction(ActionEvent event) {
        Stage stage = (Stage) showFreelancersBtn.getScene().getWindow();
        standardDeviationTxtField.clear();
        meanTxtField.clear();
        freelancerChoosedBefore.clear();
        stage.close();
    }

    @FXML
    void SeeStatisticsForFreelancerOnAction(ActionEvent event) {
        if (freelancersListView.getSelectionModel().getSelectedItem() == null) {
            Alert alert = AlertUI.createAlert(Alert.AlertType.WARNING, this.applicationController.getAppName(), "Error", "You must select a freelancer.");
            alert.show();
        } else if (freelancerChoosedBefore.contains(freelancersListView.getSelectionModel().getSelectedItem().getEmail())) {
            Alert alert = AlertUI.createAlert(Alert.AlertType.WARNING, this.applicationController.getAppName(), "Error", "You have already selected that freelancer.");
            alert.show();
        } else {
            meanTxtField.setText(String.valueOf(String.format("%.03f", collaboratorStatisticsController.meanPaymentsByFreelancer(freelancersListView.getSelectionModel().getSelectedItem()))));
            standardDeviationTxtField.setText(String.valueOf(String.format("%.03f", collaboratorStatisticsController.standardDeviationPaymentsByFreelancer(freelancersListView.getSelectionModel().getSelectedItem()))));
            freelancerChoosedBefore.add(freelancersListView.getSelectionModel().getSelectedItem().getEmail());
        }
    }

    @FXML
    void SortByOnAction(ActionEvent event) {
        if (sortTypesCmb.getSelectionModel().getSelectedItem() == null) {
            Alert alert = AlertUI.createAlert(Alert.AlertType.WARNING, this.applicationController.getAppName(), "Error", "You must select a sort type.");
            alert.show();
        } else {
            if (sortTypesCmb.getSelectionModel().getSelectedItem().equalsIgnoreCase("Name")) {
                //Collections.sort(collaboratorStatisticsController.getListFreelancersToListView());
            }
            if (sortTypesCmb.getSelectionModel().getSelectedItem().equalsIgnoreCase("Payment Value")) {

            }
        }
    }


    @FXML
    void clearStatsOnAction(ActionEvent event) {
        standardDeviationTxtField.clear();
        meanTxtField.clear();
        freelancerChoosedBefore.clear();
        sortTypesCmb.getSelectionModel().clearSelection();
    }

    public void associateParentUI2(CollaboratorMenuUI collaboratorMenuUI) {
        this.collaboratorMenuUI = collaboratorMenuUI;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sortTypesCmb.getItems().setAll(typesToSort);
    }
}
