package lapr2.isep.pot.model.List;

import lapr2.isep.pot.model.Freelancer;
import lapr2.isep.pot.model.PaymentTransaction;
import lapr2.isep.pot.model.Task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PaymentTransactionList implements Serializable {
    /**
     * Transaction list
     */
    private final List<PaymentTransaction> transactionList = new ArrayList<>();

    /**
     * Returns transaction list.
     *
     * @return transaction list
     */
    public List<PaymentTransaction> getTransactionList() {
        return transactionList;
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
    public PaymentTransaction newPaymentTransaction(String transId, String endDate, Integer delay, String descQualityOfWork, Freelancer freelancer, Task task) {
        return new PaymentTransaction(transId, endDate, delay, descQualityOfWork, freelancer, task);
    }

    /**
     * @param paymentTransaction Payment Transaction
     * @return  the boolean result of the validation
     */

    public boolean validationPaymentTransaction(PaymentTransaction paymentTransaction) {
        return !transactionList.contains(paymentTransaction);
    }

    /**
     *
     * @param paymentTransaction Payment Transaction
     * @return new transaction
     */

    public boolean addPaymentTransaction(PaymentTransaction paymentTransaction) {
        return transactionList.add(paymentTransaction);
    }


}