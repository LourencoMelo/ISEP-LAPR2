package lapr2.isep.pot.model;

import lapr2.isep.pot.model.List.PaymentTransactionList;
import lapr2.isep.pot.model.List.TaskList;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Organization implements Serializable {

    /**
     * Organization's name
     */
    private String name;

    /**
     * Organization's NIF
     */
    private String NIF;

    /**
     * Organization's task list
     */
    private TaskList taskList = new TaskList();

    /**
     * Organization's Collaborator
     */
    private Collaborator collaborator;

    /**
     * Organization's Manager
     */
    private Manager manager;

    private double mean;

    private double standardDeviation;

    private PaymentTransactionList paymentTransactionList = new PaymentTransactionList();

    /**
     * Initialize the Organization's information with the received data
     *
     * @param name Organization's name
     * @param NIF  Organizaton's NIF
     */
    public Organization(String name, String NIF, Collaborator collaborator, Manager manager) {
        if (name == null || NIF == null || collaborator == null || manager == null || name.isEmpty() || NIF.isEmpty()) {
            throw new IllegalArgumentException("Arguments cant be null or empty.");
        }
        this.name = name;
        this.NIF = NIF;
        this.manager = manager;
        this.collaborator = collaborator;
    }

    /**
     * Initialize the Organization's information with the received data
     *
     * @param name Organization's name
     * @param NIF  Organizaton's NIF
     */
    public Organization(String name, String NIF) {
        if (name == null || NIF == null || name.isEmpty() || NIF.isEmpty()) {
            throw new IllegalArgumentException("Arguments cant be null or empty.");
        }
        this.name = name;
        this.NIF = NIF;
    }

    /**
     * New collaborator
     *
     * @param name  Collaborator's name
     * @param email Collaborator's email
     * @return new Collaborator
     */
    public static Collaborator newCollaborator(String name, String email) throws IOException {
        return new Collaborator(name, email);
    }

    /**
     * New Manager
     *
     * @param name  Manager's name
     * @param email Manager's email
     * @return new Manager
     */
    public static Manager newManager(String name, String email) throws IOException {
        return new Manager(name, email);
    }

    /**
     * Returns the Organization Collaborator
     *
     * @return Collaborator
     */
    public Collaborator getCollaborator() {
        return collaborator;
    }

    /**
     * Returns the Organization Manager
     *
     * @return Manager
     */
    public Manager getManager() {
        return manager;
    }

    /**
     * Returns Task's list.
     *
     * @return task list
     */
    public List<Task> getTaskList() {
        return taskList.getTaskList();
    }

    /**
     * Returns the organization's list of tasks
     * @return list of tasks
     */
    public TaskList getListTask() {
        return taskList;
    }

    /**
     * Returns organization's mean
     * @return mean
     */
    public double getMean() {
        return mean;
    }

    /**
     * Sets organization's mean
     * @param mean  mean
     */
    public void setMean(double mean) {
        this.mean = mean;
    }

    /**
     * Returns organization's standard deviation
     * @return standard deviation
     */
    public double getStandardDeviation() {
        return standardDeviation;
    }

    /**
     * Sets organization's standard deviation
     * @param standardDeviation standard deviation
     */
    public void setStandardDeviation(double standardDeviation) {
        this.standardDeviation = standardDeviation;
    }

    /**
     * Verifies if the the organization has that collaborator
     *
     * @param email Collaborator's email
     * @return true if it founds the collaborator
     */
    public boolean hasCollaboratorWithEmail(String email) {
        boolean found = false;
        Collaborator colab = this.collaborator;
        if (colab.hasEmail(email)) {
            found = true;
        }
        return found;
    }

    /**
     * Verifies if the the organization has that collaborator
     *
     * @param email Manager's email
     * @return true if it founds the manager
     */
    public boolean hasManagerWithEmail(String email) {
        boolean found = false;
        Manager manager = this.manager;
        if (manager.hasEmail(email)) {
            found = true;
        }
        return found;
    }


        /**
         * Compares Organizations
         *
         * @param o other organization
         * @return boolean dependent organtions comparation
         */
    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o)
            return true;
        // null check
        if (o == null)
            return false;
        // type check and cast
        if (getClass() != o.getClass())
            return false;
        // field comparison
        Organization obj = (Organization) o;
        return (Objects.equals(NIF, obj.NIF));
    }

    /**
     * Returns textual description of organization
     * @return description
     */
    @Override
    public String toString() {
        return String.format("Organization: " +
                "\n\t Name: %s" +
                "\n\t NIF: %s" +
                "\n\t Manager's name: %s" +
                "\n\t Manager's email: %s" +
                "\n\t Collaborator's name: %s" +
                "\n\t Collaborator's email: %s" +
                "\n\t Tasks list: %s", name, NIF, manager.getName(), manager.getEmail(), collaborator.getName(), collaborator.getEmail(), taskList.getTaskList());
    }

    /**
     * Returns organization's payments transactions list
     * @return
     */
    public PaymentTransactionList getPaymentTransactionList() {
        return paymentTransactionList;
    }

    /**
     * Returns the summation of delays for a specific freelancer
     * @param freelancer selected freelancer
     * @return summation of delays
     */
    public double addSumOfDelays(Freelancer freelancer) {
        double summation = 0;
        for(PaymentTransaction paymentTransaction : paymentTransactionList.getListTotalPaymentsTransactions()) {
            if(paymentTransaction.getFreelancer().equals(freelancer)) {
                summation += paymentTransaction.getDelay();
            }
        }
        return summation;
    }
}
