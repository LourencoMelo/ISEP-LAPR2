package lapr2.isep.authorization.model;

import lapr2.isep.pot.model.EmailFiles;
import lapr2.isep.pot.model.ExternAlgorithmPasswordGenerator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class referent to the users of the system.
 *
 * @author José Soares, João Beires, José Maia, Lourenço Melo, Gonçalo Ferreira.
 */

public class User implements Serializable {

    /**
     * User's name
     */
    private final String name;

    /**
     * User's email
     */
    private final String email;

    /**
     * User's password
     */
    private final String password;

    /**
     * List of users initialization
     */
    private static List<User> listUsers = new ArrayList<>();

    /**
     * Constructor that initializes the name and the email of the user. Also generates user's password.
     *
     * @param name  User's name
     * @param email User's email
     * @throws IOException Input/output exception when writing the email file
     */
    public User(String name, String email) throws IOException {
        if (name == null || email == null || name.isEmpty() || email.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.email = email;
        this.password = generatePassword();
        listUsers.add(this);
        EmailFiles.writeToAFileAboutPasswords(listUsers);
    }

    /**
     * Constructor that initializes the name, the email and the password of the user.
     *
     * @param name     User's name
     * @param email    User's email
     * @param password User's password
     * @throws FileNotFoundException Input/output exception when writing the email file
     */
    public User(String name, String email, String password) throws FileNotFoundException {
        this.name = name;
        this.email = email;
        this.password = password;
        EmailFiles.writeToAFileAboutPasswords(listUsers);
    }

    /**
     * Returns User's name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns User's email
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Verifies if the password already exists
     *
     * @param password password
     * @return true if the password exists.
     */
    public boolean hasPassword(String password) {
        return this.password.equals(password);
    }

    /**
     * Generates User's password using the Extern Algorithm Generator
     *
     * @return User's password
     */
    public String generatePassword() {
        ExternAlgorithmPasswordGenerator externAlgorithmPasswordGenerator = new ExternAlgorithmPasswordGenerator.PasswordGeneratorBuilder()
                .useDigits(true)
                .useLower(true)
                .useUpper(true)
                .build();
        return externAlgorithmPasswordGenerator.generate(8);
    }

    /**
     * Manages the user's email
     *
     * @return managed user's email
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.email);
        return hash;
    }

    /**
     * Compares Objects
     *
     * @param o
     * @return true if other object equals
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
        User obj = (User) o;
        return Objects.equals(email, obj.email);
    }

    /**
     * Returns textual description of user
     *
     * @return textual description
     */
    @Override
    public String toString() {
        return String.format("To: %s" +
                "\nSubject: Registration to T4J" +
                "\n  Message:" +
                "\n\t%s had just registered to T4J with the following data: " +
                "\n\tName: %s" +
                "\n\tEmail: %s" +
                "\n\tPassword: %s" +
                "\n" +
                "\n" +
                "Best regards," +
                "\n T4J Team." +
                "\n-----------------------------------------------------------------", email, name, name, email, password);
    }

    /**
     * Returns User's password
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns List of Users
     *
     * @return
     */
    public static List<User> getListUsers() {
        return listUsers;
    }


}
