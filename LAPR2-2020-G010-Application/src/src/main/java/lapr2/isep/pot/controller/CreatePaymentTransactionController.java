package lapr2.isep.pot.controller;

import lapr2.isep.pot.UI.console.utils.CreatePaymentTransactionUI;
import lapr2.isep.pot.model.*;
import lapr2.isep.pot.model.List.PaymentTransactionList;
import lapr2.isep.pot.model.List.TaskList;

import java.util.Date;
import java.util.List;

public class CreatePaymentTransactionController {


    private final CreatePaymentTransactionUI createPaymentTransactionUI = new CreatePaymentTransactionUI();

    private final ApplicationController applicationController = ApplicationController.getApplicationController();

    private final RegisterFreelancerController registerFreelancerController = applicationController.getRegistFreelancerController();

    private final TaskCreationController taskCreationController = applicationController.getTaskCreationController();

    private PaymentTransaction paymentTransaction;

    private Platform platform = ApplicationPOT.getInstance().getPlatform();

    private final PaymentTransactionList paymentTransactionList = new PaymentTransactionList();

    public PaymentTransaction newPaymentTransaction(String transId, Date endDate, Integer delay, String descQualityOfWork, Freelancer freelancer, Task task){
        this.paymentTransaction = paymentTransactionList.newPaymentTransaction(transId, endDate, delay, descQualityOfWork, freelancer, task);
        return this.paymentTransaction;
    }

    /**
     * Regist Freelancer
     */

    public CreatePaymentTransactionController() {
        platform = ApplicationPOT.getInstance().getPlatform();
    }

    public boolean registPaymentTransaction(){
         if(paymentTransactionList.validationPaymentTransaction(this.paymentTransaction)){
            return paymentTransactionList.addPaymentTransaction(paymentTransaction);
        }
        return false;
    }

    /**
     * Returns the list of freelancers
     *
     * @return
     */
    public List<Freelancer> getListFreelancers() {
        return registerFreelancerController.getRegistFreelancer().getFreelancerList();
    }

    /**
     *
     */

    public Freelancer getFreelancerByID(String id) {
        return registerFreelancerController.getRegistFreelancer().getFreelancerByID(id);
    }

    public Task getTaskByID(String id) {
        return taskCreationController.getListTask().getTaskByID(id);
    }

    /**
     * Returns organizations task's list
     *
     * @return Task's List
     */
    public List<Task> getTaskList() {
        return taskCreationController.getTaskList();
    }

    /**
     * Returns if the transaction already exists or not
     * @param paymentTransaction transaction
     * @return false if the transaction exists
     */
    public boolean getValidationPaymentTransaction(PaymentTransaction paymentTransaction){
        return paymentTransactionList.validationPaymentTransaction(paymentTransaction);
    }

    public List<PaymentTransaction> getTransactionsList(){
        return getPaymentTransactionList().getTransactionList();
    }

    public PaymentTransactionList getPaymentTransactionList(){
        return paymentTransactionList;
    }

    public Freelancer getChoosenFreelancer() {
        return createPaymentTransactionUI.getSelectedFreelancer();
    }

    public Task getChoosenTask() {
        return createPaymentTransactionUI.getSelectedTask();
    }


    public double getTaskCost(Freelancer freelancer, Task task) {
        return paymentTransaction.calculateTaskCost(freelancer, task);
    }


    public Platform getPlatform() {
        return platform;
    }
}
