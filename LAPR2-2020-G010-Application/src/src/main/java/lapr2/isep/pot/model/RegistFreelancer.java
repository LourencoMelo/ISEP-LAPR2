package lapr2.isep.pot.model;

import lapr2.isep.pot.model.List.FreelancerList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RegistFreelancer implements Serializable {

    private List<Freelancer> freelancerList;

    public RegistFreelancer(){
        freelancerList = new ArrayList<>();
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
     *
     * @param freelancer freelancer
     * @return new freelancer
     */

    public boolean addFreelancer(Freelancer freelancer) {
        System.out.println("oi");
        return freelancerList.add(freelancer);
    }

    public List<Freelancer> getFreelancerList() {
        return freelancerList;
    }
}
