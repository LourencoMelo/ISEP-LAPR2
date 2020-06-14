package lapr2.isep.pot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RegistFreelancer implements Serializable {

    /**
     * Creates freelancer's list instance
     */
    private List<Freelancer> freelancerList;

    /**
     * Initializes freelancer's list
     */
    public RegistFreelancer(){
        this.freelancerList = new ArrayList<>();
    }

    /**
     * @param id               freelancer's id
     * @param name             freelancer's name
     * @param levelOfExpertise freelancer's level of expertise
     * @param email            freelancer's email
     * @param NIF              freelancer's nif
     * @param bankAccountIBAN  freelancer's iban
     * @param address          freelancer's address
     * @param country          freelancer's country
     * @return new Freelancer
     */

    public Freelancer newFreelancer(String id, String name, String levelOfExpertise, String email, String NIF, String bankAccountIBAN, String address, String country) {
        return new Freelancer(id, name, levelOfExpertise, email, NIF, bankAccountIBAN, address, country);
    }

    /**
     * @param freelancer freelancer
     * @return  the boolean result of the validation
     */

    public boolean validationFreelancer(Freelancer freelancer) {
        return !freelancerList.contains(freelancer);
    }

    /**
     * Returns boolean value depending if the freelancer is added
     * @param freelancer freelancer
     * @return new freelancer
     */

    public boolean addFreelancer(Freelancer freelancer) {
        return freelancerList.add(freelancer);
    }

    /**
     * Return boolean value depending if the list of freelancer already contains the freelancer received
     * @param freelancer received freelancer
     * @return true if the freelancer already exists
     */
    public boolean contains(Freelancer freelancer) {
        for (Freelancer freelancerAux : freelancerList) {
            return freelancer.equals(freelancerAux);
        }
        return false;
    }

    /**
     * Returns freelancer's list
     * @return freelancer's list
     */

    public List<Freelancer> getFreelancerList() {
        return freelancerList;
    }

    public Freelancer getFreelancerByID(String id) {
        for(Freelancer freelancer: freelancerList) {
            if (id.equals(freelancer.getId())) {
                return freelancer;
            }
        }
        return null;
    }

}
