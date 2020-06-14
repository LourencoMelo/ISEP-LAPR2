package lapr2.isep.pot.controller;

import lapr2.isep.pot.model.Platform;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

/**
 * Controller of the set of the date when will the transactions be paid.
 *
 * @author José Soares, João Beires, José Maia, Lourenço Melo, Gonçalo Ferreira.
 */
public class SetDateToPayController implements Serializable {

    /**
     * Plataform's instance
     */
    private final Platform platform;

    /**
     * ApplicationPot's instance
     */
    private final ApplicationPOT applicationPOT;

    /**
     * Constructor that initializes applicationPot and platform instances
     *
     * @throws FileNotFoundException file not found exception
     */
    public SetDateToPayController() throws FileNotFoundException {
        this.applicationPOT = ApplicationPOT.getInstance();
        this.platform = applicationPOT.getPlatform();
    }

    /**
     * Sets the date to all the payments
     *
     * @param dateToPay to define
     * @throws IOException if detects the exception
     */
    public void setDateToPay(Date dateToPay) throws IOException {
        platform.setDateToPay(dateToPay);
    }

}
