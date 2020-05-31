package lapr2.isep.authorization.model;

import sun.security.util.Password;

import java.util.Objects;

public class User {
    /**
     * User´s name.
     */
    private String name;
    /**
     * User´s email.
     */
    private String email;
    /**
     * User´s password.
     */
    private Password password;

    /**
     * Initializes the user. If the variables are null it throws an exception that the arguments cannot be null.
     *
     * @param name     User´s name.
     * @param email    User´s email.
     * @param password User´s password.
     */
    public User(String name, String email, Password password) {
        if ((name == null) || (email == null) || (password == null) || (name.isEmpty()) || (email.isEmpty())) {
            throw new IllegalArgumentException("No argument cannot be null or empty.");
        }
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /**
     * Returns the User´s name.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the User´s email.
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Verifies if the user has a password comparing the parameter
     *
     * @param password
     * @return boolean (true or false)
     */
    public boolean hasPassword(Password password) {
        return this.password.equals(password);
    }

    /**
     * NAO COMENTADO AINDA É PRECISO FAZE-LO !!!!!!!
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.email);
        return hash;
    }

    /**
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        // Inspirado em https://www.sitepoint.com/implement-javas-equals-method-correctly/

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
        User obj = (User) o;
        return Objects.equals(email, obj.email);
    }

    /**
     * Retorns a string with the informations about the user.
     *
     * @return String with (User´s name and email)
     */
    @Override
    public String toString() {
        return String.format("%s - %s", this.name, this.email);
    }

}
