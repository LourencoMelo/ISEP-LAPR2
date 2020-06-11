package lapr2.isep.pot.controller;

import lapr2.isep.authorization.model.User;
import lapr2.isep.pot.UI.console.MainApp;
import lapr2.isep.pot.UI.console.utils.CreatePaymentTransactionUI;
import lapr2.isep.pot.model.List.PaymentTransactionList;
import lapr2.isep.pot.model.Platform;
import lapr2.isep.pot.model.TransactionsRegist;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.List;

public class ApplicationController implements Serializable {

    private final ApplicationPOT applicationPOT;

    private final Platform platform;

    private TransactionsRegist transactionsRegist;

    private PaymentTransactionList paymentTransactionList;

    public ApplicationController() throws FileNotFoundException {
        this.applicationPOT = ApplicationPOT.getInstance();
        this.platform = applicationPOT.getPlatform();
        this.transactionsRegist = new TransactionsRegist();
        readCsv();
    }

    private CreatePaymentTransactionUI createPaymentTransactionUI;


    public String getAppName() {
        return MainApp.APP_TITLE;
    }

    public List<User> getListUsers() {
        return User.getListUsers();
    }

    public void readCsv() {
        paymentTransactionList = transactionsRegist.readCsv();
    }

    public int readCsv(File fileImport) {
        PaymentTransactionList listTransactionsImported = transactionsRegist.readCsv(fileImport);
        return paymentTransactionList.addListTransactions(listTransactionsImported);
    }

    public boolean saveCsv() {
        return transactionsRegist.saveCsv(paymentTransactionList);
    }

    public boolean saveCsv(File fileExport) {
        return transactionsRegist.saveCsv(fileExport, paymentTransactionList);
    }

}
