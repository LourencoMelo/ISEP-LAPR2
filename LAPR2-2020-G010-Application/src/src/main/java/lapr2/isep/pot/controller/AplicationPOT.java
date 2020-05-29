package lapr2.isep.pot.controller;

import lapr2.isep.authorization.FacadeAuthorization;
import lapr2.isep.pot.model.Constants;
import lapr2.isep.pot.model.Platform;

import java.util.Properties;

import static java.lang.System.getProperties;

public class AplicationPOT {

    private final Platform platform;
    private final FacadeAuthorization facadeAuthorization;

    private AplicationPOT() {
        Properties props = getProperties();
        this.platform = new Platform(props.getProperty(Constants.PLATFORM_DESIGNATION_PARAMS));
        this.facadeAuthorization = this.platform.getFacadeAutorization();
    }

    private static AplicationPOT singleton = null;

    public static AplicationPOT getInstance() {
        if (singleton == null) {
            synchronized (AplicationPOT.class) {
                singleton = new AplicationPOT();
            }
        }
        return singleton;
    }

    public Platform getPlatform() {
        return platform;
    }
}
