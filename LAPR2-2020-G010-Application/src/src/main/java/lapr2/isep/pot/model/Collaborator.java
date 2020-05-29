package lapr2.isep.pot.model;

import java.util.Objects;

public class Collaborator {

    /**
     * Collaborator's name
     */
    private String name;

    /**
     * Collaborator's email
     */
    private String email;

    /**
     * Initialize the Collaborator's information with the received data
     *
     * @param name  Collaborator's name
     * @param email Collaborator's email
     */
    public Collaborator(String name, String email) {
        this.name = name;
        this.email = email;
    }

    /**
     * Returns the Collaborator's name
     * @return name of the manager
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the Collaborator's email
     * @return email of the manager
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Compares two Collaborators
     *
     * @param o other object
     * @return boolean about the diference
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Collaborator that = (Collaborator) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(email, that.email);
    }

    /**
     * returns the hashCode number to the object
     * @return number of the hashCode
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }
}