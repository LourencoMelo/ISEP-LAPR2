package lapr2.isep.pot.model;

public class RegistFreelancer {

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
        // Implementar código de confirmação da existência ou não do freelancer criado no sistema

        return true;
    }

    /**
     *
     * @param freelancer freelancer
     * @return new freelancer if the validation is true
     */

    public boolean registFreelancer(Freelancer freelancer) {
        if (this.validationFreelancer(freelancer)) {
            return addFreelancer(freelancer);
        }
        return false;
    }

    /**
     *
     * @param freelancer freelancer
     * @return new freelancer
     */

    public boolean addFreelancer(Freelancer freelancer) {
        //Aqui será feita a adição do freelancer à arrayList criada na classe freelancer
        return true;
    }
}
