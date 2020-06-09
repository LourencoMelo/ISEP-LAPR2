package lapr2.isep.pot.model;

import lapr2.isep.authorization.FacadeAuthorization;
import lapr2.isep.authorization.model.User;
import lapr2.isep.pot.model.List.TaskList;

import java.io.Serializable;
import java.util.List;

public class Platform implements Serializable {

    /**
     * FacadeAuthorization's initialization
     */
    private final FacadeAuthorization facadeAuthorization;

    private Freelancer selectedFreelancer;

    private Task selectedTask;

    /**
     * Regist Freelancer instance
     */

    private  RegistFreelancer registFreelancer;

    private RegistOrganization registOrganization;
    /**
     * Organization's initialization
     */
    private Organization organization;


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
    public RegistOrganization getRegistOrganization() {
        return registOrganization;
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

    public String getRoleUser(User user) {
        return user.getRole();
    }

    public Freelancer getSelectedFreelancer() {
        return selectedFreelancer;
    }

    public void setSelectedFreelancer(Freelancer selectedFreelancer) {
        this.selectedFreelancer = selectedFreelancer;
    }

    public Task getSelectedTask() {
        return selectedTask;
    }

    public void setSelectedTask(Task selectedTask) {
        this.selectedTask = selectedTask;
    }



}
