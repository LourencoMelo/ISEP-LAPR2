package lapr2.isep.pot.controller;

import lapr2.isep.pot.UI.console.utils.CreatePaymentTransactionUI;
import lapr2.isep.pot.model.*;
import lapr2.isep.pot.model.List.PaymentTransactionList;
import lapr2.isep.pot.model.List.TaskList;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

public class CreatePaymentTransactionController {

    private final ApplicationPOT applicationPOT;

    private PaymentTransaction paymentTransaction;

    private Platform platform;

    private CreatePaymentTransactionUI createPaymentTransactionUI;

    public CreatePaymentTransactionController() throws FileNotFoundException {
        this.applicationPOT = ApplicationPOT.getInstance();
        platform = applicationPOT.getPlatform();
    }

    private final PaymentTransactionList paymentTransactionList = new PaymentTransactionList();

    public PaymentTransaction newPaymentTransaction(String transId, Date endDate, Integer delay, String descQualityOfWork, Freelancer freelancer, Task task){
        this.paymentTransaction = paymentTransactionList.newPaymentTransaction(transId, endDate, delay, descQualityOfWork, freelancer, task);
        return this.paymentTransaction;
    }

    public boolean registPaymentTransaction(){
         if(paymentTransactionList.validationPaymentTransaction(this.paymentTransaction)){
            return paymentTransactionList.addPaymentTransaction(paymentTransaction);
        }
        return false;
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
        return platform.getRegistOrganization().getPaymentTransactionList(platform.getRegistOrganization().getOrganizationByUserEmail(platform.getCurrentUserEmail())).getTransactionList();
    }

    public PaymentTransactionList getPaymentTransactionList(){
        return paymentTransactionList;
    }




    public double getTaskCost(Freelancer freelancer, Task task) {
        return paymentTransaction.calculateTaskCost(freelancer, task);
    }

    public Freelancer getSelectedFreelancer() {
        return createPaymentTransactionUI.getChosenFreelancer();
    }

    public Task getSelectedTask() {
        return createPaymentTransactionUI.getChosenTask();
    }

//    public List<Task> getListOfTask(){
//        return platform.getRegistOrganization().getOrganizationByUserEmail(platform.getCurrentUserEmail()).getTaskList();
//    }
}
