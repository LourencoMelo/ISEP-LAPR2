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
    }

    public String getAppName() {
        return MainApp.APP_TITLE;
    }

    public List<User> getListUsers() {
        return User.getListUsers();
    }

    public void readCsvFile(File file){
        transactionsRegist.readCsvFile(file);
    }

    public void readTxtFile(File file){
        transactionsRegist.readTxtFile(file);
    }
}
