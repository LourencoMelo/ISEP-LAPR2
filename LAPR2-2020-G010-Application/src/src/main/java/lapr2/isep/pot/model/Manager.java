package lapr2.isep.pot.model;

import javafx.scene.control.Alert;
import lapr2.isep.authorization.model.User;
import lapr2.isep.pot.UI.console.utils.AlertUI;
import lapr2.isep.pot.controller.ApplicationController;

import java.util.Objects;

public class Manager extends User {

    /**
     * Get the application controller instance
     */
    private ApplicationController applicationController = ApplicationController.getApplicationController();

    /**
     * Manager's name
     */
    private String name;

    /**
     * Manager's emailL
     */
    private String email;

    private String password;

    /**
     * Initialize the Manager's information with the received data
     *
     * @param name Manager's name
     * @param email Manager's name
     */
    public Manager(String name, String email) {
        super(name, email);
        if(name == null || email == null || name.isEmpty() || email.isEmpty()) {
            AlertUI.createAlert(Alert.AlertType.INFORMATION, applicationController.getAppName(), "Error", "Incorrect manager's data. Verify again the insert data.");
        } else {
            this.name = name;
            this.email = email;
        }
    }

    /**
     * Returns the Manager's name
     * @return name of the manager
     */
    public String getName() {
        return name;
    }

    /**
     * Return the Manager's email
     * @return email of the manager
     */
    public String getEmail() {
        return email;
    }

    /**
     * Compares two Collaborators
     *
     * @param o other object
     * @return boolean about the diference
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manager that = (Manager) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(email, that.email);
    }

    /**
     * returns the hashCode number to the object
     * @return number of the hashCode
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }
}