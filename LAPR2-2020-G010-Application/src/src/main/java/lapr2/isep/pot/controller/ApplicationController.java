package lapr2.isep.pot.controller;

import lapr2.isep.authorization.model.User;
import lapr2.isep.pot.UI.console.MainApp;
import lapr2.isep.pot.UI.console.utils.CreatePaymentTransactionUI;
import lapr2.isep.pot.UI.console.utils.LogInUI;
import lapr2.isep.pot.UI.console.utils.RegisterFreelancerUI;
import lapr2.isep.pot.UI.console.utils.TaskCreationUI;
import lapr2.isep.pot.model.Freelancer;
import lapr2.isep.pot.model.Platform;
import lapr2.isep.pot.model.Task;

import java.io.Serializable;
import java.util.List;

public class ApplicationController implements Serializable {

    private final ApplicationPOT applicationPOT;

    private final Platform platform;

    public ApplicationController(){
        this.applicationPOT = ApplicationPOT.getInstance();
        this.platform = applicationPOT.getPlatform();
    }

    private CreatePaymentTransactionUI createPaymentTransactionUI;


    public String getAppName() {
        return MainApp.APP_TITLE;
    }

    public List<User> getListUsers() {
        return User.getListUsers();
    }

}
