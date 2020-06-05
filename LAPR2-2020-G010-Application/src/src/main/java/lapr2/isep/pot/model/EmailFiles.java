package lapr2.isep.pot.model;

import lapr2.isep.authorization.model.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmailFiles {

    /**
     * Writes in a file the email sent to the user.
     * @param list of users
     */
    public static void writeToAFile(List<User> list) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("files\\Emails.txt"));
            writer.write("-----------------------------------------------------------------\n");
            writer.write("Email sents with user informations:");
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

}
