package lapr2.isep.pot.model;

import javafx.scene.control.Alert;
import lapr2.isep.authorization.model.User;
import lapr2.isep.pot.UI.console.utils.AlertUI;
import lapr2.isep.pot.controller.ApplicationController;

import java.util.Objects;

public class Collaborator extends User {

    /**
     * Get the application controller instance
     */
    private ApplicationController applicationController = ApplicationController.getApplicationController();

    /**
     * Collaborator's name
     */
    private String name;

    /**
     * Collaborator's email
     */
    private String email;

    private String password;

    /**
     * Initialize the Collaborator's information with the received data
     *
     * @param name  Collaborator's name
     * @param email Collaborator's email
     */
    public Collaborator(String name, String email) {
        super(name, email);
        if(name == null || email == null || name.isEmpty() || email.isEmpty()) {
            AlertUI.createAlert(Alert.AlertType.INFORMATION, applicationController.getAppName(), "Error", "Incorrect manager's data. Verify again the insert data.");
        } else {
            this.name = name;
            this.email = email;
        }
    }

    /**
     * Returns the Collaborator's name
     * @return name of the manager
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the Collaborator's email
     * @return email of the manager
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Verifies if is this the right Collaborator
     *
     * @param email Collaborator's email
     * @return true, if the email matches
     */
    public boolean hasEmail(String email){
        return this.email.equalsIgnoreCase(email);
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
        Collaborator that = (Collaborator) o;
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