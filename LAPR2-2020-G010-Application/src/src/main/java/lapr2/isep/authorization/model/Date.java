package lapr2.isep.authorization.model;

import java.text.SimpleDateFormat;

/**
 * Class that access the current date and time.
 */
public class Date {

    public static void actualDate(){
        /**
         * Get current date time
         */
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        System.out.println(sdf.format(currentDate));
    }
}
