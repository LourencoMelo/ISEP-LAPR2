package lapr2.isep.pot.controller;

import lapr2.isep.pot.model.Freelancer;
import lapr2.isep.pot.model.Platform;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.List;

/**
 * Controller of the collaborator window statistics.
 *
 * @author José Soares, João Beires, José Maia, Lourenço Melo, Gonçalo Ferreira.
 */

public class CollaboratorStatisticsController implements Serializable {

    /**
     * Platform's instance
     */
    private final Platform platform;

    /**
     * ApplicationPOT's instance
     */
    private final ApplicationPOT applicationPOT;

    /**
     * Constructor that initializes the applicationPOT and the platform instances.
     *
     * @throws FileNotFoundException file not found exception
     */
    public CollaboratorStatisticsController() throws FileNotFoundException {
        this.applicationPOT = ApplicationPOT.getInstance();
        this.platform = applicationPOT.getPlatform();
    }

    /**
     * Returns the mean of the values of delays of tasks from the current collaborator's organization and by a specific freelancer
     *
     * @param freelancer selected freelancer
     * @return mean of delays
     */
    public double meanByFreelancer(Freelancer freelancer) {
        return platform.meanByFreelancer(freelancer);
    }

    /**
     * Returns the standard deviation of the values of delays of tasks from the current collaborator's organization and by a specific freelancer
     *
     * @param freelancer selected freelancer
     * @return standard deviation of delays
     */
    public double standardDeviationByFreelancer(Freelancer freelancer) {
        return platform.standardDeviationByFreelancer(freelancer);
    }

    /**
     * Returns the number of delays, from a specific freelancer, that belong to the specified interval
     *
     * @param freelancer selected freelancer
     * @return int number of delays
     */
    public int numberDelaysFirstIntervalByFreelancer(Freelancer freelancer) {
        return platform.numberDelaysFirstIntervalByFreelancer(freelancer);
    }

    /**
     * Returns the number of delays, from a specific freelancer, that belong to the specified interval
     *
     * @param freelancer selected freelancer
     * @return int number of delays
     */
    public int numberDelaysSecondIntervalByFreelancer(Freelancer freelancer) {
        return platform.numberDelaysSecondIntervalByFreelancer(freelancer);
    }

    /**
     * Returns the number of delays, from a specific freelancer, that belong to the specified interval
     *
     * @param freelancer selected freelancer
     * @return int number of delays
     */
    public int numberDelaysThirdIntervalByFreelancer(Freelancer freelancer) {
        return platform.numberDelaysThirdIntervalByFreelancer(freelancer);
    }

    /**
     * Returns the freelancer's list from the platform
     *
     * @return freelancer's list
     */
    public List<Freelancer> getListFreelancersToListView() {
        return platform.getRegistFreelancer().getFreelancerList();
    }

    /**
     * Returns the mean of all the delays from the current collaborator's organization
     *
     * @return mean of delays
     */
    public double meanByOrganization() {
        return platform.meanByOrganization();
    }

    /**
     * Returns the standard deviation of all the delays from the current collaborator's organization
     *
     * @return standard deviation of delays
     */
    public double standardDeviationByOrganization() {
        return platform.standardDeviationByOrganization();
    }

    /**
     * Returns the number of delays from all the tasks that belong to the current collaborator's organization and to the specified interval
     *
     * @return int number of delays
     */
    public int numberDelaysFirstIntervalByOrganization() {
        return platform.numberDelaysFirstIntervalByOrganization();
    }

    /**
     * Returns the number of delays from all the tasks that belong to the current collaborator's organization and to the specified interval
     *
     * @return int number of delays
     */
    public int numberDelaysSecondIntervalByOrganization() {
        return platform.numberDelaysSecondIntervalByOrganization();
    }

    /**
     * Returns the number of delays from all the tasks that belong to the current collaborator's organization and to the specified interval
     *
     * @return int number of delays
     */
    public int numberDelaysThirdIntervalByOrganization() {
        return platform.numberDelaysThirdIntervalByOrganization();
    }

    /**
     * Returns the mean of the payments values by a specific freelancer
     *
     * @param freelancer selected freelancer
     * @return mean of payments
     */
    public double meanPaymentsByFreelancer(Freelancer freelancer) {
        return platform.meanPaymentsByFreelancer(freelancer);
    }

    /**
     * Returns the standard deviation of the payments values by a specific freelancer
     *
     * @param freelancer selected freelancer
     * @return standard deviation of payments
     */
    public double standardDeviationPaymentsByFreelancer(Freelancer freelancer) {
        return platform.standardDeviationPaymentsByFreelancer(freelancer);
    }
}
