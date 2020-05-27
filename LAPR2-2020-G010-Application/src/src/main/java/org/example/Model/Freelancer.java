package org.example.Model;

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
    private String nif;

    /**
     * Freelancer's Bank account
     */
    private String iban;

    /**
     * Freelancer's address
     */
    private String address;

    /**
     * Freelancer's country
     */
    private String country;


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
    public String getNif() {
        return nif;
    }

    /**
     * Returns freelancer's Bank account.
     *
     * @return iban.
     */
    public String getIban() {
        return iban;
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
        Freelancer outroFreelancer = (Freelancer) otherObject;
        return id.equalsIgnoreCase(outroFreelancer.id)  &&
                name.equalsIgnoreCase(outroFreelancer.name)  &&
                levelOfExpertise.equalsIgnoreCase(outroFreelancer.levelOfExpertise) &&
                email.equalsIgnoreCase(outroFreelancer.email) &&
                nif.equalsIgnoreCase(outroFreelancer.nif) &&
                iban.equalsIgnoreCase(outroFreelancer.iban) &&
                address.equalsIgnoreCase(outroFreelancer.address) &&
                country.equalsIgnoreCase(outroFreelancer.country);
    }

    /**
     * Returns the Freelancer's text description in the format: id, name, levelOfExpertise, email, nif, iban, address and country
     *
     * @return Freelancer's characteristics.
     */
    @Override
    public String toString() {
        return String.format("Freelancer: Id- %s '\n'Name- %s '\n'Level of expertise- %s '\n'Email- %s '\n'NIF- %s '\n'IBAN- %s '\n'Address- %s '\n'Country- %s", id, name, levelOfExpertise, email, nif, iban, address, country);
    }
}
