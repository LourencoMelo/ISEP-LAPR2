package lapr2.isep.pot.model.List;

import lapr2.isep.pot.model.Freelancer;
import lapr2.isep.pot.model.PaymentTransaction;
import lapr2.isep.pot.model.Task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PaymentTransactionList implements Serializable {

    private PaymentTransactionList paymentTransactionList;

    /**
     * Transaction list
     */
    private final List<PaymentTransaction> notPaidTransactionList = new ArrayList<>();

    private final List<PaymentTransaction> paidTransactionList = new ArrayList<>();

    /**
     * Returns transaction list.
     *
     * @return transaction list
     */
    public List<PaymentTransaction> getNotPaidTransactionList() {
        return notPaidTransactionList;
    }

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
     * @param paymentTransaction Payment Transaction
     * @return  the boolean result of the validation
     */

    public boolean validationNotPaidPaymentTransaction(PaymentTransaction paymentTransaction) {
        return !paidTransactionList.contains(paymentTransaction);
        //        return !transactionList.contains(paymentTransaction);
    }

    /**
     *
     * @param paymentTransaction Payment Transaction
     * @return new transaction
     */

    public boolean addNotPaidPaymentTransaction(PaymentTransaction paymentTransaction) {
        return notPaidTransactionList.add(paymentTransaction);
    }

    public boolean addPaidPaymentTransaction(PaymentTransaction paymentTransaction) {
        return paidTransactionList.add(paymentTransaction);
    }

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

    public int getNumberOfTasks(Freelancer freelancer) {
        int numberOfTasks = 0;
        for(PaymentTransaction paymentTransaction : getListTotalPaymentsTransactions()) {
            if(paymentTransaction.getFreelancer().equals(freelancer)) {
                numberOfTasks++;
            }
        }
        return numberOfTasks;
    }

    public double getTotalDelays(){
        double delays = 0;
        for (PaymentTransaction paymentTransaction : getListTotalPaymentsTransactions()){
            delays += paymentTransaction.getDelay();
        }
        return delays;
    }
}