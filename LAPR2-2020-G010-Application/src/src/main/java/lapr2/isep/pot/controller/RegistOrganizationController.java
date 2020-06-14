package lapr2.isep.pot.controller;

import lapr2.isep.authorization.model.User;
import lapr2.isep.pot.UI.console.utils.RegistOrganizationUI;
import lapr2.isep.pot.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller of the organization regist.
 *
 * @author José Soares, João Beires, José Maia, Lourenço Melo, Gonçalo Ferreira.
 */
public class RegistOrganizationController implements Serializable {

    /**
     * Plataform's initialization
     */
    private final Platform platform;

    /**
     * ApplicationPOT's initialization
     */
    private final ApplicationPOT applicationPOT;

    /**
     * Organization's initialization
     */
    private Organization organization;

    /**
     * List of collaborators's initialization
     */
    private List<Collaborator> collaboratorListRegistered = new ArrayList<>();

    /**
     * RegistOrganization's initialization
     */
    private RegistOrganization registOrganization;

    /**
     * Constructor that initialize the applicationPOT and platform and registorganization instances
     *
     * @throws FileNotFoundException if the file is not found
     */
    public RegistOrganizationController() throws FileNotFoundException {
        this.applicationPOT = ApplicationPOT.getInstance();
        this.platform = applicationPOT.getPlatform();
        this.registOrganization = platform.getRegistOrganization();
    }


    /**
     * Add the received organization
     *
     * @param organization to add
     * @return true if added or false if not
     */
    public boolean addOrganization(Organization organization) {
        return this.platform.addOrganization(organization);
    }

    /**
     * Confirms if has or not the organization
     *
     * @param organization to compare
     * @return true if has or false if not
     */
    public boolean hasOrganization(Organization organization) {
        return this.platform.hasOrganization(organization);
    }

    /**
     * Returns the organizatio
     *
     * @return organization
     */
    public Organization getOrganization() {
        return this.organization;
    }

    /**
     * Returns the list of organizations
     *
     * @return organization's list
     */
    public List<Organization> getListOrganizations() {
        return this.registOrganization.getListOrganizations();
    }

    /**
     * Returns the platform
     *
     * @return platform
     */
    public Platform getPlatform() {
        return this.platform;
    }

    /**
     * Confirms if the collaborator is logged in
     *
     * @param user to confirm if is a collaborator
     * @return true if is collaborator or false if not
     */
    public boolean isCollaboratorLoggingIn(User user) {
        return platform.userIsCollaborator(user);
    }

    /**
     * Confirms if the manager is logged in
     *
     * @param user to confirm if is a manager
     * @return true if is manager or false if not
     */
    public boolean isManagerLoggingIn(User user) {
        return platform.userIsManager(user);
    }

    /**
     * Creates an user
     *
     * @param name     of the user
     * @param email    of the user
     * @param password of the user
     * @return the user
     * @throws FileNotFoundException if file is not found
     */
    public User createUser(String name, String email, String password) throws FileNotFoundException {
        return platform.createUser(name, email, password);
    }

    /**
     * Confirms if the user received exist
     *
     * @param user to confirm
     * @return true if exist or false if not
     */
    public boolean userExist(User user) {
        return platform.userExist(user);
    }

    /**
     * Confirms if the email and password exist in the system as user
     *
     * @param email    to confirm
     * @param password to confirm
     * @return true if exist or false if not
     */
    public boolean userExist(String email, String password) {
        return platform.userExist(email, password);
    }
}