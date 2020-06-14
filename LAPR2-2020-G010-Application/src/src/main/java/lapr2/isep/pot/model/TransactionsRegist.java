package lapr2.isep.pot.model;

import lapr2.isep.pot.controller.ApplicationPOT;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Class that registers the transactions receving a file of the type(.csv or .txt)
 */
public class TransactionsRegist implements Serializable {

    /**
     * Platform's instance
     */
    private Platform platform;

    /**
     * Constant with the name of the file
     */
    public static final String FILE_NAME_CSV = "transactionsOrgSoftwareHome.csv";

    /**
     * Constructor that returns platform value
     * @throws FileNotFoundException if file not found
     */
    public TransactionsRegist() throws FileNotFoundException {
        this.platform = ApplicationPOT.getInstance().getPlatform();
    }

    /**
     * Reads the csv file
     * @param filePaymentsTransactionsList file received
     */
    public void readCsvFile(File filePaymentsTransactionsList) {
        Organization organization = platform.getRegistOrganization().getOrganizationByUserEmail(platform.getCurrentUserEmail());
        try {
            Scanner in = new Scanner(filePaymentsTransactionsList);
            in.nextLine();
            while (in.hasNext()) {
                String[] line = in.nextLine().trim().split(";");
                Task task = new Task(line[1].trim(), line[2].trim(), Double.parseDouble(line[3].trim()), Double.parseDouble(line[4].trim()), line[5].trim());
                Freelancer freelancer = new Freelancer(line[9].trim(), line[10].trim(), line[11].trim(), line[12].trim(), line[13].trim(), line[14].trim(), line[15].trim(), line[16].trim());
                PaymentTransaction paymentTransaction = new PaymentTransaction(line[0].trim(), FormatterCSV(line[6].trim()), Double.parseDouble(line[7].trim()), line[8].trim(), freelancer, task);
                if (!platform.getRegistOrganization().getPaymentTransactionList(organization).getNotPaidTransactionList().contains(paymentTransaction)) {
                    platform.getRegistOrganization().getPaymentTransactionList(organization).addNotPaidPaymentTransaction(paymentTransaction);
                    if (!platform.getRegistFreelancer().getFreelancerList().contains(freelancer)) {
                        platform.getRegistFreelancer().getFreelancerList().add(freelancer);
                    }
                    if (!organization.getTaskList().contains(task)) {
                        organization.getTaskList().add(task);
                    }
                }
            }
        } catch (FileNotFoundException | ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Reads the txt file
     * @param filePaymentsTransactionsList file received
     */
    public void readTxtFile(File filePaymentsTransactionsList) {
        Organization organization = platform.getRegistOrganization().getOrganizationByUserEmail(platform.getCurrentUserEmail());
        try {
            Scanner in = new Scanner(filePaymentsTransactionsList);
            in.nextLine();
            while (in.hasNext()) {
                String[] line = in.nextLine().trim().split("\t");
                Task task = new Task(line[1].trim(), line[2].trim(), Double.parseDouble(line[3].trim()), Double.parseDouble(line[4].trim()), line[5].trim());
                Freelancer freelancer = new Freelancer(line[9].trim(), line[10].trim(), line[11].trim(), line[12].trim(), line[13].trim(), line[14].trim(), line[15].trim(), line[16].trim());
                PaymentTransaction paymentTransaction = new PaymentTransaction(line[0].trim(), FormatterTXT(line[6].trim()), Double.parseDouble(line[7].trim()), line[8].trim(), freelancer, task);
                if (!platform.getRegistOrganization().getPaymentTransactionList(organization).getNotPaidTransactionList().contains(paymentTransaction)) {
                    platform.getRegistOrganization().getPaymentTransactionList(organization).addNotPaidPaymentTransaction(paymentTransaction);
                    if (!platform.getRegistFreelancer().getFreelancerList().contains(freelancer)) {
                        platform.getRegistFreelancer().getFreelancerList().add(freelancer);
                    }
                    if (!organization.getTaskList().contains(task)) {
                        organization.getTaskList().add(task);
                    }
                }
            }
        } catch (FileNotFoundException | ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Formates the date received
     * @param date date received
     * @return date formatted
     * @throws ParseException if conversion to date doesn't work
     */
    public static Date FormatterCSV(String date) throws ParseException {
        SimpleDateFormat formmater1 = new SimpleDateFormat("dd-MM-yyyy");
        return formmater1.parse(date);
    }

    /**
     * Formates the date received
     * @param date date received
     * @return date formatted
     * @throws ParseException if conversion to date doesn't work
     */
    public static Date FormatterTXT(String date) throws ParseException {
        SimpleDateFormat formmater1 = new SimpleDateFormat("dd/MM/yyyy");
        return formmater1.parse(date);
    }
}
