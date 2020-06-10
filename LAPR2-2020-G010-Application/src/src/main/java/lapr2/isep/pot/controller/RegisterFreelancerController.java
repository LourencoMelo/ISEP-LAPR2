package lapr2.isep.pot.controller;

import lapr2.isep.pot.model.Freelancer;
import lapr2.isep.pot.model.Platform;
import lapr2.isep.pot.model.RegistFreelancer;

import java.io.Serializable;
import java.util.List;

public class RegisterFreelancerController implements Serializable {

    /**
     * platform instance
     */

    private static Platform platform;


    /**
     * freelancer instance
     */

    private Freelancer freelancer;

    /**
     * registFreelancer instance
     */

    private final RegistFreelancer registFreelancer;

    /**
     * Application POT's instance
     */

    private final ApplicationPOT applicationPOT;
    /**
     * Initializes the frellancer's instance
     */
    public RegisterFreelancerController(){
        this.applicationPOT = ApplicationPOT.getInstance();
        this.platform = applicationPOT.getPlatform();
        this.registFreelancer = platform.getRegistFreelancer();
        this.freelancer = null;
    }

    /**
     *
     * @param id                    freelancer's id
     * @param name                  freelancer's name
     * @param levelOfExpertise      freelancer's level of expertise
     * @param email                 freelancer's email
     * @param NIF                   freelancer's nif
     * @param bankAccountIBAN       freelancer's iban
     * @param address               freelancer's address
     * @param country               freelancer's country
     * @return true or false depending of the validation
     */

    public Freelancer newFreelancer(String id, String name, String levelOfExpertise, String email, String NIF, String bankAccountIBAN, String address, String country){
            this.freelancer = this.registFreelancer.newFreelancer(id, name, levelOfExpertise, email, NIF, bankAccountIBAN, address, country);
            return this.freelancer;
    }

    /**
     *
     * @return boolean value depending of the validation
     */
    public boolean registFreelancer(){
        if (registFreelancer.validationFreelancer(this.freelancer)){
            return this.registFreelancer.addFreelancer(freelancer);
        }
        return false;
    }

    /**
     *
     * @return list of freelancers
     */
    public List<Freelancer> getListFreelancer(){
        return this.registFreelancer.getFreelancerList();
    }

    /**
     * Returns if the freelancers already exists or not
     * @param freelancer freelancer
     * @return false if the freelancer exists
     */
    public boolean getValidationFreelancer(Freelancer freelancer){
        return this.registFreelancer.validationFreelancer(freelancer);
    }

    public RegistFreelancer getRegistFreelancer() {
        return this.registFreelancer;
    }

    /**
     * Sets the freelancer's value
     * @param freelancer freelancer's value
     */
    public void setFreelancer(Freelancer freelancer){
        this.freelancer = freelancer;
    }
}
