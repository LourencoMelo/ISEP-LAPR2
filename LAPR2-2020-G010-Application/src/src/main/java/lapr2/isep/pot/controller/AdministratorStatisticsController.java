package lapr2.isep.pot.controller;

import lapr2.isep.pot.model.Freelancer;
import lapr2.isep.pot.model.Platform;


import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.List;

public class AdministratorStatisticsController implements Serializable {

    /**
     * Plataform's instance
     */
    private final Platform platform;

    /**
     * ApplicationPot's instance
     */
    private final ApplicationPOT applicationPOT;

    /**
     * Constructor that get's the applicationPot instance and the platform initialized before on ApplicationPot class.
     * @throws FileNotFoundException
     */
    public AdministratorStatisticsController() throws FileNotFoundException {
        this.applicationPOT = ApplicationPOT.getInstance();
        this.platform = applicationPOT.getPlatform();
    }

//********************************************************************Media e desvio padrao de todos os delays de todos os freelancers da plataforma para o admin*******************************************

    /**
     * Returns the mean of all platform's freelancers delays
     * @return freelancers delays mean
     */
    public double meanByAllPlatformFreelancers() {
        return platform.meanAllPlataformFreelancers();
    }

    /**
     * Returns the standard deviation of all platform's freelancers delays
     * @return freelancers delays standard deviation
     */
    public double standardDeviationAllPlatformFreelancers() {
        return platform.standardDeviationByAllPlataformFreelancers();
    }

//******************************************************************Media e desvio padrao para cada um dos freelancers da plataforma para a scene do admin********************************

    /**
     * Returns the mean of one specific freelancer from the platform
     * @param freelancer selected freelancer
     * @return mean
     */
    public double meanDelayAllTasksByFreelancer(Freelancer freelancer){
        return platform.meanDelayAllTasksByFreelancer(freelancer);
    }

    /**
     * Returns the standard Deviation of one specific freelancer from the platform
     * @param freelancer selected freelancer
     * @return standard deviation
     */
    public double standardDeviationDelayAllTasksFreelancer(Freelancer freelancer){
        return platform.standardDeviationDelayAllTasksFreelancer(freelancer);
    }

//******************************************************************Lista de todos os freelancers da plataforma**********************************************************************************

    /**
     * Returns the list of all platform's freelancers
     * @return platform's freelancers
     */
    public List<Freelancer> getListPlataformFreelancers() {
        return platform.getRegistFreelancer().getFreelancerList();
    }

    //*****************Intervalos para os delays da scene execution statistics por freelancer do admin****************************************************************


    /**
     * Returns the 1st interval of values of delays from a specific freelancer
     * @param freelancer selected freelancer
     * @return int value of number of delays on the interval specified
     */
    public int numberDelaysFirstIntervalFromPlatformByFreelancer(Freelancer freelancer) {
        return platform.numberDelaysFirstIntervalFromPlatformByFreelancer(freelancer);
    }

    /**
     * Returns the 2nd interval of values of delays from a specific freelancer
     * @param freelancer selected freelancer
     * @return int value of number of delays on the interval specified
     */
    public int numberDelaysSecondIntervalFromPlatformByFreelancer(Freelancer freelancer) {
        return platform.numberDelaysSecondIntervalFromPlatformByFreelancer(freelancer);
    }

    /**
     * Returns the 3rd interval of values of delays from a specific freelancer
     * @param freelancer selected freelancer
     * @return int value of number of delays on the interval specified
     */
    public int numberDelaysThirdIntervalFromPlatformByFreelancer(Freelancer freelancer) {
        return platform.numberDelaysThirdIntervalFromPlatformByFreelancer(freelancer);
    }

    //*****************Intervalos para os delays da scene execution statistics de todos os freelancers da plataforma do admin*************************************************

    /**
     * Returns the 1st interval of values of delays from all platform's freelancers
     * @return int value of number of delays on the interval specified
     */
    public int numberDelaysFirstIntervalFromAllPlatform() {
        return platform.numberDelaysFirstIntervalFromAllPlatform();
    }

    /**
     * Returns the 2nd interval of values of delays from all platform's freelancers
     * @return int value of number of delays on the interval specified
     */
    public int numberDelaysSecondIntervalFromAllPlatform() {
        return platform.numberDelaysSecondIntervalFromAllPlatform();
    }

    /**
     * Returns the 3rd interval of values of delays from all platform's freelancers
     * @return int value of number of delays on the interval specified
     */
    public int numberDelaysThirdIntervalFromAllPlatform() {
        return platform.numberDelaysThirdIntervalFromAllPlatform();
    }

    //***************Media e desvio padrao de todos os pagamentos da plataforma respetivos a um freelancer para a scene payment statistics do admin**********************************************

    /**
     * Returns the mean of all the platform's payment values made to a specific freelancer
     * @param freelancer selected freelancer
     * @return mean
     */
    public double meanPaymentsToAFreelancer(Freelancer freelancer) {
        return platform.meanPaymentsToAFreelancer(freelancer);
    }

    /**
     * Returns the standard deviation of all the platform's payment values made to a specific freelancer
     * @param freelancer selected freelancer
     * @return standard deviation
     */
    public double standardDeviationPaymentsToAFreelancer(Freelancer freelancer) {
        return platform.standardDeviationPaymentsToAFreelancer(freelancer);
    }

    //***************Media e desvio padrao de todos os pagamentos da plataforma a todos os freelancers para a scene payment statistics do admin***********************************************

    /**
     * Returns the mean of all platform's payments values
     * @return mean
     */
    public double meanPaymentsToAllPlataformFreelancers() {
        return platform.meanPaymentsToAllPlataformFreelancers();
    }

    /**
     * Returns the standard deviation of all platform's payments values
     * @return standard deviation
     */
    public double standardDeviationPaymentsToAllPlatformFreelancers(){
        return platform.standardDeviationPaymentsToAllPlatformFreelancers();
    }

    //*****************Intervalos para os pagamentos da scene payment statistics de todos os freelancers da plataforma do admin*************************************************

    /**
     * Returns the 1st interval of payment values from all platform's freelancers
     * @return int value of number of payment values on the interval specified
     */
    public int numberPaymentsFirstIntervalToAllPlatformFreelancers() {
        return platform.numberPaymentsFirstIntervalToAllPlatformFreelancers();
    }

    /**
     * Returns the 2nd interval of payment values from all platform's freelancers
     * @return int value of number of payment values on the interval specified
     */
    public int numberPaymentsSecondIntervalToAllPlatformFreelancers() {
        return platform.numberPaymentsSecondIntervalToAllPlatformFreelancers();
    }

    /**
     * Returns the 3rd interval of payment values from all platform's freelancers
     * @return int value of number of payment values on the interval specified
     */
    public int numberPaymentsThirdIntervalToAllPlatformFreelancers() {
        return platform.numberPaymentsThirdIntervalToAllPlatformFreelancers();
    }

    //*****************Intervalos para os pagamentos da scene payment statistics de cada um dos freelancers da plataforma do admin*************************************************

    /**
     * Returns the 1st interval of payment values from a specific platform's freelancer
     * @param freelancer selected freelancer
     * @return int value of number of payment values on the interval specified
     */
    public int numberPaymentsFirstIntervalFromFreelancerAllPlatform(Freelancer freelancer) {
        return platform.numberPaymentsFirstIntervalFromFreelancerAllPlatform(freelancer);
    }

    /**
     * Returns the 2nd interval of payment values from a specific platform's freelancer
     * @param freelancer selected freelancer
     * @return int value of number of payment values on the interval specified
     */
    public int numberPaymentsSecondIntervalFromFreelancerAllPlatform(Freelancer freelancer) {
        return platform.numberPaymentsSecondIntervalFromFreelancerAllPlatform(freelancer);
    }

    /**
     * Returns the 3rd interval of payment values from a specific platform's freelancer
     * @param freelancer selected freelancer
     * @return int value of number of payment values on the interval specified
     */
    public int numberPaymentsThirdIntervalFromFreelancerAllPlatform(Freelancer freelancer) {
        return platform.numberPaymentsThirdIntervalFromFreelancerAllPlatform(freelancer);
    }
}
