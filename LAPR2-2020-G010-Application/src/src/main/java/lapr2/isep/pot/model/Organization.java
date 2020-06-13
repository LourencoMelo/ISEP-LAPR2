package lapr2.isep.pot.model;

import lapr2.isep.pot.model.List.PaymentTransactionList;
import lapr2.isep.pot.model.List.TaskList;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
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

    public TaskList getListTask() {
        return taskList;
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

    public PaymentTransactionList getPaymentTransactionList() {
        return paymentTransactionList;
    }

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
