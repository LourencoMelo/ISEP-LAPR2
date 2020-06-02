package lapr2.isep.authorization.model;

import sun.security.util.Password;

import java.util.Objects;

public class User {
    private final String name;
    private final String email;
    private final Password password;

    public User(String name, String email, Password password) {
        if ((name == null) || (email == null) || (password == null) || (name.isEmpty()) || (email.isEmpty())) {
            throw new IllegalArgumentException("No argument cannot be null or empty.");
        }
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean hasPassword(Password password) {
        return this.password.equals(password);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.email);
        return hash;
    }

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

    @Override
    public String toString() {
        return String.format("%s - %s", this.name, this.email);
    }

}
