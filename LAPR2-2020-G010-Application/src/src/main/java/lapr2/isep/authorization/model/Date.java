package lapr2.isep.authorization.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class that access the current date and time.
 * @author José Soares, João Beires, José Maia, Lourenço Melo, Gonçalo Ferreira.
 */
public class Date {

    public static String actualDate(){
        /**
         * Get current date time
         */
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dateTimeFormatter.format(localDateTime);
    }

}
