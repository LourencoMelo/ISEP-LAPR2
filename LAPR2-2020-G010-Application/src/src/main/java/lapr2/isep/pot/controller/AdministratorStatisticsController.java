package lapr2.isep.pot.controller;

import lapr2.isep.pot.model.Freelancer;
import lapr2.isep.pot.model.Platform;


import java.io.FileNotFoundException;
import java.util.List;

public class AdministratorStatisticsController {

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
     * 
     * @return
     */
    public double meanByAllPlatformFreelancers() {
        return platform.meanAllPlataformFreelancers();
    }

    public double standardDeviationAllPlatformFreelancers() {
        return platform.standardDeviationByAllPlataformFreelancers();
    }

//******************************************************************Media e desvio padrao para cada um dos freelancers da plataforma para a scene do admin********************************

    public double meanDelayAllTasksByFreelancer(Freelancer freelancer){
        return platform.meanDelayAllTasksByFreelancer(freelancer);
    }

    public double standardDeviationDelayAllTasksFreelancer(Freelancer freelancer){
        return platform.standardDeviationDelayAllTasksFreelancer(freelancer);
    }

//******************************************************************Lista de todos os freelancers da plataforma**********************************************************************************

    public List<Freelancer> getListPlataformFreelancers() {
        return platform.getRegistFreelancer().getFreelancerList();
    }

    //*****************Intervalos para os delays da scene execution statistics por freelancer do admin****************************************************************

    public int numberDelaysFirstIntervalFromPlatformByFreelancer(Freelancer freelancer) {
        return platform.numberDelaysFirstIntervalFromPlatformByFreelancer(freelancer);
    }

    public int numberDelaysSecondIntervalFromPlatformByFreelancer(Freelancer freelancer) {
        return platform.numberDelaysSecondIntervalFromPlatformByFreelancer(freelancer);
    }

    public int numberDelaysThirdIntervalFromPlatformByFreelancer(Freelancer freelancer) {
        return platform.numberDelaysThirdIntervalFromPlatformByFreelancer(freelancer);
    }

   //*****************Intervalos para os delays da scene execution statistics de todos os freelancers da plataforma do admin*************************************************

    public int numberDelaysFirstIntervalFromAllPlatform() {
        return platform.numberDelaysFirstIntervalFromAllPlatform();
    }

    public int numberDelaysSecondIntervalFromAllPlatform() {
        return platform.numberDelaysSecondIntervalFromAllPlatform();
    }

    public int numberDelaysThirdIntervalFromAllPlatform() {
        return platform.numberDelaysThirdIntervalFromAllPlatform();
    }

    //***************Media e desvio padrao de todos os pagamentos da plataforma respetivos a um freelancer para a scene payment statistics do admin**********************************************

    public double meanPaymentsToAFreelancer(Freelancer freelancer) {
        return platform.meanPaymentsToAFreelancer(freelancer);
    }


    public double standardDeviationPaymentsToAFreelancer(Freelancer freelancer) {
        return platform.standardDeviationPaymentsToAFreelancer(freelancer);
    }

    //***************Media e desvio padrao de todos os pagamentos da plataforma a todos os freelancers para a scene payment statistics do admin***********************************************

    public double meanPaymentsToAllPlataformFreelancers() {
        return platform.meanPaymentsToAllPlataformFreelancers();
    }

    public double standardDeviationPaymentsToAllPlatformFreelancers(){
        return platform.standardDeviationPaymentsToAllPlatformFreelancers();
    }

    //*****************Intervalos para os pagamentos da scene payment statistics de todos os freelancers da plataforma do admin*************************************************

    public int numberPaymentsFirstIntervalToAllPlatformFreelancers() {
        return platform.numberPaymentsFirstIntervalToAllPlatformFreelancers();
    }

    public int numberPaymentsSecondIntervalToAllPlatformFreelancers() {
        return platform.numberPaymentsSecondIntervalToAllPlatformFreelancers();
    }

    public int numberPaymentsThirdIntervalToAllPlatformFreelancers() {
        return platform.numberPaymentsThirdIntervalToAllPlatformFreelancers();
    }

    //*****************Intervalos para os pagamentos da scene payment statistics de cada um dos freelancers da plataforma do admin*************************************************

    public int numberPaymentsFirstIntervalFromFreelancerAllPlatform(Freelancer freelancer) {
        return platform.numberPaymentsFirstIntervalFromFreelancerAllPlatform(freelancer);
    }

    public int numberPaymentsSecondIntervalFromFreelancerAllPlatform(Freelancer freelancer) {
        return platform.numberPaymentsSecondIntervalFromFreelancerAllPlatform(freelancer);
    }

    public int numberPaymentsThirdIntervalFromFreelancerAllPlatform(Freelancer freelancer) {
        return platform.numberPaymentsThirdIntervalFromFreelancerAllPlatform(freelancer);
    }
}
