package lapr2.isep.pot.UI.console;

import javafx.application.Application;
import static javafx.application.Application.launch;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class MainApp extends Application {

    public static final String APP_TITLE = "Improve T4J";

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LogInScene.fxml"));
        Parent root = loader.load();
        //stage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");

        stage.getIcons().add(new Image("file:images\\t4j.png"));
        stage.setTitle(APP_TITLE);
        stage.setScene(scene);

        stage.setResizable(false);

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override public void handle(WindowEvent event) { Alert alerta = AlertUI.criarAlerta(Alert.AlertType.CONFIRMATION, APP_TITLE,
                    "Action confirmation.", "Do you really want to close the application?");if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                event.consume(); } }});
        stage.show();
    }


    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
