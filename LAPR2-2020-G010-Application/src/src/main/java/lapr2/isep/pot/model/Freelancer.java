package lapr2.isep.pot.model;

public class Freelancer {

    /**
     * Freelancer's id
     */
    private String id;

    /**
     * Freelancer's name
     */
    private String name;

    /**
     * Freelancer's level of expertise
     */
    private String levelOfExpertise;

    /**
     * Freelancer's email
     */
    private String email;

    /**
     * Freelancer's NIF
     */
    private String NIF;

    /**
     * Freelancer's Bank account
     */
    private String bankAccountIBAN;

    /**
     * Freelancer's address
     */
    private String address;

    /**
     * Freelancer's country
     */
    private String country;

    /**
     * Initialize the Freelancer's information with the received data
     *
     * @param id Freelancer's id
     * @param name Freelancer's name
     * @param levelOfExpertise Freelancer's level of expertise
     * @param email Freelancer's email
     * @param NIF Freelancer's NIF
     * @param bankAccountIBAN Freelancer's Bank account
     * @param address Freelancer's address
     * @param country Freelancer's country
     */
    public Freelancer(String id, String name, String levelOfExpertise, String email, String NIF, String bankAccountIBAN, String address, String country){
        this.id = id;
        this.name = name;
        this.levelOfExpertise = levelOfExpertise;
        this.email = email;
        this.NIF = NIF;
        this.bankAccountIBAN = bankAccountIBAN;
        this.address = address;
        this.country = country;
    }

    /**
     * Returns freelancer's id.
     *
     * @return id.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns freelancer's name.
     *
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns freelancer's level of expertise.
     *
     * @return levelOfExpertise.
     */
    public String getLevelOfExpertise() {
        return levelOfExpertise;
    }

    /**
     * Returns freelancer's email.
     *
     * @return email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns freelancer's NIF.
     *
     * @return nif.
     */
    public String getNIF() {
        return NIF;
    }

    /**
     * Returns freelancer's Bank account.
     *
     * @return iban.
     */
    public String getBankAccountIBAN() {
        return bankAccountIBAN;
    }

    /**
     * Returns freelancer's address.
     *
     * @return address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Returns freelancer's country.
     *
     * @return country.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Compares the Freelancer with the received object.
     *
     * @param otherObject the object in comparison with the freelancer.
     * @return true if the object received represents an equivalent freelancer to
     *         Freelancer. Otherwise, returns false.
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || getClass() != otherObject.getClass()) {
            return false;
        }
        Freelancer otherFreelancer = (Freelancer) otherObject;
        return id.equalsIgnoreCase(otherFreelancer.id)  &&
                name.equalsIgnoreCase(otherFreelancer.name)  &&
                levelOfExpertise.equalsIgnoreCase(otherFreelancer.levelOfExpertise) &&
                email.equalsIgnoreCase(otherFreelancer.email) &&
                NIF.equalsIgnoreCase(otherFreelancer.NIF) &&
                bankAccountIBAN.equalsIgnoreCase(otherFreelancer.bankAccountIBAN) &&
                address.equalsIgnoreCase(otherFreelancer.address) &&
                country.equalsIgnoreCase(otherFreelancer.country);
    }

    /**
     * Returns the Freelancer's text description in the format: id, name, levelOfExpertise, email, nif, iban, address and country
     *
     * @return Freelancer's characteristics.
     */
    @Override
    public String toString() {
        return String.format("Freelancer: Id- %s '\n'Name- %s '\n'Level of expertise- %s '\n'Email- %s '\n'NIF- %s '\n'IBAN- %s '\n'Address- %s '\n'Country- %s", id, name, levelOfExpertise, email, NIF, bankAccountIBAN, address, country);
    }
}
