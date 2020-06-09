package lapr2.isep.pot.model;

import lapr2.isep.pot.model.List.FreelancerList;
import lapr2.isep.pot.model.List.PaymentTransactionList;
import lapr2.isep.pot.model.List.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class that registers the transactions receving a file of the type(.csv)
 */
public class TransactionsRegist implements Serializable {
    /**
     * Size of the Parameters of the file.
     */
    private final int SIZE_PARAMETERS = 17;
    /**
     * Name of the Transaciton´s Regist File.
     */
    private static final String FILE_TRANSACTIONS_REGIST = "transactionsOrgSoftwareHome.csv";
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


    // LISTS

    /**
     * Initialization of TaskList.
     */
    private TaskList taskList;

    /**
     *Initialization of PaymentTransactionList.
     */
    private PaymentTransactionList paymentTransactionList;

    /**
     *Initialization of Freelancer´s List.
     */
    private FreelancerList freelancerList;

    // Constructor

    public TransactionsRegist() throws FileNotFoundException {
        taskList = readTaskList();
        paymentTransactionList = readTransactionList;
        freelancerList = readFreelancerList();

    }


    //   GETS
    /**
     * Returns the tasklist.
     *
     * @return tasklist.
     */
    public List<Task> getTaskList(){
        return taskList.getTaskList();

    }

    /**
     * Returns the paymentTransactionList
     *
     * @return paymentTransactionList
     */
    public List<PaymentTransaction> getTransactionList(){
        return  paymentTransactionList.getTransactionList();
    }

    /**
     * Returns the freelancersList
     *
     * @return freelancerList
     */
    public List<Freelancer> getFreelancerList(){
        return freelancerList.getFreelancerList();
    }

    //READ

    //Task

    public TaskList readTaskList() throws FileNotFoundException{
        return  readTaskList(FILE_TRANSACTIONS_REGIST);
    }

    private  TaskList readTransactionList(String fileTaskList) throws  FileNotFoundException{
        return readTaskList(new File(fileTaskList));
    }

    private TaskList readTransactionList(File file) throws FileNotFoundException {
        try{
            Scanner in = new Scanner(file);
            TaskList taskList = new TaskList();
            while (in.hasNext()){
                String[] line = in.nextLine().trim().split(";");
                Task task = new Task(line[1].trim(), line[2].trim(), line[3].trim(), line[4].trim(), line[5].trim()); //problema por algumas cenas estarem em double na classe Task (discutir problema)
                if(!taskList.contains(task)){
                    taskList.addTask(task);
                }
            }
        } catch (FileNotFoundException fnfex){
            throw new FileNotFoundException("The File was not found!");
        }
    }



    //Payment Transaction

    //FREELANCER

    public FreelancerList readFreelancerList() throws FileNotFoundException{
        return readFreelancerList(FILE_TRANSACTIONS_REGIST);
    }

    private FreelancerList readFreelancerList(String fileFreelancerList) throws FileNotFoundException{
        return readFreelancerList(new File(fileFreelancerList));
    }

    private FreelancerList readFreelancerList(File fileFreelancerList) throws FileNotFoundException{
        try {
            Scanner in = new Scanner(fileFreelancerList);
            FreelancerList freelancerList = new FreelancerList();
            while (in.hasNext()){
                //é preciso passar a primeira linha ainda
                String[] line = in.nextLine().trim().split(";");
                Freelancer freelancer = new Freelancer(line[9].trim(), line[10].trim(), line[11].trim(), line[12].trim(), line[13].trim(), line[14].trim(), line[15].trim(), line[16].trim());
                if(!freelancerList.contains(freelancer)){
                    freelancerList.addFreelancer(freelancer);
                }
            }
            return freelancerList;
        } catch (FileNotFoundException fnfex){
            throw new FileNotFoundException("The File was not found!");
        }
    }

}
