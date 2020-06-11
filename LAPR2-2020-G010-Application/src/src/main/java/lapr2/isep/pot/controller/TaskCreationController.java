package lapr2.isep.pot.controller;

import lapr2.isep.pot.model.*;
import lapr2.isep.pot.model.List.TaskList;

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
        this.taskList = platform.getTasksList();
        this.task = null;
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
    public boolean taskCreation(){
        if (taskList.taskValidation(this.id)){
            return this.taskList.addTask(this.task);
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

    public TaskList getTaskList() {
        return taskList;
    }
    public List<Task> getTaskLists() {
        return taskList.getTaskList();
    }

    public List<Task> getTaskListByOrganization(Organization organization){
        return platform.getListOfTasksFromOrganization(organization);
   }

    public Organization getOrganizationByCollaborator(Collaborator collaborator) {
        return platform.getOrganizationByCollaborator(collaborator);
    }

    public Collaborator getCollaborator() {
        return platform.getCollaborator();
    }

}
