package lapr2.isep.pot.controller;

import lapr2.isep.pot.model.Platform;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

public class SetDateToPayController implements Serializable {

    /**
     * Plataform's instance
     */
    private final Platform platform;

    /**
     * ApplicationPot's instance
     */
    private final ApplicationPOT applicationPOT;

    public SetDateToPayController() throws FileNotFoundException {
        this.applicationPOT = ApplicationPOT.getInstance();
        this.platform = applicationPOT.getPlatform();
    }

    public void setDateToPay(Date dateToPay) throws IOException {
        platform.setDateToPay(dateToPay);
    }

}
