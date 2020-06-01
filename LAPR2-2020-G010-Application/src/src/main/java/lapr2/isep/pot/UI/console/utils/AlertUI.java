package lapr2.isep.pot.UI.console.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AlertUI {

    public static Alert createAlert(Alert.AlertType alertType, String title, String header, String message) {
        Alert alerta = new Alert(alertType);

        alerta.setTitle(title);
        alerta.setHeaderText(header);
        alerta.setContentText(message);
        ((Stage)alerta.getDialogPane().getScene().getWindow()).getIcons().add(new Image("file:images\\error.png"));

        if (alertType == Alert.AlertType.CONFIRMATION) {
            ((Button) alerta.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
            ((Button) alerta.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("No");
        }

        return alerta;
    }
}
