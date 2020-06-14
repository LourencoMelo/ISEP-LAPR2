package lapr2.isep.pot.model.List;


import lapr2.isep.pot.model.Task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class the contains the imformation of the tasks.
 * @author José Soares, João Beires, José Maia, Lourenço Melo, Gonçalo Ferreira.
 */
public class TaskList implements Serializable {

    /**
     * Task's list
     */
    private final List<Task> taskList = new ArrayList<>();

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
        return new Task(id, description, timeDuration, costPerHour, category);
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
     * @param id to compare
     * @return true if it has or false if not
     */
    public boolean taskValidation(String id) {
        for (Task aux : taskList){
            if (aux.getId().equalsIgnoreCase(id)){
                return false;
            }
        }
        return true;
    }

    /**
     * Returns textual description of task
     * @return textual description
     */
    @Override
    public String toString() {
        return String.format("\n %s", taskList);
    }

    /**
     * Returns a task from its id
     * @param id of the task we want to return
     * @return the task with the id received
     */
    public Task getTaskByID(String id) {
        for(Task task: taskList) {
            if (id.equals(task.getId())) {
                return task;
            }
        }
        return null;
    }

    /**
     * True or false dependent if the received task is already in the tasks list
     * @param task to confirm
     * @return true if contains or false if not
     */
    public boolean contains(Task task){
        for(Task freelancerAux : taskList){
            return task.equals(freelancerAux);
        }
        return false;
    }

    /**
     * Returns Tasks list.
     *
     * @return tasks list
     */
    public List<Task> getTaskList(){
        return taskList;
    }

}
