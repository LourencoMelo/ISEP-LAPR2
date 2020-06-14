package lapr2.isep.pot.model;

/**
 * Class that access the current date and time.
 * @author José Soares, João Beires, José Maia, Lourenço Melo, Gonçalo Ferreira.
 */
public interface CoinCurrencieConvert {

    /**
     * Coin currencie in united states
     */
    double UNITED_STATES = 1.13;

    /**
     * Coin currencie in england
     */
    double ENGLAND = 0.89;

    /**
     * Coin currencie in brazil
     */
    double BRAZIL = 5.57;

    /**
     * Coin currencie in japan
     */
    double JAPAN = 122.25;

    /**
     * Coin currencie in china
     */
    double CHINA = 8.03;

    //ADD FUTURE COUNTRY'S COIN CURRENCIES VALUES

    /**
     * Method that will help to convert the coin currencie
     * @param country of the freelancer the will receive the receipt
     * @param amountToPay to the freelancer in euros
     * @return the amount to pay dependent on his country
     */
    double convertCoinCurrencie(String country, double amountToPay);

}
