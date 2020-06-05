package lapr2.isep.pot.model;

import javafx.scene.control.Alert;
import lapr2.isep.authorization.model.User;
import lapr2.isep.pot.UI.console.utils.AlertUI;
import lapr2.isep.pot.controller.ApplicationController;

import java.io.IOException;
import java.util.Objects;

public class Collaborator extends User {

    /**
     * Get the application controller instance
     */
    private ApplicationController applicationController = ApplicationController.getApplicationController();

    /**
     * Initialize the Collaborator's information with the received data
     *
     * @param name  Collaborator's name
     * @param email Collaborator's email
     */
    public Collaborator(String name, String email) throws IOException {
        super(name, email);
        if (name.isEmpty() || email.isEmpty()) {
            throw new IllegalArgumentException("Arguments cant be null or empty.");
        }
    }
}