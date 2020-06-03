package lapr2.isep.authorization.model;

import javafx.scene.control.Alert;
import lapr2.isep.pot.UI.console.utils.AlertUI;
import lapr2.isep.pot.controller.ApplicationController;
import lapr2.isep.pot.model.ExternAlgorithmPasswordGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {

    /**
     * Get the application controller instance
     */
    private ApplicationController applicationController = ApplicationController.getApplicationController();
    private final String name;
    private final String email;
    private final String password;
    private static List<User> listUsers = new ArrayList<>();

    public User(String name, String email) {
        if(name == null || email == null || name.isEmpty() || email.isEmpty()) {
            AlertUI.createAlert(Alert.AlertType.INFORMATION, applicationController.getAppName(), "Error", "Incorrect manager's data. Verify again the insert data.");
        }
        this.name = name;
        this.email = email;
        this.password = generatePassword();
        listUsers.add(this);
        System.out.println(String.format("User called %s has %s as password", this.name, this.password));
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

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
        return String.format("%s - %s", this.name, this.email);
    }

    public String getPassword() {
        return password;
    }

    public static List<User> getListUsers() {
        return listUsers;
    }
}
