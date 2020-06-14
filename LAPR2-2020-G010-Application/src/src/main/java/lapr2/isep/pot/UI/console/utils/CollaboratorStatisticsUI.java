package lapr2.isep.pot.UI.console.utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lapr2.isep.pot.controller.ApplicationController;
import lapr2.isep.pot.controller.CollaboratorStatisticsController;
import lapr2.isep.pot.model.Freelancer;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CollaboratorStatisticsUI implements Initializable {

    private CollaboratorMenuUI collaboratorMenuUI;


    @FXML
    private Button SeeStatisticsForAllFreelancersBtn;

    @FXML
    private ListView<Freelancer> freelancersListView;

    @FXML
    private Button xBtn;

    @FXML
    private Button clearStatsBtn;

    @FXML
    private TextField standardDeviationTxtField;

    @FXML
    private Button SeeStatisticsForFreelancerBtn;

    @FXML
    private CategoryAxis x;

    @FXML
    private Button showFreelancersBtn;

    @FXML
    private NumberAxis y;

    @FXML
    private TextField meanTxtField;

    @FXML
    private Button goBackBtn;

    private List<String> freelancerChoosedBefore = new ArrayList<>();

    @FXML
    private BarChart<?, ?> delayChart;

    private ApplicationController applicationController;

    private CollaboratorStatisticsController collaboratorStatisticsController;

    double xwindow = 0;
    double ywindow = 0;

    public CollaboratorStatisticsUI() throws FileNotFoundException {
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
        Stage stage = (Stage) SeeStatisticsForAllFreelancersBtn.getScene().getWindow();
        delayChart.getData().clear();
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
            XYChart.Series set1 = new XYChart.Series<>();
            set1.getData().add(new XYChart.Data("]-∞, µ-σ]", collaboratorStatisticsController.numberDelaysFirstIntervalByFreelancer(freelancersListView.getSelectionModel().getSelectedItem())));
            set1.getData().add(new XYChart.Data("]µ-σ, µ+σ[", collaboratorStatisticsController.numberDelaysSecondIntervalByFreelancer(freelancersListView.getSelectionModel().getSelectedItem())));
            set1.getData().add(new XYChart.Data("[µ+σ, +∞[", collaboratorStatisticsController.numberDelaysThirdIntervalByFreelancer(freelancersListView.getSelectionModel().getSelectedItem())));

            delayChart.getData().addAll(set1);
            meanTxtField.setText(String.valueOf(String.format("%.04f", collaboratorStatisticsController.meanByFreelancer(freelancersListView.getSelectionModel().getSelectedItem()))));
            standardDeviationTxtField.setText(String.valueOf(String.format("%.04f", collaboratorStatisticsController.standardDeviationByFreelancer(freelancersListView.getSelectionModel().getSelectedItem()))));
            freelancerChoosedBefore.add(freelancersListView.getSelectionModel().getSelectedItem().getEmail());
        }
    }

    @FXML
    void SeeStatisticsForAllFreelancersOnAction(ActionEvent event) {
            XYChart.Series set1 = new XYChart.Series<>();
            set1.getData().add(new XYChart.Data("]-∞, µ-σ]", collaboratorStatisticsController.numberDelaysFirstIntervalByOrganization()));
            set1.getData().add(new XYChart.Data("]µ-σ, µ+σ[", collaboratorStatisticsController.numberDelaysSecondIntervalByOrganization()));
            set1.getData().add(new XYChart.Data("[µ+σ, +∞[", collaboratorStatisticsController.numberDelaysThirdIntervalByOrganization()));

            delayChart.getData().addAll(set1);
            meanTxtField.setText(String.valueOf(String.format("%.04f", collaboratorStatisticsController.meanByOrganization())));
            standardDeviationTxtField.setText(String.valueOf(String.format("%.04f", collaboratorStatisticsController.standardDeviationByOrganization())));
    }

    @FXML
    void clearStatsOnAction(ActionEvent event) {
        delayChart.getData().clear();
        standardDeviationTxtField.clear();
        meanTxtField.clear();
        freelancerChoosedBefore.clear();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void associateParentUI(CollaboratorMenuUI collaboratorMenuUI) {
        this.collaboratorMenuUI = collaboratorMenuUI;
    }

}
