package lapr2.isep.pot.model;

import java.util.Objects;

public class Manager {

    /**
     * Manager's name
     */
    private String name;

    /**
     * Manager's emailL
     */
    private String email;

    /**
     * Initialize the Manager's information with the received data
     *
     * @param name Manager's name
     * @param email Manager's name
     */
    public Manager(String name, String email) {
        this.name = name;
        this.email = email;
    }

    /**
     * Returns the Manager's name
     * @return name of the manager
     */
    public String getName() {
        return name;
    }

    /**
     * Return the Manager's email
     * @return email of the manager
     */
    public String getEmail() {
        return email;
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
        Manager that = (Manager) o;
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