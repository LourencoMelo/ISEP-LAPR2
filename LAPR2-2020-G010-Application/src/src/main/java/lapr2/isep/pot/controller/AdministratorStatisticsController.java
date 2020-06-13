package lapr2.isep.pot.controller;

import lapr2.isep.pot.model.Freelancer;
import lapr2.isep.pot.model.Platform;

import java.io.FileNotFoundException;

public class AdministratorStatisticsController {

    private final Platform platform;

    private final ApplicationPOT applicationPOT;

    public AdministratorStatisticsController() throws FileNotFoundException {
        this.applicationPOT = ApplicationPOT.getInstance();
        this.platform = applicationPOT.getPlatform();
    }


    public double meanByAllPlatformFreelancers(){
        return platform.meanAllPlataformFreelancers();
    }

    public double standardDeviationAllPlatformFreelancers(){
        return platform.standardDeviationByAllOrganization();
    }
}
