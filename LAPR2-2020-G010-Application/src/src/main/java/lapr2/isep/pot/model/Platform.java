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


    /**
     * Freelancer's selected freelancer
     */
    private Freelancer selectedFreelancer;

    /**
     * Task's selected task
     */
    private Task selectedTask;

    /**
     * TransactionRegist's instance
     */
    private TransactionsRegist transactionsRegist;

    /**
     * currentUserEmail's instance
     */
    private String currentUserEmail;

    /**
     * Regist Freelancer instance
     */

    private RegistFreelancer registFreelancer;

    /**
     * Regist Organization instance
     */
    private RegistOrganization registOrganization;

    /**
     * TaskList's instance
     */
    private TaskList taskList;

    /**
     * Constructor that initializes registFreelancer, registOrganization and taskList
     * @throws FileNotFoundException if file not found
     */
    public Platform() throws FileNotFoundException {
        this.registFreelancer = new RegistFreelancer();
        this.registOrganization = new RegistOrganization();
        this.taskList = new TaskList();
        //readSerialization();
        //this.transactionsRegist = new TransactionsRegist();
    }

    /**
     * Reads the serialized list of freelancer's but with some errors
     */
    private void readSerialization() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("SerializableData"));
            this.registFreelancer = (RegistFreelancer) in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            Alert alert = AlertUI.createAlert(Alert.AlertType.WARNING, "Improve T4J", "Error", "The binary file wasn't read.");
            alert.show();
        }
    }

    /**
     * Serializes the registFreelancer
     */
    public void serialization(){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File("SerializableData"));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(registFreelancer);
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

    /**
     * Returns boolean value if the organization is added or not
     * @param organization organization received
     * @return true if the organization is added
     */
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

    /**
     * Returns boolean value depending if the current user is collaborator or not
     * @param user received user
     * @return true if the user is collaborator
     */
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

    /**
     * Returns boolean value depending if the current user is manager or not
     * @param user received user
     * @return true if the user is manager
     */
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

    /**
     * Returns boolean value if the user exist
     * @param email         user's email
     * @param password      user's password
     * @return  true if the user exists
     */
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

    /**
     * Creates a new User object
     * @param name          user's name
     * @param email         user's email
     * @param password      user's password
     * @return  new user
     * @throws FileNotFoundException if file not found
     */
    public User createUser(String name, String email, String password) throws FileNotFoundException {
        return new User(name, email, password);
    }

    /**
     * Returns boolean value depending if the user received exists on the system
     * @param user user received
     * @return true if the list of users contains the received user
     */
    public boolean userExist(User user) {
        return User.getListUsers().contains(user);
    }

    /**
     * Returns the organization from a specific collaborator
     * @param collaborator received collaborator
     * @return organization
     */
    public Organization getOrganizationByCollaborator(Collaborator collaborator) {
        return registOrganization.getOrganizationByCollaborator(collaborator);
    }

    /**
     * Returns list of collaborators of all organizations
     * @return list of collaborators
     */
    public List<Collaborator> getListCollaboratorsAllOrganizations() {
        return registOrganization.getListCollaboratorsAllOrganizations();
    }

    /**
     * Returns list of managers of all organizations
     * @return list of managers
     */
    public List<Manager> getListManagersAllOrganizations() {
        return registOrganization.getListManagersAllOrganizations();
    }

    /**
     * Returns list of users
     * @return list of users
     */
    public List<User> getListUsers() {
        return User.getListUsers();
    }


    /**
     * Returns task list of a specific organization
     * @param organization received organization
     * @return list of tasks
     */
    public List<Task> getListOfTasksFromOrganization(Organization organization) {
        return organization.getTaskList();
    }

    /**
     * Returns collaborator from organization
     * @return collaborator
     */
    public Collaborator getCollaborator() {
        return registOrganization.getCollaborator();
    }

    /**
     * Returns task list instance
     * @return task list instance
     */
    public TaskList getTasksList() {
        return taskList;
    }

    /**
     * Returns current user email
     * @return user email
     */
    public String getCurrentUserEmail() {
        return currentUserEmail;
    }

    /**
     * Returns current user organization
     * @return user organization
     */
    public Organization getOrganizationCurrentUser() {
        return registOrganization.getOrganizationByUserEmail(getCurrentUserEmail());
    }

    /**
     * Returns the payment list of the current user's organization
     * @return payment list
     */
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
     * Return the mean of delays of one organization
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


    /**
     * Returns the mean of delays from a specific freelancer that has done a task from the current user organization
     * @param freelancer  selected freelancer
     * @return mean
     */
    public double meanByFreelancer(Freelancer freelancer) {
        double summation = getOrganizationCurrentUser().addSumOfDelays(freelancer);
        int numberOfTasksExecutedByFreelancer = getOrganizationCurrentUser().getPaymentTransactionList().getNumberOfTasks(freelancer);
        double mean = summation / numberOfTasksExecutedByFreelancer;
        freelancer.setMean(mean);
        return mean;

    }

    /**
     * Returns the mean of all platform freelancer's delays
     * @return mean
     */
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


    /**
     * Returns the standard deviation of all platform freelancer's delays
     * @return standard deviation
     */
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

    /**
     * Returns the mean of delays on every organization's tasks from a specific freelancer
     * @param freelancer received freelancer
     * @return mean
     */
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


    /**
     * Returns the standard deviation of delays on every organization's tasks from a specific freelancer
     * @param freelancer received freelancer
     * @return standard deviation
     */
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

    /**
     * Returns the standard deviation of all current user organization's tasks delays
     * @return standard deviation
     */
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

    /**
     * Returns the standard deviation of tasks delays from a specific freelancer
     * @return standard deviation
     */
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

    /**
     * Returns the number of delays from all the tasks from a specific freelancer to the current collaborator's organization and to the specified interval
     *
     * @return int number of delays
     */
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

    /**
     * Returns the number of delays from all the tasks from a specific freelancer to the current collaborator's organization and to the specified interval
     *
     * @return int number of delays
     */
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

    /**
     * Returns the number of delays from all the tasks from a specific freelancer to the current collaborator's organization and to the specified interval
     *
     * @return int number of delays
     */
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

    /**
     * Returns the number of delays from all the tasks from the current collaborator's organization and to the specified interval
     *
     * @return int number of delays
     */
    public int numberDelaysFirstIntervalByOrganization() {
        int delays = 0;

        for (PaymentTransaction paymentTransaction : getOrganizationCurrentUser().getPaymentTransactionList().getListTotalPaymentsTransactions()) {
            if (paymentTransaction.getDelay() <= (getOrganizationCurrentUser().getMean() - getOrganizationCurrentUser().getStandardDeviation())) {
                delays++;
            }

        }
        return delays;
    }

    /**
     * Returns the number of delays from all the tasks from the current collaborator's organization and to the specified interval
     *
     * @return int number of delays
     */
    public int numberDelaysSecondIntervalByOrganization() {
        int delays = 0;

        for (PaymentTransaction paymentTransaction : getOrganizationCurrentUser().getPaymentTransactionList().getListTotalPaymentsTransactions()) {
            if (paymentTransaction.getDelay() > (getOrganizationCurrentUser().getMean() - getOrganizationCurrentUser().getStandardDeviation()) && (paymentTransaction.getDelay() < getOrganizationCurrentUser().getMean() + getOrganizationCurrentUser().getStandardDeviation())) {
                delays++;
            }

        }
        return delays;
    }

    /**
     * Returns the number of delays from all the tasks from the current collaborator's organization and to the specified interval
     *
     * @return int number of delays
     */
    public int numberDelaysThirdIntervalByOrganization() {
        int delays = 0;

        for (PaymentTransaction paymentTransaction : getOrganizationCurrentUser().getPaymentTransactionList().getListTotalPaymentsTransactions()) {
            if (paymentTransaction.getDelay() >= getOrganizationCurrentUser().getMean() + getOrganizationCurrentUser().getStandardDeviation()) {
                delays++;
            }

        }
        return delays;
    }

    /**
     * Returns the mean of payments from current user organization's tasks made to a specific freelancer
     * @param freelancer received freelancer
     * @return mean
     */
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

    /**
     * Returns the standard deviation of payments from current user organization's tasks made to a specific freelancer
     * @param freelancer received freelancer
     * @return standard deviation
     */
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

    /**
     * Returns the number of delays from all the platform's tasks by a specific freelancer to the specified interval
     * @param freelancer received freelancer
     * @return int number of delays
     */
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

    /**
     * Returns the number of delays from all the platform's tasks by a specific freelancer to the specified interval
     * @param freelancer received freelancer
     * @return int number of delays
     */
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

    /**
     * Returns the number of delays from all the platform's tasks by a specific freelancer to the specified interval
     * @param freelancer received freelancer
     * @return int number of delays
     */
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

    /**
     * Returns the number of delays from all the platform's tasks to the specified interval
     * @return int number of delays
     */
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

    /**
     * Returns the number of delays from all the platform's tasks to the specified interval
     * @return int number of delays
     */
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

    /**
     * Returns the number of delays from all the platform's tasks to the specified interval
     * @return int number of delays
     */
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

    /**
     * Returns the mean of all platform payments made to all freelancers
     * @return mean
     */
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

    /**
     * Returns the standard deviation of all platform payments made to all freelancers
     * @return standard deviation
     */
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

    /**
     * Returns the mean of all platform payments made to a specific freelancer
     * @param freelancer received freelancer
     * @return mean
     */
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

    /**
     * Returns the standard deviation of all platform payments made to a specific freelancer
     * @param freelancer received freelancer
     * @return standard deviation
     */
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

    /**
     * Returns the number of payments from all the platform's tasks to the specified interval
     * @return int number of payments
     */
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

    /**
     * Returns the number of payments from all the platform's tasks to the specified interval
     * @return int number of payments
     */
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

    /**
     * Returns the number of payments from all the platform's tasks to the specified interval
     * @return int number of payments
     */
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

    /**
     * Returns the number of payments from all the platform's tasks by a specific freelancer to the specified interval
     * @return int number of payments
     */
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

    /**
     * Returns the number of payments from all the platform's tasks by a specific freelancer to the specified interval
     * @return int number of payments
     */
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

    /**
     * Returns the number of payments from all the platform's tasks by a specific freelancer to the specified interval
     * @return int number of payments
     */
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

    /**
     * Sets the date to pay received to each one of the current user organization's payments transactions
     * @param dateToPay date to automatic payment
     * @throws IOException if there is an input or output exception
     */
    public void setDateToPay(Date dateToPay) throws IOException {
        for (PaymentTransaction paymentTransaction : getOrganizationCurrentUser().getPaymentTransactionList().getNotPaidTransactionList()) {
            paymentTransaction.setDateToPay(dateToPay);
//            getOrganizationCurrentUser().getPaymentTransactionList().getNotPaidTransactionList().remove(paymentTransaction);
//            getOrganizationCurrentUser().getPaymentTransactionList().getPaidTransactionList().add(paymentTransaction);
//            System.out.println(getOrganizationCurrentUser().getPaymentTransactionList().getNotPaidTransactionList().size());
//            System.out.println(getOrganizationCurrentUser().getPaymentTransactionList().getPaidTransactionList().size());
        }
    }

    /**
     * Gets the percentage of tasks with delay from a specific freelancer
     * @param freelancer freelancer received
     * @return percentage
     */
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

    /**
     * Gets the percentage of tasks with delay of all platform's tasks
     * @return percentage
     */
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

    /**
     *  Sends an email to all the freelancers that have a mean of delays higher than 3h and also a percentage of delays higher than the mean of all platform's delays percentage
     * @throws FileNotFoundException if file not found
     */
    public void sendEmailWithDelayHigherThanThree() throws FileNotFoundException {
        for (Freelancer freelancer : getRegistFreelancer().getFreelancerList()) {
            if (meanDelayAllTasksByFreelancer(freelancer) > 3  && (percentageDelayByFreelancer(freelancer) > percentageDelayTotal())) {
                DelayHigherThanThreeAutomaticWarning delayHigherThanThreeAutomaticWarning = new DelayHigherThanThreeAutomaticWarning(freelancer);
                delayHigherThanThreeAutomaticWarning.run();
            }
        }
    }

    /**
     * Creates file with the regist of payment transactions already paid
     */
    public void createFileWithPaidTransactions() {
        for (Organization organization : registOrganization.getListOrganizations()) {
            Files.writeToAFileAllPaidPaymentTransactions(organization.getPaymentTransactionList().getListTotalPaymentsTransactions());
        }
    }
}