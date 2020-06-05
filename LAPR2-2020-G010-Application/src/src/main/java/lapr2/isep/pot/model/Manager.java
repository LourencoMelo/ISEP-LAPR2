package lapr2.isep.pot.model;

import javafx.scene.control.Alert;
import lapr2.isep.authorization.model.User;
import lapr2.isep.pot.UI.console.utils.AlertUI;
import lapr2.isep.pot.controller.ApplicationController;

import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

public class Manager extends User implements Serializable {

    /**
     * Get the application controller instance
     */
    private ApplicationController applicationController = ApplicationController.getApplicationController();
    /**
     * Initialize the Manager's information with the received data
     *
     * @param name  Manager's name
     * @param email Manager's name
     */
    public Manager(String name, String email) throws IOException {
        super(name, email);
        if (name.isEmpty() || email.isEmpty()) {
            throw new IllegalArgumentException("Arguments cant be null or empty.");
        }
    }
}