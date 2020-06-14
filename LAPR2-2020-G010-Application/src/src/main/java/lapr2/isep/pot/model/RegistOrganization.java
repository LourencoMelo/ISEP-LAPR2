package lapr2.isep.pot.model;

import lapr2.isep.pot.model.List.PaymentTransactionList;
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

    /**
     * Organization's instance
     */
    private Organization organization;

    /**
     * Collaborator's password
     */
    private String collabPassword;

    /**
     * List of the collaborators
     */
    List<Collaborator> collaboratorList = new ArrayList<>();

    /**
     * List of managers
     */
    List<Manager> managerList = new ArrayList<>();

    /**
     * Initialize an Organization Regist creating a list of organizations
     */
    public RegistOrganization() {
        this.listOrganizations = new ArrayList<>();
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
     * If has the organization
     *
     * @param organization to compare
     * @return true if it has or false if not
     */
    public boolean hasOrganization(Organization organization) {
        return listOrganizations.contains(organization);
    }

    /**
     * Returns list of organizations
     * @return organizations
     */
    public List<Organization> getListOrganizations() {
        return listOrganizations;
    }

    /**
     * Returns the organization by an user's email
     * @param email from user
     * @return organization
     */
    public Organization getOrganizationByUserEmail(String email) {
        Organization orgReturn = null;

        for (Organization org : this.listOrganizations) {
            boolean found = org.hasCollaboratorWithEmail(email);
            if (!org.hasCollaboratorWithEmail(email)){
                found = org.hasManagerWithEmail(email);
            }
            if (found) {
                orgReturn = org;
            }
        }
        return orgReturn;
    }

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

    /**
     * Returns the organization by a collaborator
     * @return organization
     */
    public Organization getOrganizationByCollaborator(Collaborator collaborator) {
        for (Organization organization : listOrganizations) {
            if (collaborator.equals(organization.getCollaborator())) {
                return organization;
            }
        }
        return null;
    }

    /**
     * Returns list of all collaborators
     * @return list of collaborators
     */
    public List<Collaborator> getListCollaboratorsAllOrganizations() {
        for (Organization organization : listOrganizations) {
            collaboratorList.add(organization.getCollaborator());
        }
        return collaboratorList;
    }

    /**
     * Returns list of all managers
     * @return list of managers
     */
    public List<Manager> getListManagersAllOrganizations() {
        for (Organization organization : listOrganizations) {
            managerList.add(organization.getManager());
        }
        return managerList;
    }

    /**
     * Returns organization's collaborator
     * @return collaborator
     */
    public Collaborator getCollaborator() {
        return organization.getCollaborator();
    }

    /**
     * Returns payment transaction list instance from received organization
     * @param organization organization received
     * @return payment transaction list instance
     */
    public PaymentTransactionList getPaymentTransactionList(Organization organization){
        return organization.getPaymentTransactionList();
    }
}
