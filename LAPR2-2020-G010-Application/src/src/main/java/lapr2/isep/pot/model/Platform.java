package lapr2.isep.pot.model;

import lapr2.isep.authorization.FacadeAuthorization;
import lapr2.isep.pot.model.List.TaskList;

import java.io.Serializable;
import java.util.List;

public class Platform implements Serializable {

    /**
     * FacadeAuthorization's initialization
     */
    private final FacadeAuthorization facadeAuthorization;

    /**
     * Regist Freelancer instance
     */

    private final RegistFreelancer registFreelancer;

    /**
     * Organization's initialization
     */
    private Organization organization;

    private RegistOrganization registOrganization;

    public Platform() {                                              //INCOMPLETE
        this.facadeAuthorization = new FacadeAuthorization();
        this.registFreelancer = new RegistFreelancer();
        this.registOrganization = new RegistOrganization();
    }

    public ExternAlgorithmPasswordGenerator getAlgorithmPasswordGenerator() {
        return null;                                                            //WRITE CODE IF NECESSARY
    }

    public FacadeAuthorization getFacadeAutorization() {
        return facadeAuthorization;
    }

    /**
     * Returns Organization
     *
     * @return Organization
     */
    public Organization getRegistOrganization() {
        return organization;
    }

    /**
     * Returns RegistFreelancer
     * @return registFreelancer
     */
    public RegistFreelancer getRegistFreelancer(){
        return this.registFreelancer;
    }

    public boolean addOrganization(Organization organization) {
        return registOrganization.addOrganization(organization);
    }

    /**
     * confirms if the the list has the organization
     * @param organization to confirm
     * @return true if has or false if not
     */
    public boolean hasOrganization(Organization organization) {
        return registOrganization.hasOrganization(organization);
    }

    /**
     * Returns Organization's List
     *
     * @return organization list
     */
    public List<Organization> getListOrganizations() {
        return registOrganization.getListOrganizations();
    }
}
