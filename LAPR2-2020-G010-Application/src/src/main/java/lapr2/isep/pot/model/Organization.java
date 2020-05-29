package lapr2.isep.pot.model;

import java.util.Objects;

public class Organization {

    /**
     * Organization's name
     */
    private String name;

    /**
     * Organization's NIF
     */
    private String NIF;

    /**
     * Initialize the Organization's information with the received data
     * @param name Organization's name
     * @param NIF Organizaton's NIF
     */
    public Organization(String name, String NIF) {
        this.name = name;
        this.NIF = NIF;
    }

    /**
     * New collaborator
     * @param name Collaborator's name
     * @param email Collaborator's email
     * @return  new Collaborator
     */
    public static Collaborator newCollaborator(String name, String email) {
        return new Collaborator(name, email);
    }

    /**
     * New Manager
     * @param name Manager's name
     * @param email Manager's email
     * @return new Manager
     */
    public static Manager newManager(String name, String email) {
        return new Manager(name, email);
    }

    /**
     * Compares Organizations
     * @param o other organization
     * @return boolean dependent organtions comparation
     */
    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o)
            return true;
        // null check
        if (o == null)
            return false;
        // type check and cast
        if (getClass() != o.getClass())
            return false;
        // field comparison
        Organization obj = (Organization) o;
        return (Objects.equals(NIF, obj.NIF));
    }

}
