package lapr2.isep.pot.model.List;

import lapr2.isep.pot.model.Organization;
import lapr2.isep.pot.model.Task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TaskList implements Serializable {

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
     * Creates one Task
     * @param task to create
     * @return true if creates or false if not
     */
    public boolean TaskCreation(Task task) {
        if (this.taskValidation(task)) {
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
     * If has the Task
     * @param task to compare
     * @return true if it has or false if not
     */
    public boolean taskValidation(Task task) {
        return taskList.contains(task);
    }

    @Override
    public String toString() {
        return String.format("\n%s", taskList);
    }
}
