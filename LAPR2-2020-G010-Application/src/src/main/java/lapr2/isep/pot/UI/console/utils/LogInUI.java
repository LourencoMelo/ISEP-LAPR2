package lapr2.isep.pot.UI.console.utils;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class LogInUI implements Initializable {

    double x = 0;
    double y = 0;

    @FXML
    private TextField emailTxtField;

    @FXML
    private TextField pwdTxtFiled;

    @FXML
    private Button logInBtn;

    @FXML
    void dragged(MouseEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    @FXML
    void pressed(MouseEvent event) {
        x =  event.getSceneX();
        y = event.getSceneY();
    }

    @FXML
    void LogInOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }


}
