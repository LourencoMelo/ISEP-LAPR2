package lapr2.isep.pot.controller;

import lapr2.isep.pot.model.Freelancer;
import lapr2.isep.pot.model.Platform;

import java.io.FileNotFoundException;
import java.util.List;

public class ManagerStatisticsController {

    private final Platform platform;

    private final ApplicationPOT applicationPOT;

    public ManagerStatisticsController() throws FileNotFoundException {
        this.applicationPOT = ApplicationPOT.getInstance();
        this.platform = applicationPOT.getPlatform();
    }

    public double meanByFreelancer(Freelancer freelancer) {
        return platform.meanByFreelancer(freelancer);
    }

    public double standardDeviationByFreelancer(Freelancer freelancer) {
        return platform.standardDeviationByFreelancer(freelancer);
    }

    public int numberDelaysFirstIntervalByFreelancer(Freelancer freelancer) {
        return platform.numberDelaysFirstIntervalByFreelancer(freelancer);
    }

    public int numberDelaysSecondIntervalByFreelancer(Freelancer freelancer) {
        return platform.numberDelaysSecondIntervalByFreelancer(freelancer);
    }

    public int numberDelaysThirdIntervalByFreelancer(Freelancer freelancer) {
        return platform.numberDelaysThirdIntervalByFreelancer(freelancer);
    }

    public List<Freelancer> getListFreelancersToListView() {
        return platform.getRegistFreelancer().getFreelancerList();
    }

    public double meanByOrganization() {
        return platform.meanByOrganization();
    }

    public double standardDeviationByOrganization(){
        return platform.standardDeviationByOrganization();
    }

    public int numberDelaysFirstIntervalByOrganization() {
        return platform.numberDelaysFirstIntervalByOrganization();
    }

    public int numberDelaysSecondIntervalByOrganization() {
        return platform.numberDelaysSecondIntervalByOrganization();
    }

    public int numberDelaysThirdIntervalByOrganization() {
        return platform.numberDelaysThirdIntervalByOrganization();
    }

}
