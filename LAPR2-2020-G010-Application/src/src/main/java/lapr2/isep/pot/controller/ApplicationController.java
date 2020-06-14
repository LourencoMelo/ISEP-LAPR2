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

/**
 * Controller of the application.
 *
 * @author José Soares, João Beires, José Maia, Lourenço Melo, Gonçalo Ferreira.
 */
public class ApplicationController implements Serializable {

    /**
     * ApplicationsPOT's instance
     */
    private final ApplicationPOT applicationPOT;

    /**
     * Platform's instance
     */
    private final Platform platform;

    /**
     * TransactionsRegist's instance
     */
    private TransactionsRegist transactionsRegist;

    /**
     * PaymentTransactionList's instance
     */
    private PaymentTransactionList paymentTransactionList;

    /**
     * Constructor that initializes applicationPot, platform and transactionsRegist instances
     *
     * @throws FileNotFoundException file not found exception
     */
    public ApplicationController() throws FileNotFoundException {
        this.applicationPOT = ApplicationPOT.getInstance();
        this.platform = applicationPOT.getPlatform();
        this.transactionsRegist = new TransactionsRegist();
    }

    /**
     * Returns application's name
     *
     * @return name
     */
    public String getAppName() {
        return MainApp.APP_TITLE;
    }

    /**
     * Returns list of Users
     *
     * @return user's list
     */
    public List<User> getListUsers() {
        return User.getListUsers();
    }

    /**
     * Reads an csv file
     *
     * @param file file imported
     */
    public void readCsvFile(File file) {
        transactionsRegist.readCsvFile(file);
    }

    /**
     * Reads an txt file
     *
     * @param file file imported
     */
    public void readTxtFile(File file) {
        transactionsRegist.readTxtFile(file);
    }

    /**
     * Saves all the platform data
     */
    public void saveInfo() {
        platform.serialization();
    }

    /**
     * Sends the email to freelancers who have delay higher than three
     *
     * @throws FileNotFoundException if doesnt find the file
     */
    public void sendEmailWithDelayHigherThanThree() throws FileNotFoundException {
        platform.sendEmailWithDelayHigherThanThree();
    }

    /**
     * Returns the list of paid payment transaction that exist on the platform
     */
    public void createFileWithPaidTransactions() {
        platform.createFileWithPaidTransactions();
    }
}
