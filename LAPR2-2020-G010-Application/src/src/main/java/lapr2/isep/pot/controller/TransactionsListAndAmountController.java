package lapr2.isep.pot.controller;

import lapr2.isep.pot.model.List.PaymentTransactionList;
import lapr2.isep.pot.model.PaymentTransaction;

import java.util.List;

public class TransactionsListAndAmountController {

    private ApplicationController applicationController = ApplicationController.getApplicationController();

    private PaymentTransactionList paymentTransactionList = new PaymentTransactionList();

    private CreatePaymentTransactionController createPaymentTransactionController = applicationController.getCreatePaymentTransactionController();

    public List<PaymentTransaction> getTransactionsList(){
        return createPaymentTransactionController.getPaymentTransactionList().getTransactionList();
    }



}
