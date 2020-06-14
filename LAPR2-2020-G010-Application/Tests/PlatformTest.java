import lapr2.isep.pot.model.Freelancer;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Assert;

public class PlatformTest {

    Freelancer freelancer1 = new Freelancer("1","João", "Junior", "joao@example.com", "12313124", "PT50 0000320432", "Avenida da Boavista", "Portugal");

    public void percentageDelayByFreelancer() {
        freelancer1.setNumberOfTasks(10);
        int numberOfTasks = freelancer1.getNumberOfTasks();
        int numberOfTasksWithDelay = 5;

    }

    @Test
    public void percentageDelayTotal() {
    }


}