package lapr2.isep.pot.model;

import lapr2.isep.pot.controller.ApplicationPOT;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.TimerTask;

public class TaskPaymentAutomatically extends TimerTask implements CoinCurrencieConvert, Serializable {

    /**
     * Starts the date that will be received by parameter
     */
    private final Date date;

    private final Freelancer freelancer;

    private final double valueToTheReceipt;

    private final File file = new File("files\\PaymentReceipt.txt");

    /**
     * Plataform's instance
     */
    private final Platform platform;

    /**
     * ApplicationPot's instance
     */
    private final ApplicationPOT applicationPOT;

    /**
     * Constructor with the date parameter that will be used in the run method
     *
     * @param date date
     */
    public TaskPaymentAutomatically(Date date, Freelancer freelancer, double valueToTheReceipt) throws IOException {
        this.date = date;
        this.freelancer = freelancer;
        this.valueToTheReceipt = valueToTheReceipt;
        this.applicationPOT = ApplicationPOT.getInstance();
        this.platform = applicationPOT.getPlatform();
    }

    /**
     * Method that allows us to run the timertask
     */
    @Override
    public void run() {
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write("To: " + freelancer.getEmail() + "\n");
            writer.write("Subject: Pay slipt - T4J" + "\n");
            writer.write(" Message: \n" +
                    "\tDear " + freelancer.getName() + ", we send you your salary " +
                    "\n\treceipt for consultation and file.\n");
            writer.write("\t-------------------------------------------------\n");
            writer.write("\t              T4J Payment Receipt:");
            writer.write("\n\t-------------------------------------------------\n");
            writer.write("\t---------- IBAN: " + freelancer.getBankAccountIBAN() + " -----------\n");
            writer.write("\t-------------------------------------------------\n");
            writer.write("\t- Name: " + freelancer.getName() + "\n");
            writer.write("\t- NIF: " + freelancer.getNIF());
            writer.write("\n\t-------------------------------------------------\n");
            writer.write("\n\t-------------------------------------------------\n");
            writer.write("\t-Payment value by the task: " + convertCoinCurrencie(freelancer.getCountry(), valueToTheReceipt) + getCoinSymbolByCountry(freelancer.getCountry()) + "\n");
            writer.write("\t-------------------------------------------------\n");
            writer.write("\n\t" + date.toString() + "\n");
            writer.write("\tBest regards," +
                    "\n\t T4J Team.\n");
            writer.write("\n\t------------------------------------------\n");
            writer.write("\n");
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts the coin currencie dependent on the country
     * @param country of the freelancer
     * @param amountToPay to convert
     * @return the amount converted
     */
    @Override
    public double convertCoinCurrencie(String country, double amountToPay) {
        if (freelancer.getCountry().equalsIgnoreCase("UNITED STATES") || freelancer.getCountry().equalsIgnoreCase("USA") || freelancer.getCountry().equalsIgnoreCase("U.S.A.") || freelancer.getCountry().equalsIgnoreCase("U.S.A")) {
            return amountToPay * CoinCurrencieConvert.UNITED_STATES;
        } else if (freelancer.getCountry().equalsIgnoreCase("ENGLAND")) {
            return amountToPay * CoinCurrencieConvert.ENGLAND;
        } else if (freelancer.getCountry().equalsIgnoreCase("BRAZIL")) {
            return amountToPay * CoinCurrencieConvert.BRAZIL;
        } else if (freelancer.getCountry().equalsIgnoreCase("JAPAN")) {
            return amountToPay * CoinCurrencieConvert.JAPAN;
        } else if (freelancer.getCountry().equalsIgnoreCase("CHINA")) {
            return amountToPay * CoinCurrencieConvert.CHINA;
            //ADD THE FUTURE COUNTRY'S COIN CURRENCIE
        } else {
            return amountToPay;
        }
    }

    /**
     * Gets the coin symbol dependent of the country of the freelancer
     * @param country of the freelancerr
     * @return coin symbol
     */
    public String getCoinSymbolByCountry(String country) {
        if (country.equalsIgnoreCase("UNITED STATES") || country.equalsIgnoreCase("USA") || country.equalsIgnoreCase("U.S.A.") || country.equalsIgnoreCase("U.S.A")) {
            return "$";
        } else if (country.equalsIgnoreCase("ENGLAND")) {
            return "£";
        } else if (country.equalsIgnoreCase("BRAZIL")) {
            return "R$";
        } else if (country.equalsIgnoreCase("JAPAN")) {
            return "円";
        } else if (country.equalsIgnoreCase("CHINA")) {
            return "元";
            //ADD THE FUTURE COUNTRY'S COIN SYMBOL
        } else {
            return "€";
        }
    }
}
