package lapr2.isep.pot.controller;

import lapr2.isep.pot.UI.console.utils.LogInUI;

public class ApplicationController {

    public static ApplicationController getApplicationController() {
        return LogInUI.getApplicationController();
    }

}
