package lapr2.isep.pot.model.List;

import lapr2.isep.pot.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    /**
     * Task's list
     */
    private final List<Task> taskList;

    /**
     * Build a task list
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Returns Task's list.
     *
     * @return task list
     */
    public List<Task> getTaskList(){
        return taskList;
    }

    /**
     * Creates a new task
     *
     * @param id Task's id
     * @param description Task's brief description
     * @param timeDuration Task's time duration (in hours)
     * @param costPerHour Task's cost per hour (in euros)
     * @param category Task's category
     * @return new task
     */
    public Task newTask(String id, String description, Double timeDuration, Double costPerHour, String category){
        return newTask(id, description, timeDuration, costPerHour, category);
    }

    /**
     * Verifies if this task can be created
     *
     * @param task verifing to add task
     * @return false, if the task already exists
     */
    public boolean taskCreation(Task task){
        if (this.taskValidation(task))
        {
            return addTask(task);
        }
        return false;
    }

    /**
     * Adds a task to the task list
     *
     * @param task adding task
     * @return true; if it adds a task
     */
    public boolean addTask(Task task) {
        return taskList.add(task);
    }

    /**
     * Checks if an equal task already exists in the task list
     *
     * @param task task to compare
     * @return true, if the task is already in the list
     */
    public boolean taskValidation(Task task) {
        for (Task auxTask: taskList) {
            return task.equals(auxTask);
        }
        return false;
    }
}
