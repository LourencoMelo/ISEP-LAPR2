package lapr2.isep.pot.controller;

import lapr2.isep.authorization.FacadeAuthorization;
import lapr2.isep.authorization.model.UserSession;
import lapr2.isep.pot.model.Constants;
import lapr2.isep.pot.model.Platform;

import java.io.Serializable;
import java.util.Properties;

import static java.lang.System.getProperties;

public class ApplicationPOT implements Serializable {

    private Platform platform;

    private FacadeAuthorization facadeAuthorization;

    private ApplicationPOT() {
        platform = new Platform();
    }

    private static ApplicationPOT singleton = null;

    public static ApplicationPOT getInstance() {
        if (singleton == null) {
            synchronized (ApplicationPOT.class) {
                singleton = new ApplicationPOT();
            }
        }
        return singleton;
    }

    public Platform getPlatform() {
        return platform;
    }
}
