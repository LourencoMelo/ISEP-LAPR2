package lapr2.isep.pot.controller;

import lapr2.isep.authorization.model.User;
import lapr2.isep.pot.UI.console.MainApp;
import lapr2.isep.pot.UI.console.utils.LogInUI;
import lapr2.isep.pot.UI.console.utils.RegisterFreelancerUI;
import lapr2.isep.pot.UI.console.utils.TaskCreationUI;

import java.io.Serializable;
import java.util.List;

public class ApplicationController implements Serializable {

    public static ApplicationController getApplicationController() {
        return LogInUI.getApplicationController();
    }

    public String getAppName() {
        return MainApp.APP_TITLE;
    }

    public List<User> getListUsers() {
        return User.getListUsers();
    }

    public boolean userExist(String email, String password) {
        boolean exist = false;
        for(User user: getListUsers()) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                exist = true;
                break;
            } else {
                exist = false;
            }
        }
        return exist;
    }

    public RegisterFreelancerController getRegistFreelancerController() {
        return RegisterFreelancerUI.getRegisterFreelancerController();
    }

    public TaskCreationController getTaskCreationController() {
        return TaskCreationUI.getTaskCreationController();
    }
}
