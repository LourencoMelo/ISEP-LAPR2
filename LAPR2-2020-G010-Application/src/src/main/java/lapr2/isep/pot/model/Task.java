package lapr2.isep.pot.model;

public class Task {

    /**
     * Task's id
     */
    private String id;

    /**
     * Task's brief description
     */
    private String description;

    /**
     * Task's time duration (in hours)
     */
    private Double timeDuration;

    /**
     * Task's cost per hour (in euros)
     */
    private Double costPerHour;

    /**
     * Task's category
     */
    private String category;

    /**
     * Initialize the Task's information with the received data
     *
     * @param id Task's id
     * @param description Task's brief description
     * @param timeDuration Task's time duration (in hours)
     * @param costPerHour Task's cost per hour (in euros)
     * @param category Task's category
     */
    public Task(String id, String description, double timeDuration, double costPerHour, String category){
        this.id = id;
        this.description = description;
        this.timeDuration = timeDuration;
        this.costPerHour = costPerHour;
        this.category = category;
    }

    /**
     * Returns Task's id.
     *
     * @return id.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns Task's brief description.
     *
     * @return description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns Task's time duration (in hours).
     *
     * @return time duration.
     */
    public Double getTimeDuration() {
        return timeDuration;
    }

    /**
     * Returns Task's cost per hour (in euros).
     *
     * @return cost per hour.
     */
    public Double getCostPerHour() {
        return costPerHour;
    }

    /**
     * Returns Task's category.
     *
     * @return category.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Compares the Task with the received object.
     *
     * @param otherObject the object in comparison with the Task.
     * @return true if the object received represents an equivalent task to
     *         Task. Otherwise, returns false.
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || getClass() != otherObject.getClass()) {
            return false;
        }
        Task otherTask = (Task) otherObject;
        return id.equalsIgnoreCase(otherTask.id)  &&
                description.equalsIgnoreCase(otherTask.description)  &&
                timeDuration.equals(otherTask.timeDuration) &&
                costPerHour.equals(otherTask.costPerHour) &&
                category.equalsIgnoreCase(otherTask.category);
    }

    /**
     * Returns the Task's text description in the format: id, description, time duration, cost per hour and category
     *
     * @return Task's characteristics.
     */
    @Override
    public String toString() {
        return String.format("Task: Id- %s '\n'Brief Description- %s '\n'Time Duration- %f '\n'Cost Per Hour- %f '\n'Category- %s", id, description, timeDuration, costPerHour, category);
    }
}
