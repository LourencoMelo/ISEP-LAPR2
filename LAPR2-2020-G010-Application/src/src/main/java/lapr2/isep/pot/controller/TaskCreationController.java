package lapr2.isep.pot.controller;

import lapr2.isep.pot.model.*;
import lapr2.isep.pot.model.List.TaskList;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.List;

/**
 * Controller of the creation of the task.
 *
 * @author José Soares, João Beires, José Maia, Lourenço Melo, Gonçalo Ferreira.
 */
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
     * Task's id
     */
    private String id;

    /**
     * ApplicationPOT's instace
     */
    private ApplicationPOT applicationPOT;

    /**
     * Platform
     */
    private Platform platform;

    /**
     * Organization's instance
     */
    private Organization organization;


    /**
     * Constructor that initialize the applicationPOT and platform and tasklist instances
     *
     * @throws FileNotFoundException if the file is not found
     */
    public TaskCreationController() throws FileNotFoundException {
        this.applicationPOT = ApplicationPOT.getInstance();
        this.platform = applicationPOT.getPlatform();
        this.taskList = platform.getTasksList();
        this.task = null;
    }

    /**
     * Creates a new task and verifies if it already exists
     *
     * @param id           Task's id
     * @param description  Task's brief description
     * @param timeDuration Task's time duration (in hours)
     * @param costPerHour  Task's cost per hour (in euros)
     * @param category     Task's category
     * @return true, if this task doesn´t already exists
     */
    public Task newTask(String id, String description, Double timeDuration, Double costPerHour, String category) {
        this.organization = platform.getRegistOrganization().getOrganizationByUserEmail(platform.getCurrentUserEmail());
        List<Task> list = this.organization.getTaskList();
        this.task = this.taskList.newTask(id, description, timeDuration, costPerHour, category);
        list.add(this.task);
        return this.task;
    }

    /**
     * Creates a new task
     *
     * @return true, if it creates a new task
     */
    public boolean taskCreation() {
        if (taskList.taskValidation(this.id)) {
            return this.taskList.addTask(this.task);
        }
        return false;
    }

    /**
     * Returns if the task already exists or not
     *
     * @param id task's id
     * @return false if the task already exists
     */
    public boolean getTaskValidation(String id) {
        return this.taskList.taskValidation(id);
    }

    /**
     * Gets the task list
     *
     * @return list of tasks
     */
    public List<Task> getTaskLists() {
        return platform.getRegistOrganization().getOrganizationByUserEmail(platform.getCurrentUserEmail()).getTaskList();
    }

    /**
     * Returns the collaborator
     *
     * @return collaborator
     */
    public Collaborator getCollaborator() {
        return platform.getCollaborator();
    }

}
