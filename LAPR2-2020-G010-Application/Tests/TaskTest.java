import lapr2.isep.pot.model.Task;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TaskTest {

    Task task1 = new Task("1", "cook", 2, 2, "cooking");

    @Test
    public void getId() {
        Assert.assertEquals("1", task1.getId());
    }

    @Test
    public void getDescription() {
        Assert.assertEquals("cook", task1.getDescription());
    }

    @Test
    public void getTimeDuration() {
        Assert.assertEquals(2, task1.getTimeDuration(), 2);
    }

    @Test
    public void getCostPerHour() {
        Assert.assertEquals(2, task1.getCostPerHour(), 2);
    }

    @Test
    public void getCategory() {
        Assert.assertEquals("cooking", task1.getCategory());
    }
}