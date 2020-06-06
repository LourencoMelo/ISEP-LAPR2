package lapr2.isep.pot.controller;

import lapr2.isep.pot.model.Freelancer;
import lapr2.isep.pot.model.Platform;
import lapr2.isep.pot.model.RegistFreelancer;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    private RegistFreelancer registFreelancer;

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

    public boolean newFreelancer(String id, String name, String levelOfExpertise, String email, String NIF, String bankAccountIBAN, String address, String country){
        try {
            this.freelancer = this.registFreelancer.newFreelancer(id, name, levelOfExpertise, email, NIF, bankAccountIBAN, address, country);
            return this.registFreelancer.validationFreelancer(freelancer);
        } catch (RuntimeException exception){
            //Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, exception);
            this.freelancer = null;
            return false;
        }
    }

    /**
     *
     * @param freelancer    freelancer's instance
     * @return boolean value depending of the validation
     */
    public boolean registFreelancer(Freelancer freelancer){
        return registFreelancer.registFreelancer(freelancer);
    }
}
