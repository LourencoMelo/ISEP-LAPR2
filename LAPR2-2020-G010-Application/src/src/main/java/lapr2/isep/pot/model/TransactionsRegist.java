package lapr2.isep.pot.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class that registers the transactions receving a file of the type(.csv or .txt)
 */
public class TransactionsRegist implements Serializable {
    /**
     * Size of the Parameters of the file.
     */
    private final int SIZE_PARAMETERS = 17;
    /**
     * Name of the Transaciton´s Regist File.
     */
    private static final String FILE_TRANSACTIONS_REGIST = "TransactionsRegist.txt";
    /**
     * Transactions ID.
     */
    private String transID;
    /**
     * Task ID of the task done by the freelancer.
     */
    private String taskID;
    /**
     * Task description of the task done by the freelancer.
     */
    private String taskDescription;
    /**
     * Task assign duration.
     */
    private String taskAssignDuration;
    /**
     * Task cost per hour.
     */
    private String taskCostPerHour;
    /**
     * Task category.
     */
    private String taskCategory;
    /**
     * Execution finish date of the task.
     */
    private String executionFinishDate;
    /**
     * Execution delay of the task.
     */
    private String executionDelay;
    /**
     * Brief description of the execution of the task.
     */
    private String executionBriefDescription;
    /**
     * Freelancer´s ID.
     */
    private String freelancerId;
    /**
     * Freelancer´s name.
     */
    private String freelancerName;
    /**
     * Freelancer´s expertise.
     */
    private String freelancerExpertise;
    /**
     * Freelancer´s NIF.
     */
    private String freelancerNIF;
    /**
     * Freelancer´s BankAccount.
     */
    private String freelancerBankAccount;
    /**
     * Freelancer´s adress.
     */
    private String freelancerAdress;
    /**
     * Freelancer´s country.
     */
    private String freelancerCountry;

    /**
     * ArrayList of the Transaction Regist.
     */
    private ArrayList<TransactionsRegist> listaGrausDeProficiencia = new ArrayList<TransactionsRegist>();

    /**
     * Returns the name of the file.
     *
     * @param nameFile name of the file
     */
    public void getlistTransactionFile(String nameFile) {
        return;
    }

    /**
     * Reads the file and registers every information on a Arraylist.
     *
     * @param nameFile name of the file
     */
    public void readTransactionFile(String nameFile) {
        return;
    }

}
