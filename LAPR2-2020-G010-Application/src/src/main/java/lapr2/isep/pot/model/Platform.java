package lapr2.isep.pot.model;

import lapr2.isep.authorization.model.User;
import lapr2.isep.pot.model.List.PaymentTransactionList;
import lapr2.isep.pot.model.List.TaskList;

import java.io.*;
import java.util.ArrayList;
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
        //this.transactionsRegist = new TransactionsRegist();
    }

    /*private void readInfo() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File("dados.bin")));
            this.registOrganization = (RegistOrganization) in.readObject();
            System.out.println("Abriu para ler");
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("ERRO: nao abriu o ficheiro para leitura");
        }
    }

    public void saveInfo(){
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File("dados.bin")));
            out.writeObject(this.registOrganization);
            out.close();
        } catch (IOException e) {
            System.out.println("ERRO: nao abriu o ficheiro para escrita");
        }

    }

     */

    public ExternAlgorithmPasswordGenerator getAlgorithmPasswordGenerator() {
        return null;
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

    /*public List<Task> getListOfTasks() {
        return registOrganization.getListOfTasks();
    }

     */

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
        for (Freelancer freelancer : getFreelancersByOrganization()) {
            summation += freelancer.getDelay();
            numberOfTasksExecutedByFreelancer += freelancer.getNumberOfTasks();
        }
        double mean = summation / numberOfTasksExecutedByFreelancer;
        return mean;
    }

    public double meanByFreelancer(Freelancer freelancer) {
        double summation = getOrganizationCurrentUser().addSumOfDelays(freelancer);
        int numberOfTasksExecutedByFreelancer = getOrganizationCurrentUser().getPaymentTransactionList().getNumberOfTasks(freelancer);
        double mean = summation / numberOfTasksExecutedByFreelancer;
        freelancer.setMean(mean);
        return mean;

    }

    /*public double standardDeviationByOrganization() {

    }

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

}