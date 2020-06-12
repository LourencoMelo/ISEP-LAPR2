package lapr2.isep.authorization.model;

import lapr2.isep.pot.controller.ApplicationController;
import lapr2.isep.pot.model.Collaborator;
import lapr2.isep.pot.model.EmailFiles;
import lapr2.isep.pot.model.ExternAlgorithmPasswordGenerator;
import lapr2.isep.pot.model.Manager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User implements Serializable {

    PrintWriter out = new PrintWriter(new File("files\\Emails.txt"));

    /**
     * Get the application controller instance
     */
    private ApplicationController applicationController = new ApplicationController();
    private final String name;
    private final String email;
    private final String password;
    //private String role;
    private static List<User> listUsers = new ArrayList<>();

    public User(String name, String email) throws IOException {
        if (name == null || email == null || name.isEmpty() || email.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.email = email;
        this.password = generatePassword();
        listUsers.add(this);
        EmailFiles.writeToAFile(listUsers);
        //sendEmail(this.name, this.email, this.password);
    }

    public User(String name, String email, String password) throws FileNotFoundException {
        this.name = name;
        this.email = email;
        this.password = password;
        EmailFiles.writeToAFile(listUsers);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

//    public String getRole() {
//        return this.role;
//    }

    public boolean hasPassword(String password) {
        return this.password.equals(password);
    }

    public String generatePassword() {
        ExternAlgorithmPasswordGenerator externAlgorithmPasswordGenerator = new ExternAlgorithmPasswordGenerator.PasswordGeneratorBuilder()
                .useDigits(true)
                .useLower(true)
                .useUpper(true)
                .build();
        return externAlgorithmPasswordGenerator.generate(8);
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
        return String.format("To: %s" +
                "\nSubject: Registration to T4J" +
                "\n  Message:" +
                "\n\t%s had just registered to T4J with the following data: " +
                "\n\tEmail: %s" +
                "\n\tPassword: %s" +
                "\n" +
                "\n" +
                "Best regards," +
                "\n T4J Administrator." +
                "\n-----------------------------------------------------------------", email, name, email, password);
    }

    public String getPassword() {
        return password;
    }

    public static List<User> getListUsers() {
        return listUsers;
    }

    public void sendEmail(String name, String email, String password) throws IOException {
        out.printf("To: %s" +
                "\n  Subject: Registration to T4J" +
                "\n  Message:" +
                "\n\t%s had just registered to T4J with the following data: " +
                "\n\tEmail: %s" +
                "\n\tPassword: %s" +
                "\n" +
                "\n" +
                "Best regards," +
                "\n T4J Administrator." +
                "\n-----------------------------------------------------------------", email, name, email, password);
        System.out.println("Ficheiro criado.");
        out.close();
    }

}
