package lapr2.isep.pot.model;

import lapr2.isep.pot.model.List.TaskList;
import sun.security.util.Password;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RegistOrganization implements Serializable {


    /**
     * Initialization of Organizations' list
     */
    private final List<Organization> listOrganizations;


    /**
     * ExternAlgorithmPasswordGenerator's initialization
     */
    private ExternAlgorithmPasswordGenerator algorithm;

    private Organization organization;

    /**
     * Collaborator's password
     */
    private String collabPassword;

    List<Collaborator> collaboratorList = new ArrayList<>();

    List<Manager> managerList = new ArrayList<>();
    /**
     * Initialize an Organization Regist creating a list of organizations
     */
    public RegistOrganization() {
        this.listOrganizations = new ArrayList<>();
    }

    /**
     * Validates an Organization received by parameter
     *
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
     *
     * @param organization to add
     * @return true if was added or false if not
     */
    public boolean addOrganization(Organization organization) {
        return listOrganizations.add(organization);
    }

    /**
     * Regists one organization
     *
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
     *
     * @param name         of Organization
     * @param NIF          of Organization
     * @param collaborator of Organization
     * @param manager      of Organization
     * @return
     */
    public Organization newOrganization(String name, String NIF, Collaborator collaborator, Manager manager) {
        return new Organization(name, NIF, collaborator, manager);
    }

    /**
     * If has the organization
     *
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
     *
     * @param email    used
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

    public Organization getOrganizationByUserEmail(String email) {
        Organization orgReturn = null;

        for (int i = 0; i < this.listOrganizations.size(); i++) {
            Organization org = this.listOrganizations.get(i);
            boolean found = org.hasCollaboratorWithEmail(email);
            if (found)
                orgReturn = org;
        }
        return orgReturn;
    }

//    public List<Task> getTaskListByOrganization() {
//
//    }


    /**
     * Description of the organizations in the list
     *
     * @return organizations in string
     */
    public String toString() {
        List<Organization> aux = new ArrayList<>(listOrganizations);

        StringBuilder s = new StringBuilder();
        for (Organization organization : aux) {
            s.append(organization);
            s.append("\n");
        }

        return s.toString().trim();
    }

    public Organization getOrganizationByCollaborator(Collaborator collaborator) {
        for (Organization organization : listOrganizations) {
            if (collaborator.equals(organization.getCollaborator())) {
                return organization;
            }
        }
        return null;
    }

    public List<Collaborator> getListCollaboratorsAllOrganizations() {
        for (Organization organization : listOrganizations) {
            collaboratorList.add(organization.getCollaborator());
        }
        return collaboratorList;
    }

    public List<Manager> getListManagersAllOrganizations() {
        for (Organization organization : listOrganizations) {
            managerList.add(organization.getManager());
        }
        return managerList;
    }

    public List<Task> getListOfTasks() {
        return organization.getTaskList();
    }

    public Collaborator getCollaborator() {
        return organization.getCollaborator();
    }

    public TaskList getTasksList() {
        return organization.getListTask();
    }
}
