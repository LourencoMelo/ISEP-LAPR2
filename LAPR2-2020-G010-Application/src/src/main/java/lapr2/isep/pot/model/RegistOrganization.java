package lapr2.isep.pot.model;

import com.sun.org.apache.xpath.internal.operations.Or;
import lapr2.isep.authorization.FacadeAuthorization;
import lapr2.isep.authorization.model.User;
import lapr2.isep.pot.controller.ApplicationPOT;
import lapr2.isep.pot.model.List.CollaboratorList;
import sun.security.util.Password;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RegistOrganization implements Serializable {

    /**
     * FacadeAutorization's initialization
     */
    private FacadeAuthorization facadeAuthorization;

    /**
     * Initialization of Organizations' list
     */
    private final List<Organization> listOrganizations;

    /**
     * Plataform's initialization
     */
    private Platform platform;

    /**
     * ExternAlgorithmPasswordGenerator's initialization
     */
    private ExternAlgorithmPasswordGenerator algorithm;

    /**
     * Collaborator's password
     */
    private String collabPassword;

    /**
     *
     */

    /**
     * Initialize an Organization Regist creating a list of organizations
     */
    public RegistOrganization() {
        this.listOrganizations = new ArrayList<>();
    }

    /**
     * Validates an Organization received by parameter
     * @param org organization to validate
     * @return boolean dependent of validation
     */
    public boolean validatesOrganization(Organization org) {
        boolean isValid = false;
        //write validation code
        return isValid;
    }

    /**
     * Add an organization
     * @param organization to add
     * @return true if was added or false if not
     */
    public boolean addOrganization(Organization organization) {
        return listOrganizations.add(organization);
    }

    /**
     * Regists one organization
     * @param organization to regist
     * @return true if regist or false if not
     */
    public boolean registOrganization(Organization organization) {
        if (this.validatesOrganization(organization)) {
            /*
            if (this.validaOrganizacao(oOrganizacao)) {                 NECESSARY?!?!?!
            Colaborador oGestor = oOrganizacao.getGestor();             NECESSARY?!?!?!
             */
            return addOrganization(organization);
        }
        return false;
    }

    /**
     * New instance of organization with the necessary data
     * @param name of Organization
     * @param NIF of Organization
     * @param collaborator of Organization
     * @param manager of Organization
     * @return
     */
    public Organization newOrganization(String name, String NIF, Collaborator collaborator, Manager manager) {
        return new Organization(name, NIF, collaborator, manager);
    }

    /**
     * If has the organization
     * @param organization to compare
     * @return true if it has or false if not
     */
    public boolean hasOrganization(Organization organization) {
        return listOrganizations.contains(organization);
    }

    /**
     * Regists a manager as a system user
     * @param manager to regist
     * @return true if registed or false if not
     */
    /*public boolean registManagerAsUser(Manager manager) {
        //this.platform = ApplicationPOT.getInstance().getPlatform();

        String managerName = manager.getName();
        String managerEmail = manager.getEmail();                                            //IMPROVE THIS METHOD

        this.algorithm = this.platform.getAlgorithmPasswordGenerator();

        String password = String.valueOf(this.algorithm.generatePassword(managerName, managerEmail));

        this.facadeAuthorization = platform.getFacadeAutorization();

        //return (facadeAuthorization.registUserWithRole(managerName, managerEmail, password, new String[] {Constants.ORGANIZATION_MANAGER_ROLE}));
        return false;
    }

     */

    /**
     * Regists a manager as a system user
     * @param collab to regist
     * @return true if registed or false if not
     */
    /*public void registCollaboratorAsUser(Collaborator collab) {

        User user = new User(collab.getName(), collab.getEmail(), password);
    }

     */

    /**
     * Sends the password to the email
     * @param email used
     * @param password sent
     * @return true if sent or false if not
     */
    private boolean sendPassword(String email, Password password) {
        //                                                              TO IMPLEMENT THE CODE
        return false;
    }

    public List<Organization> getListOrganizations() {
        return listOrganizations;
    }
}
