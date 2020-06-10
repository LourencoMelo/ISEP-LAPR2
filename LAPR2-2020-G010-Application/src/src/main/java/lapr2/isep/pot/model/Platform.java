package lapr2.isep.pot.model;

import lapr2.isep.authorization.model.User;

import java.io.Serializable;
import java.util.List;

public class Platform implements Serializable {


    private Freelancer selectedFreelancer;

    private Task selectedTask;

    /**
     * Regist Freelancer instance
     */

    private  RegistFreelancer registFreelancer;

    /**
     * Regist Organization instance
     */
    private RegistOrganization registOrganization;



    public Platform() {
        this.registFreelancer = new RegistFreelancer();
        this.registOrganization = new RegistOrganization();
    }

    public ExternAlgorithmPasswordGenerator getAlgorithmPasswordGenerator() {
        return null;
    }


    /**
     * Returns Organization
     *
     * @return Organization
     */
    public RegistOrganization getRegistOrganization() {
        return this.registOrganization;
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
