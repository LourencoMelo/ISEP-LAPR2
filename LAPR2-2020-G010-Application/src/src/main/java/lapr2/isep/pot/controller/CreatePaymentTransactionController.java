package lapr2.isep.pot.controller;

import lapr2.isep.pot.UI.console.utils.CreatePaymentTransactionUI;
import lapr2.isep.pot.model.Freelancer;
import lapr2.isep.pot.model.List.PaymentTransactionList;
import lapr2.isep.pot.model.List.TaskList;
import lapr2.isep.pot.model.PaymentTransaction;
import lapr2.isep.pot.model.RegistFreelancer;
import lapr2.isep.pot.model.Task;

import java.util.Date;
import java.util.List;

public class CreatePaymentTransactionController {

    private Task task;

    private CreatePaymentTransactionUI createPaymentTransactionUI = new CreatePaymentTransactionUI();

    private ApplicationController applicationController = ApplicationController.getApplicationController();

    private RegisterFreelancerController registerFreelancerController = applicationController.getRegistFreelancerController();

    private TaskCreationController taskCreationController = applicationController.getTaskCreationController();

    private PaymentTransaction paymentTransaction;

    private PaymentTransactionList paymentTransactionList = new PaymentTransactionList();

    public PaymentTransaction newPaymentTransaction(String transId, String endDate, Integer delay, String descQualityOfWork, Freelancer freelancer, Task task){
        this.paymentTransaction = paymentTransactionList.newPaymentTransaction(transId, endDate, delay, descQualityOfWork, freelancer, task);
        return this.paymentTransaction;
    }

    /**
     * Regist Freelancer
     */

    public CreatePaymentTransactionController() {

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

}
