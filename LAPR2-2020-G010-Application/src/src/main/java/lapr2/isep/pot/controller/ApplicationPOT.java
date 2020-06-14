package lapr2.isep.pot.controller;

import lapr2.isep.pot.model.Platform;

import java.io.FileNotFoundException;
import java.io.Serializable;

/**
 * Main controller of the controllers.
 *
 * @author José Soares, João Beires, José Maia, Lourenço Melo, Gonçalo Ferreira.
 */

public class ApplicationPOT implements Serializable {

    /**
     * Platform's instance
     */
    private Platform platform;

    /**
     * Constructor that initializes platform
     *
     * @throws FileNotFoundException file not found exception
     */
    private ApplicationPOT() throws FileNotFoundException {
        this.platform = new Platform();
        //readSerialization();
    }

    /**
     * Returns platform's instance
     *
     * @return platform
     */
    public Platform getPlatform() {
        return this.platform;
    }

    /**
     * Initializes singleton that secures the existence of only one instance of the applicationPOT class
     */
    private static ApplicationPOT singleton = null;

    /**
     * Returns the singleton
     *
     * @return singleton
     * @throws FileNotFoundException file not found exception
     */
    public static ApplicationPOT getInstance() throws FileNotFoundException {

        if (singleton == null) {
            synchronized (ApplicationPOT.class) {
                singleton = new ApplicationPOT();
            }
        }
        return singleton;
    }


}
