package lapr2.isep.pot.model;

public interface CoinCurrencieConvert {

    double UNITED_STATES = 1.13;

    double ENGLAND = 0.89;

    double BRAZIL = 5.57;

    double JAPAN = 122.25;

    double CHINA = 8.03;

    //ADD FUTURE COUNTRY'S COIN CURRENCIES VALUES

    double convertCoinCurrencie(String country, double amountToPay);

}
