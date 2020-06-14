package lapr2.isep.pot.model;

import javafx.scene.control.Alert;
import lapr2.isep.authorization.model.User;
import lapr2.isep.pot.UI.console.utils.AlertUI;
import lapr2.isep.pot.model.List.PaymentTransactionList;
import lapr2.isep.pot.model.List.TaskList;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Platform implements Serializable {


    private Freelancer selectedFreelancer;

    private Task selectedTask;

    private TransactionsRegist transactionsRegist;

    private String currentUserEmail;

    /**
     * Regist Freelancer instance
     */

    private RegistFreelancer registFreelancer;

    /**
     * Regist Organization instance
     */
    private RegistOrganization registOrganization;

    private TaskList taskList;

    public Platform() throws FileNotFoundException {
        this.registFreelancer = new RegistFreelancer();
        this.registOrganization = new RegistOrganization();
        this.taskList = new TaskList();
        readSerialization();
        //this.transactionsRegist = new TransactionsRegist();
    }

    private void readSerialization() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("SerializableData"));
            this.registFreelancer = (RegistFreelancer) in.readObject();
//            this.registOrganization = (RegistOrganization) in.readObject();
            this.taskList = (TaskList) in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            Alert alert = AlertUI.createAlert(Alert.AlertType.WARNING, "Improve T4J", "Error", "The binary file wasn't read.");
            alert.show();
        }
    }


    public void serialization(){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File("SerializableData"));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(registFreelancer);
//            objectOutputStream.writeObject(registOrganization);
            objectOutputStream.writeObject(taskList);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            Alert alert = AlertUI.createAlert(Alert.AlertType.WARNING, "Improve T4J", "Error", "The binary file didn't save the information.");
            alert.show();
        }
    }

    /**
     * Returns Organization
     *
     * @return Organization
     */
    public RegistOrganization getRegistOrganization() {
        return this.registOrganization;
    }

    /**
     * Returns RegistFreelancer
     *
     * @return registFreelancer
     */

    public RegistFreelancer getRegistFreelancer() {
        return this.registFreelancer;
    }

    public boolean addOrganization(Organization organization) {
        return registOrganization.addOrganization(organization);
    }

    /**
     * confirms if the the list has the organization
     *
     * @param organization to confirm
     * @return true if has or false if not
     */
    public boolean hasOrganization(Organization organization) {
        return registOrganization.hasOrganization(organization);
    }


//    public String getRoleUser(User user) {
//        return user.getRole();
//    }

    public Freelancer getSelectedFreelancer() {
        return selectedFreelancer;
    }

    public void setSelectedFreelancer(Freelancer selectedFreelancer) {
        this.selectedFreelancer = selectedFreelancer;
    }

    public Task getSelectedTask() {
        return selectedTask;
    }

    public void setSelectedTask(Task selectedTask) {
        this.selectedTask = selectedTask;
    }

    public boolean userIsCollaborator(User user) {
        for (Collaborator collaborator : getListCollaboratorsAllOrganizations()) {
            if (collaborator.getEmail().equalsIgnoreCase(user.getEmail()) && collaborator.getName().equalsIgnoreCase(user.getName())) {
                for (User userAux : getListUsers()) {
                    if (userAux.getPassword().equalsIgnoreCase(user.getPassword())) {
                        this.currentUserEmail = collaborator.getEmail();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean userIsManager(User user) {
        for (Manager manager : getListManagersAllOrganizations()) {
            if (manager.getEmail().equalsIgnoreCase(user.getEmail()) && manager.getName().equalsIgnoreCase(user.getName())) {
                for (User userAux : getListUsers()) {
                    if (userAux.getPassword().equalsIgnoreCase(user.getPassword())) {
                        this.currentUserEmail = manager.getEmail();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean userExist(String email, String password) {
        boolean exist = false;
        for (User user : User.getListUsers()) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                exist = true;
                break;
            }
        }
        return exist;
    }

    public User createUser(String name, String email, String password) throws FileNotFoundException {
        return new User(name, email, password);
    }

    public boolean userExist(User user) {
        return User.getListUsers().contains(user);
    }

    public Organization getOrganizationByCollaborator(Collaborator collaborator) {
        return registOrganization.getOrganizationByCollaborator(collaborator);
    }

    public List<Collaborator> getListCollaboratorsAllOrganizations() {
        return registOrganization.getListCollaboratorsAllOrganizations();
    }

    public List<Manager> getListManagersAllOrganizations() {
        return registOrganization.getListManagersAllOrganizations();
    }

    public List<User> getListUsers() {
        return User.getListUsers();
    }


    public List<Task> getListOfTasksFromOrganization(Organization organization) {
        return organization.getTaskList();
    }

    public Collaborator getCollaborator() {
        return registOrganization.getCollaborator();
    }

    public TaskList getTasksList() {
        return taskList;
    }

    public String getCurrentUserEmail() {
        return currentUserEmail;
    }

    public Organization getOrganizationCurrentUser() {
        return registOrganization.getOrganizationByUserEmail(getCurrentUserEmail());
    }

    public PaymentTransactionList getPaymentListByOrganization() {
        return getOrganizationCurrentUser().getPaymentTransactionList();
    }

    public List<Freelancer> getFreelancersByOrganization() {
        List<Freelancer> listFreelancer = new ArrayList<>();
        for (PaymentTransaction paymentTransaction : getPaymentListByOrganization().getListTotalPaymentsTransactions()) {
            listFreelancer.add(paymentTransaction.getFreelancer());
        }
        return listFreelancer;
    }

    /**
     * Return the mean of delays of 1 organization
     *
     * @return mean of delays
     */
    public double meanByOrganization() {
        double summation = 0;
        int numberOfTasksExecutedByFreelancer = 0;
        summation += getOrganizationCurrentUser().getPaymentTransactionList().getTotalDelays();
        numberOfTasksExecutedByFreelancer = getOrganizationCurrentUser().getPaymentTransactionList().getListTotalPaymentsTransactions().size();
        double mean = summation / numberOfTasksExecutedByFreelancer;
        getOrganizationCurrentUser().setMean(mean);
        return mean;
    }


//*************************************Media para um freelancer que tenha feito a task da organizacao atual para as scenes de execution statistics do manager e do colaborador***********

    public double meanByFreelancer(Freelancer freelancer) {
        double summation = getOrganizationCurrentUser().addSumOfDelays(freelancer);
        int numberOfTasksExecutedByFreelancer = getOrganizationCurrentUser().getPaymentTransactionList().getNumberOfTasks(freelancer);
        double mean = summation / numberOfTasksExecutedByFreelancer;
        freelancer.setMean(mean);
        return mean;

    }

//    public double meanByFreelancerAllOrganizations(Freelancer freelancer) {
//        return 0;
//    }

    //*************************************************Media de todos os freelancers da plataforma para a scene do admin execution statistics***********************************************
    public double meanAllPlataformFreelancers() {
        double summation = 0;
        int numberOfTransactions = 0;
        for (Organization organization : registOrganization.getListOrganizations()) {
            for (PaymentTransaction paymentTransaction : organization.getPaymentTransactionList().getListTotalPaymentsTransactions()) {
                summation += paymentTransaction.getDelay();
                numberOfTransactions++;
            }
        }
        double mean = summation / numberOfTransactions;
        return mean;
    }

//******************************************Desvio padrao de todos os freelancers da plataforma para a scene do administrator execution statistics********************************

    public double standardDeviationByAllPlataformFreelancers() {
        List<Double> listDelays = new ArrayList<>();
        double summation = 0;
        for (Organization organization : registOrganization.getListOrganizations()) {
            for (PaymentTransaction paymentTransaction : organization.getPaymentTransactionList().getListTotalPaymentsTransactions()) {
                listDelays.add(paymentTransaction.getDelay());
            }
        }

        for (double delay : listDelays) {
            summation += Math.pow(meanAllPlataformFreelancers() - delay, 2);
        }
        double standardDeviation = Math.sqrt(summation / listDelays.size());
        return standardDeviation;
    }

    //************************************************Media de delays por freelancer na scene do admin execution statistics******************************************************
    public double meanDelayAllTasksByFreelancer(Freelancer freelancer) {
        double summationFreelancersDelay = 0;
        int numberOfTransactions = 0;
        for (Organization organization : registOrganization.getListOrganizations()) {
            for (PaymentTransaction paymentTransaction : organization.getPaymentTransactionList().getListTotalPaymentsTransactions()) {
                if (paymentTransaction.getFreelancer().equals(freelancer)) {
                    summationFreelancersDelay += paymentTransaction.getDelay();
                    numberOfTransactions++;
                }
            }
        }
        double mean = summationFreelancersDelay / numberOfTransactions;
        return mean;
    }

//***********************************************Desvio padrao por freelancer na scene do admin execution statistics************************************************************

    public double standardDeviationDelayAllTasksFreelancer(Freelancer freelancer) {
        List<Double> listDelaysFromFreelancer = new ArrayList<>();
        double summation = 0;
        for (Organization organization : registOrganization.getListOrganizations()) {
            for (PaymentTransaction paymentTransaction : organization.getPaymentTransactionList().getListTotalPaymentsTransactions()) {
                if (paymentTransaction.getFreelancer().equals(freelancer)) {
                    listDelaysFromFreelancer.add(paymentTransaction.getDelay());
                }
            }
        }

        for (double delay : listDelaysFromFreelancer) {
            summation += Math.pow(meanDelayAllTasksByFreelancer(freelancer) - delay, 2);
        }
        double standardDeviation = Math.sqrt(summation / listDelaysFromFreelancer.size());
        return standardDeviation;
    }

//**************************************************Desvio padrao de toda a organizacao atual para a scene do colaborador e do manager*****************************************************

    public double standardDeviationByOrganization() {
        List<Double> listTimesFromDelays = new ArrayList<>();
        for (PaymentTransaction paymentTransaction : getOrganizationCurrentUser().getPaymentTransactionList().getListTotalPaymentsTransactions()) {
            listTimesFromDelays.add(paymentTransaction.getDelay());
        }
        double summation = 0;
        for (double doubleAux : listTimesFromDelays) {
            summation += Math.pow(meanByOrganization() - doubleAux, 2);
        }
        double standardDeviation = Math.sqrt(summation / listTimesFromDelays.size());
        getOrganizationCurrentUser().setStandardDeviation(standardDeviation);
        return standardDeviation;
    }


    public double standardDeviationByFreelancer(Freelancer freelancer) {
        List<Double> listTimesFromDelays = new ArrayList<>();
        for (PaymentTransaction paymentTransaction : getOrganizationCurrentUser().getPaymentTransactionList().getListTotalPaymentsTransactions()) {
            if (paymentTransaction.getFreelancer().equals(freelancer)) {
                listTimesFromDelays.add(paymentTransaction.getDelay());
            }
        }
        double summation = 0;
        for (double doubleAux : listTimesFromDelays) {
            summation += Math.pow(freelancer.getMean() - doubleAux, 2);
        }
        double standardDeviation = Math.sqrt(summation / listTimesFromDelays.size());
        return standardDeviation;
    }

    //**************************************************Intervalos para a scene do colaborador e do manager por freelancer e acerca dos delays***********************************************
    public int numberDelaysFirstIntervalByFreelancer(Freelancer freelancer) {
        int delays = 0;
        for (PaymentTransaction paymentTransaction : getOrganizationCurrentUser().getPaymentTransactionList().getListTotalPaymentsTransactions()) {
            if (paymentTransaction.getFreelancer().equals(freelancer)) {
                if (paymentTransaction.getDelay() <= (freelancer.getMean() - standardDeviationByFreelancer(freelancer))) {
                    delays++;
                }
            }
        }
        return delays;
    }

    public int numberDelaysSecondIntervalByFreelancer(Freelancer freelancer) {
        int delays = 0;
        for (PaymentTransaction paymentTransaction : getOrganizationCurrentUser().getPaymentTransactionList().getListTotalPaymentsTransactions()) {
            if (paymentTransaction.getFreelancer().equals(freelancer)) {
                if (paymentTransaction.getDelay() > (freelancer.getMean() - standardDeviationByFreelancer(freelancer)) && (paymentTransaction.getDelay() < (freelancer.getMean()) + standardDeviationByFreelancer(freelancer))) {
                    delays++;
                }
            }
        }
        return delays;
    }

    public int numberDelaysThirdIntervalByFreelancer(Freelancer freelancer) {
        int delays = 0;
        for (PaymentTransaction paymentTransaction : getOrganizationCurrentUser().getPaymentTransactionList().getListTotalPaymentsTransactions()) {
            if (paymentTransaction.getFreelancer().equals(freelancer)) {
                if (paymentTransaction.getDelay() >= (freelancer.getMean() + standardDeviationByFreelancer(freelancer))) {
                    delays++;
                }
            }
        }
        return delays;
    }

//***********************************************Intervalos para a scene do colaborador e do manager onde apresenta os delays todos da organizaçao respetiva***********************************************

    public int numberDelaysFirstIntervalByOrganization() {
        int delays = 0;

        for (PaymentTransaction paymentTransaction : getOrganizationCurrentUser().getPaymentTransactionList().getListTotalPaymentsTransactions()) {
            if (paymentTransaction.getDelay() <= (getOrganizationCurrentUser().getMean() - getOrganizationCurrentUser().getStandardDeviation())) {
                delays++;
            }

        }
        return delays;
    }

    public int numberDelaysSecondIntervalByOrganization() {
        int delays = 0;

        for (PaymentTransaction paymentTransaction : getOrganizationCurrentUser().getPaymentTransactionList().getListTotalPaymentsTransactions()) {
            if (paymentTransaction.getDelay() > (getOrganizationCurrentUser().getMean() - getOrganizationCurrentUser().getStandardDeviation()) && (paymentTransaction.getDelay() < getOrganizationCurrentUser().getMean() + getOrganizationCurrentUser().getStandardDeviation())) {
                delays++;
            }

        }
        return delays;
    }

    public int numberDelaysThirdIntervalByOrganization() {
        int delays = 0;

        for (PaymentTransaction paymentTransaction : getOrganizationCurrentUser().getPaymentTransactionList().getListTotalPaymentsTransactions()) {
            if (paymentTransaction.getDelay() >= getOrganizationCurrentUser().getMean() + getOrganizationCurrentUser().getStandardDeviation()) {
                delays++;
            }

        }
        return delays;
    }

//************************************************************Media dos pagamentos por freelancer para a scene do colaborador e do manager***************************************************************

    public double meanPaymentsByFreelancer(Freelancer freelancer) {
        double summation = 0;
        int numberOfTransactions = 0;
        for (PaymentTransaction paymentTransaction : getOrganizationCurrentUser().getPaymentTransactionList().getListTotalPaymentsTransactions()) {
            if (paymentTransaction.getFreelancer().equals(freelancer)) {
                summation += paymentTransaction.getAmountPay();
                numberOfTransactions++;
            }
        }
        double mean = summation / numberOfTransactions;
        return mean;
    }

//************************************************************Desvio padrao dos pagamentos por freelancer para a scene do colaborador e do manager*************************************

    public double standardDeviationPaymentsByFreelancer(Freelancer freelancer) {
        List<Double> listPayment = new ArrayList<>();
        for (PaymentTransaction paymentTransaction : getOrganizationCurrentUser().getPaymentTransactionList().getListTotalPaymentsTransactions()) {
            if (paymentTransaction.getFreelancer().equals(freelancer)) {
                listPayment.add(paymentTransaction.getAmountPay());
            }
        }

        double summation = 0;
        for (double amountToPay : listPayment) {
            summation += Math.pow(meanPaymentsByFreelancer(freelancer) - amountToPay, 2);
        }
        double standardDeviation = Math.sqrt(summation / listPayment.size());

        return standardDeviation;

    }

//************************************************************* Intervalos da scene de execution statistics do administrator ********************************************************

    public int numberDelaysFirstIntervalFromPlatformByFreelancer(Freelancer freelancer) {
        int delays = 0;

        for (Organization organization : registOrganization.getListOrganizations()) {
            for (PaymentTransaction paymentTransaction : organization.getPaymentTransactionList().getListTotalPaymentsTransactions()) {
                if (paymentTransaction.getFreelancer().equals(freelancer)) {
                    if (paymentTransaction.getDelay() <= (meanDelayAllTasksByFreelancer(freelancer) - standardDeviationDelayAllTasksFreelancer(freelancer))) {
                        delays++;
                    }
                }
            }
        }
        return delays;
    }

    public int numberDelaysSecondIntervalFromPlatformByFreelancer(Freelancer freelancer) {
        int delays = 0;

        for (Organization organization : registOrganization.getListOrganizations()) {
            for (PaymentTransaction paymentTransaction : organization.getPaymentTransactionList().getListTotalPaymentsTransactions()) {
                if (paymentTransaction.getFreelancer().equals(freelancer)) {
                    if (paymentTransaction.getDelay() > (meanDelayAllTasksByFreelancer(freelancer) - standardDeviationDelayAllTasksFreelancer(freelancer)) && paymentTransaction.getDelay() < meanDelayAllTasksByFreelancer(freelancer) + standardDeviationDelayAllTasksFreelancer(freelancer)) {
                        delays++;
                    }
                }
            }
        }
        return delays;
    }

    public int numberDelaysThirdIntervalFromPlatformByFreelancer(Freelancer freelancer) {
        int delays = 0;

        for (Organization organization : registOrganization.getListOrganizations()) {
            for (PaymentTransaction paymentTransaction : organization.getPaymentTransactionList().getListTotalPaymentsTransactions()) {
                if (paymentTransaction.getFreelancer().equals(freelancer)) {
                    if (paymentTransaction.getDelay() >= meanDelayAllTasksByFreelancer(freelancer) + standardDeviationDelayAllTasksFreelancer(freelancer)) {
                        delays++;
                    }
                }
            }
        }
        return delays;
    }

//****************************************Intervalos para os delays de todos os freelancers da plataforma para a scene execution statistics do admin***************************************************************************************************


    public int numberDelaysFirstIntervalFromAllPlatform() {
        int delays = 0;

        for (Organization organization : registOrganization.getListOrganizations()) {
            for (PaymentTransaction paymentTransaction : organization.getPaymentTransactionList().getListTotalPaymentsTransactions()) {
                if (paymentTransaction.getDelay() <= (meanAllPlataformFreelancers() - standardDeviationByAllPlataformFreelancers())) {
                    delays++;
                }
            }
        }

        return delays;
    }

    public int numberDelaysSecondIntervalFromAllPlatform() {
        int delays = 0;

        for (Organization organization : registOrganization.getListOrganizations()) {
            for (PaymentTransaction paymentTransaction : organization.getPaymentTransactionList().getListTotalPaymentsTransactions()) {

                if (paymentTransaction.getDelay() > (meanAllPlataformFreelancers() - standardDeviationByAllPlataformFreelancers()) && paymentTransaction.getDelay() < meanAllPlataformFreelancers() + standardDeviationByAllPlataformFreelancers()) {
                    delays++;
                }

            }
        }
        return delays;
    }

    public int numberDelaysThirdIntervalFromAllPlatform() {
        int delays = 0;

        for (Organization organization : registOrganization.getListOrganizations()) {
            for (PaymentTransaction paymentTransaction : organization.getPaymentTransactionList().getListTotalPaymentsTransactions()) {
                if (paymentTransaction.getDelay() >= meanAllPlataformFreelancers() + standardDeviationByAllPlataformFreelancers()) {
                    delays++;
                }
            }
        }
        return delays;
    }

//**********************************************Media e desvio padrao dos pagamentos para todos os freelancers da plataforma para a scene do admin***************************************************************

    public double meanPaymentsToAllPlataformFreelancers() {
        double summation = 0;
        double numberOfpayments = 0;
        for (Organization organization : registOrganization.getListOrganizations()) {
            for (PaymentTransaction paymentTransaction : organization.getPaymentTransactionList().getListTotalPaymentsTransactions()) {
                summation += paymentTransaction.getAmountPay();
                numberOfpayments++;
            }
        }
        double mean = summation / numberOfpayments;

        return mean;
    }


    public double standardDeviationPaymentsToAllPlatformFreelancers() {
        double summation = 0;
        List<Double> listPayments = new ArrayList<>();
        for (Organization organization : registOrganization.getListOrganizations()) {
            for (PaymentTransaction paymentTransaction : organization.getPaymentTransactionList().getListTotalPaymentsTransactions()) {
                listPayments.add(paymentTransaction.getAmountPay());
            }
        }

        for (double amountToPay : listPayments) {
            summation += Math.pow(meanPaymentsToAllPlataformFreelancers() - amountToPay, 2);
        }
        double standardDeviation = Math.sqrt(summation / listPayments.size());
        return standardDeviation;
    }

//**************************************************Media e desvio padrao dos pagamentos a cada um dos freelancers da plataforma para a scene do admin***********************************************

    public double meanPaymentsToAFreelancer(Freelancer freelancer) {
        double summationFromFreelancer = 0;
        double numberOfPaymentsFromFreelancer = 0;
        for (Organization organization : registOrganization.getListOrganizations()) {
            for (PaymentTransaction paymentTransaction : organization.getPaymentTransactionList().getListTotalPaymentsTransactions()) {
                if (paymentTransaction.getFreelancer().equals(freelancer)) {
                    summationFromFreelancer += paymentTransaction.getAmountPay();
                    numberOfPaymentsFromFreelancer++;
                }
            }
        }
        double mean = summationFromFreelancer / numberOfPaymentsFromFreelancer;

        return mean;
    }

    public double standardDeviationPaymentsToAFreelancer(Freelancer freelancer) {
        double summation = 0;
        List<Double> listPaymentsToFreelancer = new ArrayList<>();
        for (Organization organization : registOrganization.getListOrganizations()) {
            for (PaymentTransaction paymentTransaction : organization.getPaymentTransactionList().getListTotalPaymentsTransactions()) {
                if (freelancer.equals(paymentTransaction.getFreelancer())) {
                    listPaymentsToFreelancer.add(paymentTransaction.getAmountPay());
                }
            }
        }

        for (double amountToPayToFreelancer : listPaymentsToFreelancer) {
            summation += Math.pow(meanPaymentsToAFreelancer(freelancer) - amountToPayToFreelancer, 2);
        }
        double standardDeviation = Math.sqrt(summation / listPaymentsToFreelancer.size());

        return standardDeviation;
    }

//***********************************************Intervalos para os dados dos pagamentos a todos os freelancers da plataforma para a scene do admin*************************************************************

    public int numberPaymentsFirstIntervalToAllPlatformFreelancers() {
        int delays = 0;

        for (Organization organization : registOrganization.getListOrganizations()) {
            for (PaymentTransaction paymentTransaction : organization.getPaymentTransactionList().getListTotalPaymentsTransactions()) {
                if (paymentTransaction.getAmountPay() <= (meanPaymentsToAllPlataformFreelancers() - standardDeviationPaymentsToAllPlatformFreelancers())) {
                    delays++;
                }
            }
        }

        return delays;
    }

    public int numberPaymentsSecondIntervalToAllPlatformFreelancers() {
        int delays = 0;

        for (Organization organization : registOrganization.getListOrganizations()) {
            for (PaymentTransaction paymentTransaction : organization.getPaymentTransactionList().getListTotalPaymentsTransactions()) {
                if (paymentTransaction.getAmountPay() > (meanPaymentsToAllPlataformFreelancers() - standardDeviationPaymentsToAllPlatformFreelancers()) && paymentTransaction.getAmountPay() < meanPaymentsToAllPlataformFreelancers() + standardDeviationPaymentsToAllPlatformFreelancers()) {
                    delays++;
                }

            }
        }
        return delays;
    }

    public int numberPaymentsThirdIntervalToAllPlatformFreelancers() {
        int delays = 0;

        for (Organization organization : registOrganization.getListOrganizations()) {
            for (PaymentTransaction paymentTransaction : organization.getPaymentTransactionList().getListTotalPaymentsTransactions()) {
                if (paymentTransaction.getAmountPay() >= meanPaymentsToAllPlataformFreelancers() + standardDeviationPaymentsToAllPlatformFreelancers()) {
                    delays++;
                }
            }
        }
        return delays;
    }


//************************************************Intervalos para os dados dos pagamentos a cada um dos freelancers da plataforma para a scene do admin*************************************************************

    public int numberPaymentsFirstIntervalFromFreelancerAllPlatform(Freelancer freelancer) {
        int delays = 0;

        for (Organization organization : registOrganization.getListOrganizations()) {
            for (PaymentTransaction paymentTransaction : organization.getPaymentTransactionList().getListTotalPaymentsTransactions()) {
                if (freelancer.equals(paymentTransaction.getFreelancer())) {
                    if ((paymentTransaction.getAmountPay()) <= (meanPaymentsToAFreelancer(freelancer) - standardDeviationPaymentsToAFreelancer(freelancer))) {
                        delays++;
                    }
                }
            }
        }

        return delays;
    }

    public int numberPaymentsSecondIntervalFromFreelancerAllPlatform(Freelancer freelancer) {
        int delays = 0;

        for (Organization organization : registOrganization.getListOrganizations()) {
            for (PaymentTransaction paymentTransaction : organization.getPaymentTransactionList().getListTotalPaymentsTransactions()) {
                if (freelancer.equals(paymentTransaction.getFreelancer())) {
                    if ((paymentTransaction.getDelay()) > (meanPaymentsToAFreelancer(freelancer) - standardDeviationPaymentsToAFreelancer(freelancer)) && (paymentTransaction.getDelay()) < (meanPaymentsToAFreelancer(freelancer) + standardDeviationPaymentsToAFreelancer(freelancer))) {
                        delays++;
                    }
                }
            }
        }
        return delays;
    }

    public int numberPaymentsThirdIntervalFromFreelancerAllPlatform(Freelancer freelancer) {
        int delays = 0;

        for (Organization organization : registOrganization.getListOrganizations()) {
            for (PaymentTransaction paymentTransaction : organization.getPaymentTransactionList().getListTotalPaymentsTransactions()) {
                if (freelancer.equals(paymentTransaction.getFreelancer())) {
                    if (paymentTransaction.getDelay() >= meanPaymentsToAFreelancer(freelancer) + standardDeviationPaymentsToAFreelancer(freelancer)) {
                        delays++;
                    }
                }
            }
        }
        return delays;
    }

    public void setDateToPay(Date dateToPay) throws IOException {
        for (PaymentTransaction paymentTransaction : getOrganizationCurrentUser().getPaymentTransactionList().getNotPaidTransactionList()) {
            paymentTransaction.setDateToPay(dateToPay);
        }
    }

    public double percentageDelayByFreelancer(Freelancer freelancer) {
        double numberOfTasksWithDelay = 0;
        double numberOfTaskFreelancerDid = 0;
        for (Organization organization : registOrganization.getListOrganizations()) {
            for (PaymentTransaction paymentTransaction : organization.getPaymentTransactionList().getListTotalPaymentsTransactions()) {
                if (paymentTransaction.getFreelancer().equals(freelancer)) {
                    numberOfTaskFreelancerDid++;
                    if (paymentTransaction.getDelay() > 0) {
                        numberOfTasksWithDelay++;
                    }
                }
            }
        }
        return numberOfTasksWithDelay / numberOfTaskFreelancerDid;
    }

    public double percentageDelayTotal() {
        double numberOfTasksWithDelay = 0;
        double numberOfTasks = 0;
        for (Organization organization : registOrganization.getListOrganizations()) {
            for (PaymentTransaction paymentTransaction : organization.getPaymentTransactionList().getListTotalPaymentsTransactions()) {
                numberOfTasks++;
                if (paymentTransaction.getDelay() > 0) {
                    numberOfTasksWithDelay++;
                }
            }
        }
        return numberOfTasksWithDelay / numberOfTasks;
    }

    public void sendEmailWithDelayHigherThanThree() throws FileNotFoundException {
        for (Freelancer freelancer : getRegistFreelancer().getFreelancerList()) {
            if (meanDelayAllTasksByFreelancer(freelancer) > 3  && (percentageDelayByFreelancer(freelancer) > percentageDelayTotal())) {
                DelayHigherThanThreeAutomaticWarning delayHigherThanThreeAutomaticWarning = new DelayHigherThanThreeAutomaticWarning(freelancer);
                delayHigherThanThreeAutomaticWarning.run();
            }
        }
    }

}