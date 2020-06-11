package lapr2.isep.pot.model;

import javafx.fxml.Initializable;

import java.util.Date;
import java.util.Objects;

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

    public Task getTask() {
        return task;
    }

    /**
     *  Returns the transaction's text description in the format: id, end date, delay, description of the quality of work, freelancer , task
     *
     * @return Transaction's characteristics.
     */



    @Override
    public String toString() {
        return "PaymentTransaction: " +
                "\n\tTransaction ID: " + transId +
                "\n\tEnd date: " + endDate +
                "\n\tDelay: " + delay + " days" +
                "\n\tDescription quality of work: " + descQualityOfWork + "\n" +
                "\n"  + freelancer + "\n" +
                "\n" + task;
    }

    public double calculateTaskCost(Freelancer freelancer, Task task) {
        if (freelancer.getLevelOfExpertise().equalsIgnoreCase("senior")) {
            return (2 * task.getCostPerHour()) * task.getTimeDuration();
        } else if (freelancer.getLevelOfExpertise().equalsIgnoreCase("junior")) {
            return task.getCostPerHour() * task.getTimeDuration();
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentTransaction that = (PaymentTransaction) o;
        return Double.compare(that.amountPay, amountPay) == 0 &&
                Objects.equals(transId, that.transId) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(delay, that.delay) &&
                Objects.equals(descQualityOfWork, that.descQualityOfWork) &&
                Objects.equals(freelancer, that.freelancer) &&
                Objects.equals(task, that.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transId, endDate, delay, descQualityOfWork, amountPay, freelancer, task);
    }
}















