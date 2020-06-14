package lapr2.isep.pot.model.List;

import lapr2.isep.pot.model.Freelancer;
import lapr2.isep.pot.model.PaymentTransaction;
import lapr2.isep.pot.model.Task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class the contains the imformation of the paid and not paid transactions lists.
 * @author José Soares, João Beires, José Maia, Lourenço Melo, Gonçalo Ferreira.
 */
public class PaymentTransactionList implements Serializable {

    /**
     * Not paid Transaction list
     */
    private final List<PaymentTransaction> notPaidTransactionList = new ArrayList<>();

    /**
     * Paid transaction list
     */
    private final List<PaymentTransaction> paidTransactionList = new ArrayList<>();

    /**
     * Returns not paid transaction list.
     *
     * @return not paid transaction list
     */
    public List<PaymentTransaction> getNotPaidTransactionList() {
        return notPaidTransactionList;
    }

    /**
     * Returns paid transaction list.
     *
     * @return paid transaction list
     */
    public List<PaymentTransaction> getPaidTransactionList() {
        return paidTransactionList;
    }

    /**
     * Creates a new Payment Transaction
     *
     * @param transId id
     * @param endDate end date
     * @param delay delay
     * @param descQualityOfWork description of quality of work
     * @param freelancer freelancer
     * @param task task
     * @return new transaction
     */
    public PaymentTransaction newPaymentTransaction(String transId, Date endDate, double delay, String descQualityOfWork, Freelancer freelancer, Task task) {
        return new PaymentTransaction(transId, endDate, delay, descQualityOfWork, freelancer, task);
    }

    /**
     * Validates the not paid transaction
     * @param paymentTransaction Payment Transaction
     * @return  the boolean result of the validation
     */

    public boolean validationNotPaidPaymentTransaction(PaymentTransaction paymentTransaction) {
        return !paidTransactionList.contains(paymentTransaction);
    }

    /**
     * Add the not paid transaction to the not paid list
     * @param paymentTransaction Payment Transaction
     * @return new transaction not paid
     */

    public boolean addNotPaidPaymentTransaction(PaymentTransaction paymentTransaction) {
        return notPaidTransactionList.add(paymentTransaction);
    }

    /**
     * Add the paid transaction to the paid list
     * Returns not paid transaction list.
     *
     * @return not paid transaction list
     */
    public boolean addPaidPaymentTransaction(PaymentTransaction paymentTransaction) {
        return paidTransactionList.add(paymentTransaction);
    }

    /**
     * returns the number of transactions added
     * @param paymentTransactionList list of payment transactions
     * @return number of added payment transactions
     */
    public int addListTransactions(PaymentTransactionList paymentTransactionList) {
        int totalTransactionsAdded = 0;
        for(PaymentTransaction paymentTransaction : paymentTransactionList.notPaidTransactionList) {
            boolean wasAdded = addNotPaidPaymentTransaction(paymentTransaction);
            if (wasAdded) {
                totalTransactionsAdded++;
            }
        }
        return totalTransactionsAdded;
     }

    /**
     * Returns the total list of payment transactions (paid and not paid transactions)
     * @return a copy of a list that contains all the transactions (paid and not paid)
     */
    public List<PaymentTransaction> getListTotalPaymentsTransactions() {
        List<PaymentTransaction> copyList = new ArrayList<>();
        for (PaymentTransaction paymentTransaction : paidTransactionList) {
            copyList.add(paymentTransaction);
        }
        for(PaymentTransaction paymentTransaction : notPaidTransactionList) {
            copyList.add(paymentTransaction);
        }
        return copyList;
    }

    /**
     * Returns the number of tasks that a specific freelancer does
     * @param freelancer that we want to know the number of tasks
     * @return the number of tasks of a freelancer
     */
    public int getNumberOfTasks(Freelancer freelancer) {
        int numberOfTasks = 0;
        for(PaymentTransaction paymentTransaction : getListTotalPaymentsTransactions()) {
            if(paymentTransaction.getFreelancer().equals(freelancer)) {
                numberOfTasks++;
            }
        }
        return numberOfTasks;
    }

    /**
     * Returns the total of delays of all the payment transactions
     * @return all delays
     */
    public double getTotalDelays(){
        double delays = 0;
        for (PaymentTransaction paymentTransaction : getListTotalPaymentsTransactions()){
            delays += paymentTransaction.getDelay();
        }
        return delays;
    }
}