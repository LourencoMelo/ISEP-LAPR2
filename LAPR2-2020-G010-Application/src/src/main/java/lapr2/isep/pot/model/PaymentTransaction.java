package lapr2.isep.pot.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Timer;

public class PaymentTransaction implements Serializable {

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
    private double delay;

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

    private Date dateToPay;

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

    public PaymentTransaction(String transId,Date endDate,double delay,String descQualityOfWork,Freelancer freelancer,Task task){
        if(transId == null || endDate == null || descQualityOfWork == null || transId.isEmpty() || descQualityOfWork.isEmpty()){
            throw new IllegalArgumentException("Arguments cant be null or empty.");
        }
        this.transId = transId;
        this.endDate = endDate;
        this.delay = delay;
        this.descQualityOfWork = descQualityOfWork;
        this.freelancer = freelancer;
        this.task = task;
        amountPay();
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
    public double getDelay() {
        return delay;
    }

    /**
     * Returns payment transaction's freelancer
     * @return freelancer
     */
    public Freelancer getFreelancer() {
        return freelancer;
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
     * Returns payment transaction's task
     * @return task
     */
    public Task getTask() {
        return task;
    }

    /**
     * Returns payment transaction's amount to pay
     * @return amount to pay
     */
    public double getAmountPay() {
        return this.amountPay;
    }

    /**
     * Sets the date to the automatic payment
     * @param dateToPay     selected date
     * @throws IOException  if input or output not correct
     */
    public void setDateToPay(Date dateToPay) throws IOException {
        TaskPaymentAutomatically taskPaymentAutomatically = new TaskPaymentAutomatically(dateToPay, freelancer, getAmountPay());
        Timer timer = new Timer();
        timer.schedule(taskPaymentAutomatically, dateToPay);
        this.dateToPay = dateToPay;
    }

    /**
     * Returns the date to pay
     * @return
     */
    public Date getDateToPay() {
        return dateToPay;
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

    /**
     * Returns task's cost considering the formula
     * @param freelancer    task's freelancer
     * @param task          task
     * @return task's cost
     */
    public double calculateTaskCost(Freelancer freelancer, Task task) {
        if (freelancer.getLevelOfExpertise().equalsIgnoreCase("Senior")) {
            return  (2 * task.getCostPerHour()) * task.getTimeDuration();
        } else if (freelancer.getLevelOfExpertise().equalsIgnoreCase("Junior")) {
            return  task.getCostPerHour() * task.getTimeDuration();
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Sets amount to pay instance to the value returned on calculate task cost method
     */
    public void amountPay(){
        this.amountPay = calculateTaskCost(this.freelancer, this.task);
    }

    /**
     * Checks if 2 transactions have the same id or are equal
     * @param o other transaction to compare
     * @return  true if two tasks are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentTransaction that = (PaymentTransaction) o;
        return Objects.equals(transId, that.transId) ||
                Objects.equals(task, that.task);
    }

    /**
     * Manages payment transaction's attributes
     * @return managed attribute
     */
    @Override
    public int hashCode() {
        return Objects.hash(transId, endDate, delay, descQualityOfWork, amountPay, freelancer, task);
    }

}















