package lapr2.isep.pot.model;

import java.io.Serializable;

public class Freelancer implements Serializable {

    /**
     * Freelancer's id
     */
    private final String id;

    /**
     * Freelancer's name
     */
    private final String name;

    /**
     * Freelancer's level of expertise
     */
    private final String levelOfExpertise;

    /**
     * Freelancer's email
     */
    private final String email;

    /**
     * Freelancer's NIF
     */
    private final String NIF;

    /**
     * Freelancer's Bank account
     */
    private final String bankAccountIBAN;

    /**
     * Freelancer's address
     */
    private final String address;

    /**
     * Freelancer's country
     */
    private final String country;

    private int delay = 0;

    /**
     * Initialize the Freelancer's information with the received data
     *
     * @param id               Freelancer's id
     * @param name             Freelancer's name
     * @param levelOfExpertise Freelancer's level of expertise
     * @param email            Freelancer's email
     * @param NIF              Freelancer's NIF
     * @param bankAccountIBAN  Freelancer's Bank account
     * @param address          Freelancer's address
     * @param country          Freelancer's country
     */
    public Freelancer(String id, String name, String levelOfExpertise, String email, String NIF, String bankAccountIBAN, String address, String country) {
        if (id == null || name == null || levelOfExpertise == null || email == null || NIF == null || bankAccountIBAN == null || address == null || country == null || id.isEmpty() || name.isEmpty() || levelOfExpertise.isEmpty() || email.isEmpty() || NIF.isEmpty() || bankAccountIBAN.isEmpty() || address.isEmpty() || country.isEmpty()) {
            throw new IllegalArgumentException("Arguments cant be null or empty.");
        }
        this.id = id;
        this.name = name;
        this.levelOfExpertise = levelOfExpertise;
        this.email = email;
        this.NIF = NIF;
        this.bankAccountIBAN = bankAccountIBAN;
        this.address = address;
        this.country = country;
    }

    public void addDelayToFreelancer(int delay) {
        this.delay += delay;
    }

    public int getDelay() {
        return delay;
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
     * Freelancer. Otherwise, returns false.
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
        return id.equalsIgnoreCase(otherFreelancer.id) &&
                name.equalsIgnoreCase(otherFreelancer.name) &&
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
        return String.format("Freelancer: " +
                "\n\tID: %s" +
                "\n\tName: %s" +
                "\n\tLevel of Expertise: %s" +
                "\n\tEmail: %s" +
                "\n\tNIF: %s" +
                "\n\tBank account IBAN: %s" +
                "\n\tAddress: %s" +
                "\n\tCountry: %s", id, name, levelOfExpertise, email, NIF, bankAccountIBAN, address, country);
    }
}
