package lapr2.isep.pot.controller;

import lapr2.isep.pot.model.Platform;

import java.io.FileNotFoundException;
import java.io.Serializable;

public class ApplicationPOT implements Serializable {

    private final Platform platform;

    private static final String PLATFORM_NAME = "T4J";

    //private FacadeAuthorization facadeAuthorization; --> Necessary???

    private ApplicationPOT() throws FileNotFoundException {
        this.platform = new Platform();
    }

    public Platform getPlatform() {
        return this.platform;
    }

    private static ApplicationPOT singleton = null;

    public static ApplicationPOT getInstance() throws FileNotFoundException {

        if (singleton == null) {
            synchronized (ApplicationPOT.class) {
                singleton = new ApplicationPOT();
            }
        }
        return singleton;
    }
}
