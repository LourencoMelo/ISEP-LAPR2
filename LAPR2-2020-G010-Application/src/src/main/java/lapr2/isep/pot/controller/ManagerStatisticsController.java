package lapr2.isep.pot.controller;

import lapr2.isep.pot.model.Freelancer;
import lapr2.isep.pot.model.Platform;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.List;

/**
 * Controller of the manager window statistics.
 *
 * @author José Soares, João Beires, José Maia, Lourenço Melo, Gonçalo Ferreira.
 */
public class ManagerStatisticsController implements Serializable {

    /**
     * Initializes the platform
     */
    private final Platform platform;

    /**
     * Initializes the applicationPOT
     */
    private final ApplicationPOT applicationPOT;

    /**
     * Constructor that will get the applicationPOT and platform
     *
     * @throws FileNotFoundException if the file is not found
     */
    public ManagerStatisticsController() throws FileNotFoundException {
        this.applicationPOT = ApplicationPOT.getInstance();
        this.platform = applicationPOT.getPlatform();
    }

    /**
     * Returns the mean from a freelancer received
     *
     * @param freelancer to return the mean
     * @return the mean of the freelancer
     */
    public double meanByFreelancer(Freelancer freelancer) {
        return platform.meanByFreelancer(freelancer);
    }

    /**
     * Returns the standard deviation from a freelancer received
     *
     * @param freelancer to return the standard deviation
     * @return the standard deviation of a freelancer
     */
    public double standardDeviationByFreelancer(Freelancer freelancer) {
        return platform.standardDeviationByFreelancer(freelancer);
    }

    /**
     * Returns the number of delays from an interval of a freelancer
     *
     * @param freelancer to calculate the number delays to the first interval
     * @return the number of delays in the first interval
     */
    public int numberDelaysFirstIntervalByFreelancer(Freelancer freelancer) {
        return platform.numberDelaysFirstIntervalByFreelancer(freelancer);
    }

    /**
     * Returns the number of delays from an interval of a freelancer
     *
     * @param freelancer to calculate the number delays to the second interval
     * @return the number of delays in the second interval
     */
    public int numberDelaysSecondIntervalByFreelancer(Freelancer freelancer) {
        return platform.numberDelaysSecondIntervalByFreelancer(freelancer);
    }

    /**
     * Returns the number of delays from an interval of a freelancer
     *
     * @param freelancer to calculate the number delays to the third interval
     * @return the number of delays in the third interval
     */
    public int numberDelaysThirdIntervalByFreelancer(Freelancer freelancer) {
        return platform.numberDelaysThirdIntervalByFreelancer(freelancer);
    }

    /**
     * Returns the list of freelancers
     *
     * @return list of freelancers
     */
    public List<Freelancer> getListFreelancersToListView() {
        return platform.getRegistFreelancer().getFreelancerList();
    }

    /**
     * Returns the mean form an organization
     *
     * @return mean from organization
     */
    public double meanByOrganization() {
        return platform.meanByOrganization();
    }

    /**
     * Standard deviation of an organization
     *
     * @return standard deviation of an organization
     */
    public double standardDeviationByOrganization() {
        return platform.standardDeviationByOrganization();
    }

    /**
     * Returns the number of delays from an interval of an organization
     *
     * @return the number of delays in the first interval
     */
    public int numberDelaysFirstIntervalByOrganization() {
        return platform.numberDelaysFirstIntervalByOrganization();
    }

    /**
     * Returns the number of delays from an interval of an organization
     *
     * @return the number of delays in the second interval
     */
    public int numberDelaysSecondIntervalByOrganization() {
        return platform.numberDelaysSecondIntervalByOrganization();
    }

    /**
     * Returns the number of delays from an interval of an organization
     *
     * @return the number of delays in the third interval
     */
    public int numberDelaysThirdIntervalByOrganization() {
        return platform.numberDelaysThirdIntervalByOrganization();
    }

    /**
     * Returns the payment mean of a freelancer
     *
     * @param freelancer that we will calculate the payment mean
     * @return freelancer payment mean
     */
    public double meanPaymentsByFreelancer(Freelancer freelancer) {
        return platform.meanPaymentsByFreelancer(freelancer);
    }

    /**
     * Returns the payments standard deviation of a freelancer
     *
     * @param freelancer that we will calculate the payment standard deviation
     * @return the payment standard deviation
     */
    public double standardDeviationPaymentsByFreelancer(Freelancer freelancer) {
        return platform.standardDeviationPaymentsByFreelancer(freelancer);
    }
}
