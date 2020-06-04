package lapr2.isep.pot.controller;

import lapr2.isep.authorization.FacadeAuthorization;
import lapr2.isep.authorization.model.UserSession;
import lapr2.isep.pot.model.Constants;
import lapr2.isep.pot.model.Platform;

import java.util.Properties;

import static java.lang.System.getProperties;

public class ApplicationPOT {

    /*//private final Platform platform;
    //private final FacadeAuthorization facadeAuthorization;

    private ApplicationPOT() {
        Properties props = getProperties();
        //this.platform = new Platform(props.getProperty(Constants.PLATFORM_DESIGNATION_PARAMS));
        //this.facadeAuthorization = this.platform.getFacadeAutorization();
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

    /**
     * Returns Current User session
     *
     * @return Current Session
     */
    /*public UserSession getCurrentSession() {
        return this.facadeAuthorization.getCurrentSession();
    }
    /*

}

     */
}
