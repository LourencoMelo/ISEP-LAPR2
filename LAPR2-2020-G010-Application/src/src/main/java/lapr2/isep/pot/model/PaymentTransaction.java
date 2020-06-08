package lapr2.isep.pot.model;

import javafx.fxml.Initializable;

import java.util.Date;

public class PaymentTransaction {

    /**
     * Transaction ID
     */
    private String transId;

    /**
     * Task end date
     */
    private Date endDate;

    /**
     * Delay
     */
    private Integer delay;

    /**
     * Description of the quality of work
     */
    private String descQualityOfWork;

    /**
     * Amount to pay to the freelancer
     */
    private double amountPay;

    /**
     * The freelancer that made the task
     */
    private Freelancer freelancer;

    /**
     * Task that was made
     */
    private Task task;

    /**
     * Initialize the transaction's information with the received data
     *
     * @param transId transaction ID
     * @param endDate end date of the task
     * @param delay delay
     * @param descQualityOfWork Description of the quality of work
     * @param freelancer The freelancer that made the task
     * @param task Task that was made
     */

    public PaymentTransaction(String transId,Date endDate,Integer delay,String descQualityOfWork,Freelancer freelancer,Task task){
        if(transId == null || endDate == null || delay == null || descQualityOfWork == null || transId.isEmpty() || descQualityOfWork.isEmpty()){
            throw new IllegalArgumentException("Arguments cant be null or empty.");
        }
        this.transId = transId;
        this.endDate = endDate;
        this.delay = delay;
        this.descQualityOfWork = descQualityOfWork;
        this.freelancer = freelancer;
        this.task = task;
    }

    /**
     * Returns Transaction ID
     *
     * @return transaction ID
     */
    public String getTransId() {
        return transId;
    }

    /**
     * Returns end Date
     *
     * @return end date
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Returns delay
     *
     * @return delay
     */
    public Integer getDelay() {
        return delay;
    }

    /**
     * Returns Description of the quality of work
     *
     * @return Description of the quality of work
     */
    public String getDescQualityOfWork() {
        return descQualityOfWork;
    }

    /**
     *  Returns the transaction's text description in the format: id, end date, delay, description of the quality of work, freelancer , task
     *
     * @return Transaction's characteristics.
     */
    @Override
    public String toString() {
        return "PaymentTransaction{" +
                "transId='" + transId + '\'' +
                ", endDate=" + endDate +
                ", delay=" + delay +
                ", descQualityOfWork='" + descQualityOfWork + '\'' +
                ", freelancer=" + freelancer +
                ", task=" + task +
                '}';
    }


}















