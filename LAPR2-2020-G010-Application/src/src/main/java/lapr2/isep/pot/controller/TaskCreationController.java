package lapr2.isep.pot.controller;

import com.sun.org.apache.xpath.internal.operations.Or;
import lapr2.isep.pot.model.List.TaskList;
import lapr2.isep.pot.model.Organization;
import lapr2.isep.pot.model.Platform;
import lapr2.isep.pot.model.RegistOrganization;
import lapr2.isep.pot.model.Task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TaskCreationController implements Serializable {

    /**
     * Task's List
     */
    private TaskList taskList;

    /**
     * Task
     */
    private Task task;

    /**
     *  Task's id
     */
    private String id;


    private ApplicationPOT applicationPOT;


    /**
     * Platform
     */
    private Platform platform;

    /**
     * Organization's instance
     */
    private static Organization organization;


    /**
     * Creates a task creation controller
     */
    public TaskCreationController(){
        this.applicationPOT = ApplicationPOT.getInstance();
        this.platform = applicationPOT.getPlatform();
    }

    /**
     * Creates a new task and verifies if it already exists
     *
     * @param id Task's id
     * @param description Task's brief description
     * @param timeDuration Task's time duration (in hours)
     * @param costPerHour Task's cost per hour (in euros)
     * @param category Task's category
     * @return true, if this task doesn´t already exists
     */
    public Task newTask(String id, String description, Double timeDuration, Double costPerHour, String category){
        this.task = this.taskList.newTask(id, description, timeDuration, costPerHour, category);
        return this.task;
    }

    /**
     * Creates a new task
     *
     * @return true, if it creates a new task
     */
    public boolean taskCreation(Task task){
        if (this.taskList.taskValidation(id)){
            return this.taskList.addTask(task);
        }
        return false;
    }



//    /**
//     * Returns organizations task's list
//     *
//     * @return Task's List
//     */
//    public List<Task> getTaskList(){
//
//    }

    /**
     * Returns if the task already exists or not
     * @param id  task's id
     * @return   false if the task already exists
     */
    public boolean getTaskValidation(String id){
        return this.taskList.taskValidation(id);
   }

    public TaskList getListTask() {
        return this.taskList;
    }

//    public List<Task> getTaskListByOrganization(){
//        return platform.getRegistOrganization().
//    }

}
