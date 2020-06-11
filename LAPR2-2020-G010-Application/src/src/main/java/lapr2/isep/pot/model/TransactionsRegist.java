package lapr2.isep.pot.model;

import lapr2.isep.pot.controller.ApplicationPOT;
import lapr2.isep.pot.model.List.PaymentTransactionList;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Class that registers the transactions receving a file of the type(.csv or .txt)
 */
public class TransactionsRegist implements Serializable {

    private Platform platform;

    public static final String FILE_NAME_CSV = "transactionsOrgSoftwareHome.csv";

    public TransactionsRegist() throws FileNotFoundException {
        this.platform = ApplicationPOT.getInstance().getPlatform();
    }

    public PaymentTransactionList readCsv() {
        return readCsv(FILE_NAME_CSV);
    }

    public PaymentTransactionList readCsv(String fileName) {
        return readCsv(new File(fileName));
    }

    public PaymentTransactionList readCsv(File file) {
        PaymentTransactionList paymentTransactionList;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(file));
            try {
                paymentTransactionList = (PaymentTransactionList) in.readObject();
            } finally {
                in.close();
            }
            return paymentTransactionList;
        } catch (IOException | ClassNotFoundException ex) {
            return new PaymentTransactionList();
        }
    }

    public boolean saveCsv(PaymentTransactionList paymentTransactionList) {
        return saveCsv(FILE_NAME_CSV, paymentTransactionList);
    }

    public boolean saveCsv(String fileName, PaymentTransactionList paymentTransactionList) {
        return saveCsv(new File(fileName), paymentTransactionList);
    }

    public boolean saveCsv(File file, PaymentTransactionList paymentTransactionList) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(file));
            try {
                out.writeObject(paymentTransactionList);
            } finally {
                out.close();
            }
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public PaymentTransactionList readTransactionList() throws FileNotFoundException{
        return readTransactionList(FILE_NAME_CSV);
    }

    private PaymentTransactionList readTransactionList(String fileTransactionList) throws FileNotFoundException {
        return readTransactionList(new File(fileTransactionList));
    }

    private PaymentTransactionList readTransactionList(File fileTransactionList) throws FileNotFoundException {
        try {
            Scanner in = new Scanner(fileTransactionList);
            PaymentTransactionList copyPaymentTransactionList = platform.getRegistOrganization().;
            in.nextLine();
            while (in.hasNext()){
                String[] line = in.nextLine().trim().split("\t");
                Task task = new Task(line[1].trim(), line[2].trim(), Double.parseDouble(line[3].trim()), Double.parseDouble(line[4].trim()), line[5].trim());
                Freelancer freelancer = new Freelancer(line[9].trim(), line[10].trim(), line[11].trim(), line[12].trim(), line[13].trim(), line[14].trim(), line[15].trim(), line[16].trim());
                PaymentTransaction paymentTransaction = new PaymentTransaction(line[0].trim(), Formatter(line[6].trim()), Integer.parseInt(line[7].trim()), line[8].trim(), freelancer, task);
                if(!this.paymentTransactionList.contains(paymentTransaction)){
                    this.paymentTransactionList.addPaymentTransaction(paymentTransaction);
                }
            }
            return copyPaymentTransactionList;
        } catch (FileNotFoundException | ParseException fnfex){
            throw new FileNotFoundException("The File was not found!");
        }
    }

    public static Date Formatter(String date) throws ParseException {
        SimpleDateFormat formmater1 = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = formmater1.parse(date);
        return date1;
    }
}
