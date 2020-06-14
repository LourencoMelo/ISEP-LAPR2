package lapr2.isep.pot.controller;

import lapr2.isep.pot.UI.console.utils.CreatePaymentTransactionUI;
import lapr2.isep.pot.model.*;
import lapr2.isep.pot.model.List.PaymentTransactionList;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CreatePaymentTransactionController implements Serializable {

    /**
     * ApplicationPOT's instance
     */
    private final ApplicationPOT applicationPOT;

    /**
     * PaymentTransaction's instance
     */
    private PaymentTransaction paymentTransaction;

    /**
     * Platform's instance
     */
    private Platform platform;

    /**
     * Constructor that gets the instance from applicationPOT and also the platform
     * @throws FileNotFoundException file not found exception
     */
    public CreatePaymentTransactionController() throws FileNotFoundException {
        this.applicationPOT = ApplicationPOT.getInstance();
        platform = applicationPOT.getPlatform();
    }

    /**
     * Creates a new payment transaction
     * @param transId               transaction's id
     * @param endDate               transaction's end date
     * @param delay                 transaction's delay
     * @param descQualityOfWork     transaction's description of Quality of Work
     * @param freelancer            transaction's freelancer
     * @param task                  transaction's task
     * @return  new payment transaction
     */
    public PaymentTransaction newPaymentTransaction(String transId, Date endDate, double delay, String descQualityOfWork, Freelancer freelancer, Task task){
        this.paymentTransaction = platform.getRegistOrganization().getOrganizationByUserEmail(platform.getCurrentUserEmail()).getPaymentTransactionList().newPaymentTransaction(transId, endDate, delay, descQualityOfWork, freelancer, task);
        return this.paymentTransaction;
    }

    /**
     * Validates and adds the new payment transaction
     * @return true if the payment transaction was added
     */
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

    /**
     * Returns the list of transactions not paid
     * @return list not paid transaction
     */
    public List<PaymentTransaction> getNotPaidTransactionsList(){
        return platform.getRegistOrganization().getPaymentTransactionList(platform.getRegistOrganization().getOrganizationByUserEmail(platform.getCurrentUserEmail())).getNotPaidTransactionList();
    }

    /**
     * Returns the list of paid transactions
     * @return paid transactions list
     */
    public List<PaymentTransaction> getPaidTransactionsList() {
        return platform.getRegistOrganization().getPaymentTransactionList(platform.getRegistOrganization().getOrganizationByUserEmail(platform.getCurrentUserEmail())).getPaidTransactionList();
    }

    /**
     * Returns the instance of PaymentTransactionList class
     * @return instance
     */
    public PaymentTransactionList getPaymentTransactionList(){
        return platform.getRegistOrganization().getOrganizationByUserEmail(platform.getCurrentUserEmail()).getPaymentTransactionList();
    }

    /**
     * Returns the list of all payment transactions on the system
     * @return payment transaction list
     */
    public List<PaymentTransaction> getTotalPaymentTransactionList() {
        return platform.getRegistOrganization().getPaymentTransactionList(platform.getRegistOrganization().getOrganizationByUserEmail(platform.getCurrentUserEmail())).getListTotalPaymentsTransactions();
    }

    /**
     * Returns the cost of a specific task
     * @param freelancer selected freelancer
     * @param task  selected task
     * @return  task's cost
     */
    public double getTaskCost(Freelancer freelancer, Task task) {
        return paymentTransaction.calculateTaskCost(freelancer, task);
    }
}
