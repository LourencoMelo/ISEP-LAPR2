package lapr2.isep.pot.controller;

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
    private TaskList taskList = new TaskList();

    /**
     * Task
     */
    private Task task;

    /**
     *  Task's id
     */
    private String id;


    /**
     * Platform
     */
    private Platform platform = ApplicationPOT.getInstance().getPlatform();



    /**
     * Creates a task creation controller
     */
    public TaskCreationController(){
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
        this.task = taskList.newTask(id, description, timeDuration, costPerHour, category);
        return this.task;
    }

    /**
     * Creates a new task
     *
     * @return true, if it creates a new task
     */
    public boolean taskCreation(Task task){
        if (taskList.taskValidation(id)){
            return taskList.addTask(task);
        }
        return false;
    }



    /**
     * Returns organizations task's list
     *
     * @return Task's List
     */
    public List<Task> getTaskList(){
        return taskList.getTaskList();
    }

    /**
     * Returns if the task already exists or not
     * @param id  task's id
     * @return   false if the task already exists
     */
    public boolean getTaskValidation(String id){
        return taskList.taskValidation(id);
   }

    public TaskList getListTask() {
        return taskList;
    }

}
