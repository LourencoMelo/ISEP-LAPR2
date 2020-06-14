package lapr2.isep.pot.model;

import lapr2.isep.authorization.model.User;

import java.io.*;
import java.util.List;

public class Files implements Serializable {

    /**
     * Writes in a file the email sent to the user.
     * @param list of users
     */
    public static void writeToAFileAboutPasswords(List<User> list) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("files\\LoginCredentials.txt"));
            writer.write("-----------------------------------------------------------------\n");
            writer.write("Email sent with user information:");
            writer.write("\n-----------------------------------------------------------------\n");
            for (User s : list) {
                writer.write(s.toString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Regists in a txt file the history of payment transactions already paid
     * @param list list to save
     */
    public static void writeToAFileAllPaidPaymentTransactions(List<PaymentTransaction> list) {
        try {
            FileWriter writer = new FileWriter(new File("files\\HistoryPaymentList.txt"), true);
            writer.write("==============================================================\n\n");
            writer.write("                History paid payment list: \n\n");
            for (PaymentTransaction paymentTransaction : list) {
                writer.write("==============================================================\n\n");
                writer.write(paymentTransaction.toString() + "\n\n");
            }
            writer.write("==============================================================");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
