package lapr2.isep.pot.model;

import lapr2.isep.pot.model.List.PaymentTransactionList;
import lapr2.isep.pot.model.List.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

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
    private static final String FILE_TRANSACTIONS_REGIST = "src/Files/transactionsOrgSoftwareHome.csv";

    private RegistFreelancer registFreelancer;

    // LISTS

    /**
     * Initialization of TaskList.
     */
    private TaskList taskList;

    /**
     *Initialization of PaymentTransactionList.
     */
    private PaymentTransactionList paymentTransactionList;

    // Constructor

    public TransactionsRegist() throws FileNotFoundException {
        taskList = readTaskList();
        registFreelancer = readFreelancerList();
        paymentTransactionList = readTransactionList();
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
        return registFreelancer.getFreelancerList();
    }

    //<editor-fold desc="Read task list">
    public TaskList readTaskList() throws FileNotFoundException{
        return readTaskList(FILE_TRANSACTIONS_REGIST);
    }

    private  TaskList readTaskList(String fileTaskList) throws  FileNotFoundException{
        return readTaskList(new File(fileTaskList));
    }

    private TaskList readTaskList(File fileTaskList) throws FileNotFoundException {
        try{
            Scanner in = new Scanner(fileTaskList);
            TaskList copyTaskList = (TaskList) taskList.getTaskList();
            in.nextLine();
            while (in.hasNext()){
                String[] line = in.nextLine().trim().split(";");
                Task task = new Task(line[1].trim(), line[2].trim(), Double.parseDouble(line[3].trim()), Double.parseDouble(line[4].trim()), line[5].trim()); //problema por algumas cenas estarem em double na classe Task (discutir problema)
                if(!copyTaskList.contains(task)){
                    copyTaskList.addTask(task);
                }
            }
            return copyTaskList;
        } catch (FileNotFoundException fnfex){
            throw new FileNotFoundException("The File was not found!");
        }
    }
    //</editor-fold>

    //Payment Transaction

    //<editor-fold desc="Read freelancer list">
    public RegistFreelancer readFreelancerList() throws FileNotFoundException{
        return readFreelancerList(FILE_TRANSACTIONS_REGIST);
    }

    private RegistFreelancer readFreelancerList(String fileFreelancerList) throws FileNotFoundException{
        return readFreelancerList(new File(fileFreelancerList));
    }

    private RegistFreelancer readFreelancerList(File fileFreelancerList) throws FileNotFoundException{
        try {
            Scanner in = new Scanner(fileFreelancerList);
            RegistFreelancer freelancerList = (RegistFreelancer) registFreelancer.getFreelancerList();
            in.nextLine();
            while (in.hasNext()){
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
    //</editor-fold>

    public PaymentTransactionList readTransactionList() throws FileNotFoundException{
        return readTransactionList(FILE_TRANSACTIONS_REGIST);
    }

    private PaymentTransactionList readTransactionList(String fileTransactionList) throws FileNotFoundException {
        return readTransactionList(new File(fileTransactionList));
    }

    private PaymentTransactionList readTransactionList(File fileTransactionList) throws FileNotFoundException {
        try {
            Scanner in = new Scanner(fileTransactionList);
            PaymentTransactionList copyPaymentTransactionList = (PaymentTransactionList) paymentTransactionList.getTransactionList();
            in.nextLine();
            while (in.hasNext()){
                String[] line = in.nextLine().trim().split(";");
                PaymentTransaction paymentTransaction = new PaymentTransaction(line[0].trim(), line[6].trim(), Integer.parseInt(line[7].trim()), line[8].trim(), new Freelancer(line[9].trim(), line[10].trim(), line[11].trim(), line[12].trim(), line[13].trim(), line[14].trim(), line[15].trim(), line[16].trim()), new Task(line[1].trim(), line[2].trim(), Double.parseDouble(line[3].trim()), Double.parseDouble(line[4].trim()), line[5].trim()));
                if(!this.paymentTransactionList.contains(paymentTransaction)){
                    this.paymentTransactionList.addPaymentTransaction(paymentTransaction);
                }
            }
            return copyPaymentTransactionList;
        } catch (FileNotFoundException fnfex){
            throw new FileNotFoundException("The File was not found!");
        }
    }


}
