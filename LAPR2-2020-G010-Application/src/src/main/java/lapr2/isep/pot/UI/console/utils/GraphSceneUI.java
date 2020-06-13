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
import lapr2.isep.pot.controller.StatisticsController;
import lapr2.isep.pot.model.Freelancer;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@SuppressWarnings("ALL")
public class GraphSceneUI implements Initializable {

    private ApplicationController applicationController;

    private StatisticsController statisticsController;
    private AdministratorMenuUI administratorMenuUI;

    @FXML
    private Button SeeStatisticsForAllFreelancersBtn;

    @FXML
    private ListView<Freelancer> freelancersListView;

    @FXML
    private Button xBtn;

    @FXML
    private Button showFreelancersBtn;

    @FXML
    private TextField standardDeviationTxtField;

    @FXML
    private Button SeeStatisticsForFreelancerBtn;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    @FXML
    private TextField meanTxtField;

    @FXML
    private Button goBackBtn;

    @FXML
    private BarChart<?, ?> delayChart;

    double xwindow = 0;
    double ywindow = 0;

    private List<String> freelancerChoosedBefore = new ArrayList<>();

    public GraphSceneUI() throws FileNotFoundException {
        this.applicationController = new ApplicationController();
        this.statisticsController = new StatisticsController();
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
    void XOnAction(ActionEvent event) {
        Alert alert = AlertUI.createAlert(Alert.AlertType.CONFIRMATION, applicationController.getAppName(),
                "Action confirmation.", "Do you really want to close the application?");
        if (alert.showAndWait().get() == ButtonType.CANCEL) {
            event.consume();
        } else {
            //registOrganizationController.getPlatform().saveInfo(registOrganizationController.getPlatform());
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
            double y1 = statisticsController.numberDelaysFirstIntervalByFreelancer(freelancersListView.getSelectionModel().getSelectedItem());
            XYChart.Series set1 = new XYChart.Series<>();
                set1.getData().add(new XYChart.Data("]-∞, µ-σ]", statisticsController.numberDelaysFirstIntervalByFreelancer(freelancersListView.getSelectionModel().getSelectedItem())));
                set1.getData().add(new XYChart.Data("]µ-σ, µ+σ[", statisticsController.numberDelaysSecondIntervalByFreelancer(freelancersListView.getSelectionModel().getSelectedItem())));
                set1.getData().add(new XYChart.Data("[µ+σ, +∞[", statisticsController.numberDelaysThirdIntervalByFreelancer(freelancersListView.getSelectionModel().getSelectedItem())));

            delayChart.getData().addAll(set1);
            meanTxtField.setText(String.valueOf(String.format("%.04f", statisticsController.meanByFreelancer(freelancersListView.getSelectionModel().getSelectedItem()))));
            standardDeviationTxtField.setText(String.valueOf(String.format("%.04f", statisticsController.standardDeviationByFreelancer(freelancersListView.getSelectionModel().getSelectedItem()))));
            freelancerChoosedBefore.add(freelancersListView.getSelectionModel().getSelectedItem().getEmail());
        }
    }

    @FXML
    void SeeStatisticsForAllFreelancersOnAction(ActionEvent event) {

    }

    public void associateParentUI(AdministratorMenuUI administratorMenuUI) {
        this.administratorMenuUI = administratorMenuUI;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void ShowFreelancersOnAction(ActionEvent event) {
        if (statisticsController.getListFreelancersToListView().size() == 0) {
            Alert alert = AlertUI.createAlert(Alert.AlertType.WARNING, this.applicationController.getAppName(), "Error", "There are no freelancers in the system.");
            alert.show();
        } else {
            freelancersListView.getItems().setAll(statisticsController.getListFreelancersToListView());
        }
    }
}
