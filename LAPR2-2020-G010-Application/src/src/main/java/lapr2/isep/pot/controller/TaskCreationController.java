package lapr2.isep.pot.controller;

import lapr2.isep.pot.model.List.TaskList;
import lapr2.isep.pot.model.Organization;
import lapr2.isep.pot.model.Platform;
import lapr2.isep.pot.model.RegistOrganization;
import lapr2.isep.pot.model.Task;

import java.io.Serializable;
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
     *  Organization
     */
    private Organization organization;

    /**
     * Platform
     */
    private Platform platform;

    private RegistOrganizationController registOrganizationController = RegistOrganizationController.getRegistOrganizationController();

    /**
     * Creates a task creation controller
     */
    public TaskCreationController(){
        organization = platform.getRegistOrganization();
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
    public boolean taskCreation(Task task){
        return taskList.TaskCreation(task);
    }

    /**
     * Add the received task
     * @param task to add
     * @return true if added or false if not
     */
    public boolean addTask(Task task) {
        return organization.addTask(task);
    }

    /**
     * Confirms if has or not the task
     * @param task to compare
     * @return true if has or false if not
     */
    public boolean taskValidation(Task task) {
        return organization.TaskValidation(task);
    }

    /**
     * Returns organizations task's list
     *
     * @return Task's List
     */
    public TaskList getTaskList(){
        return organization.getTaskList();
    }

    /**
     * Returns Organization
     *
     * @return organization
     */
    public Organization getOrganization(){
        return organization;
    }

    /**
     * Returns Task
     *
     * @return task
     */
    public Task getTask() {
        return task;
    }
}
