package lapr2.isep.pot.controller;

import lapr2.isep.pot.model.List.TaskList;
import lapr2.isep.pot.model.Platform;
import lapr2.isep.pot.model.Task;

public class TaskCreationController {

    /**
     * Task's List
     */
    private TaskList taskList;

    /**
     * Platform
     */
    private final Platform platform;

    /**
     * Task
     */
    private Task task;

    /**
     * Creates a task creation controller
     */
    public TaskCreationController(){
        this.platform = ApplicationPOT.getInstance().getPlatform();
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
    public boolean newTask(String id, String description, Double timeDuration, Double costPerHour, String category){
        this.task = this.taskList.newTask(id, description, timeDuration, costPerHour, category);
        return this.taskList.taskValidation(this.task);
    }

    /**
     * Creates a new task
     *
     * @return true, if it creates a new task
     */
    public boolean taskCreation(){
        return this.taskList.taskCreation(this.task);
    }
}
