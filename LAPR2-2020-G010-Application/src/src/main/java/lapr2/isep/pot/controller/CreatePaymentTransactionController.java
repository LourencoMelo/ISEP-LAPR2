package lapr2.isep.pot.controller;

import lapr2.isep.pot.UI.console.utils.CreatePaymentTransactionUI;
import lapr2.isep.pot.model.*;
import lapr2.isep.pot.model.List.PaymentTransactionList;

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

    public PaymentTransaction newPaymentTransaction(String transId, Date endDate, double delay, String descQualityOfWork, Freelancer freelancer, Task task){
        this.paymentTransaction = platform.getRegistOrganization().getOrganizationByUserEmail(platform.getCurrentUserEmail()).getPaymentTransactionList().newPaymentTransaction(transId, endDate, delay, descQualityOfWork, freelancer, task);
        return this.paymentTransaction;
    }

    public boolean registPaymentTransaction(){
         if(platform.getRegistOrganization().getOrganizationByUserEmail(platform.getCurrentUserEmail()).getPaymentTransactionList().validationNotPaidPaymentTransaction(this.paymentTransaction)){
            return platform.getRegistOrganization().getOrganizationByUserEmail(platform.getCurrentUserEmail()).getPaymentTransactionList().addNotPaidPaymentTransaction(paymentTransaction);
        }
        return false;
    }


    /**
     * Returns if the transaction already exists or not
     * @param paymentTransaction transaction
     * @return false if the transaction exists
     */
    public boolean getValidationPaymentTransaction(PaymentTransaction paymentTransaction){
        return platform.getRegistOrganization().getOrganizationByUserEmail(platform.getCurrentUserEmail()).getPaymentTransactionList().validationNotPaidPaymentTransaction(paymentTransaction);
    }

    public List<PaymentTransaction> getNotPaidTransactionsList(){
        return platform.getRegistOrganization().getPaymentTransactionList(platform.getRegistOrganization().getOrganizationByUserEmail(platform.getCurrentUserEmail())).getNotPaidTransactionList();
    }

    public List<PaymentTransaction> getPaidTransactionsList() {
        return platform.getRegistOrganization().getPaymentTransactionList(platform.getRegistOrganization().getOrganizationByUserEmail(platform.getCurrentUserEmail())).getPaidTransactionList();
    }

    public PaymentTransactionList getPaymentTransactionList(){
        return platform.getRegistOrganization().getOrganizationByUserEmail(platform.getCurrentUserEmail()).getPaymentTransactionList();
    }

    public List<PaymentTransaction> getTotalPaymentTransactionList() {
        return platform.getRegistOrganization().getPaymentTransactionList(platform.getRegistOrganization().getOrganizationByUserEmail(platform.getCurrentUserEmail())).getListTotalPaymentsTransactions();
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
