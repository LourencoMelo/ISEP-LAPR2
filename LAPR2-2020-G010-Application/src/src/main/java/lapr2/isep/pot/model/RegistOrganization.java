package lapr2.isep.pot.model;

import lapr2.isep.authorization.FacadeAuthorization;
import lapr2.isep.pot.controller.ApplicationPOT;
import lapr2.isep.pot.model.List.CollaboratorList;
import sun.security.util.Password;

import java.util.ArrayList;
import java.util.List;

public class RegistOrganization {

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

    private boolean addOrganization(Organization org) {
        return listOrganizations.add(org);
    }

    public boolean registOrganization(Organization org) {
        if (this.validatesOrganization(org)) {
            /*
            if (this.validaOrganizacao(oOrganizacao)) {                 NECESSARY?!?!?!
            Colaborador oGestor = oOrganizacao.getGestor();             NECESSARY?!?!?!
             */
            return addOrganization(org);
        }
        return false;
    }

    public Organization newOrganization(String name, String NIF) {
        return new Organization(name, NIF);
    }

    public boolean registManagerAsUser(Manager manager) {
        this.platform = ApplicationPOT.getInstance().getPlatform();

        String managerName = manager.getName();
        String managerEmail = manager.getEmail();                                            //IMPROVE THIS METHOD

        this.algorithm = this.platform.getAlgorithmPasswordGenerator();

        String password = String.valueOf(this.algorithm.generatePassword(managerName, managerEmail));

        this.facadeAuthorization = platform.getFacadeAutorization();

        //return (facadeAuthorization.registUserWithRole(managerName, managerEmail, password, new String[] {Constants.ORGANIZATION_MANAGER_ROLE}));
        return false;
    }

    public boolean registCollaboratorAsUser(Collaborator collab) {
        this.platform = ApplicationPOT.getInstance().getPlatform();

        String collaboratorName = collab.getName();                       //WHY THIS ERRORS?!
        String collaboratorEmail = collab.getEmail();                                   //IMPROVE THIS METHOD

        this.algorithm = this.platform.getAlgorithmPasswordGenerator();

        String password = String.valueOf(this.algorithm.generatePassword(collaboratorName, collaboratorEmail));

        this.facadeAuthorization = platform.getFacadeAutorization();

        //return (facadeAuthorization.registUserWithRole(managerName, managerEmail, password, new String[] {Constants.ORGANIZATION_MANAGER_ROLE}));
        return false;
    }

    private boolean sendPassword(String email, Password password) {
        //                                                              TO IMPLEMENT THE CODE
        return false;
    }

    /**
     * Searches for the Collaborator's Organization
     *
     * @param email Collaborator's email
     * @return Organization in search
     */
    public Organization getOrganizationByUserEmail(String email){
        Organization orgReturn = null;

        for (int i=0;i<this.listOrganizations.size();i++){
            Organization org = this.listOrganizations.get(i);
            CollaboratorList cl = org.getCollaboratorList();
            boolean found = cl.hasCollaboratorWithEmail(email);
            if (found)
                orgReturn = org;
        }
        return orgReturn;
    }

}
