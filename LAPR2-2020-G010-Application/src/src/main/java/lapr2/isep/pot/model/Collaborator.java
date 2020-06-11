package lapr2.isep.pot.model;

import javafx.scene.control.Alert;
import lapr2.isep.authorization.model.User;
import lapr2.isep.pot.UI.console.utils.AlertUI;
import lapr2.isep.pot.controller.ApplicationController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Collaborator extends User implements Serializable {

    private String name;
    private String email;
    private String password;
    List<Collaborator> listCollab = new ArrayList<>();

    /**
     * User
     */
    private User user;

    /**
     * Get the application controller instance
     */
    private ApplicationController applicationController = new ApplicationController();

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
        this.name = name;
        this.email = email;
        listCollab.add(this);
    }

    public Collaborator(String name, String email, String password) throws FileNotFoundException {
        super(name, email, password);
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /**
     * Confirms if the Collaborator exits
     *
     * @param email Collaborator's email
     * @return true if the email exits
     */
    public boolean hasEmail(String email){
        return user.getEmail().equalsIgnoreCase(email);
    }

}