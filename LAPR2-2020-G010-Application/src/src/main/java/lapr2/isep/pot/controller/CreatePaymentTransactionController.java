package lapr2.isep.pot.controller;

import lapr2.isep.pot.model.Freelancer;
import lapr2.isep.pot.model.List.TaskList;
import lapr2.isep.pot.model.PaymentTransaction;
import lapr2.isep.pot.model.RegistFreelancer;
import lapr2.isep.pot.model.Task;

import java.util.List;

public class CreatePaymentTransactionController {

    private ApplicationController applicationController = ApplicationController.getApplicationController();

    private RegisterFreelancerController registerFreelancerController = applicationController.getRegistFreelancerController();

    private TaskCreationController taskCreationController = applicationController.getTaskCreationController();
    /**
     * Task's List
     */


    /**
     * Regist Freelancer
     */

    public CreatePaymentTransactionController() {

    }

    /**
     * Returns the list of freelancers
     *
     * @return
     */
    public List<Freelancer> getListFreelancers() {
        return registerFreelancerController.getRegistFreelancer().getFreelancerList();
    }

    /**
     *
     */

    public void getFreelancerByID() {
    }

    public void getTaskByID() {
    }

    /**
     * Returns organizations task's list
     *
     * @return Task's List
     */
    public List<Task> getTaskList() {
        return taskCreationController.getTaskList();
    }
}
