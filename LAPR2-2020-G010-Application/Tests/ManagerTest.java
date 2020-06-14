import lapr2.isep.pot.model.Manager;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Assert;

import java.io.IOException;

public class ManagerTest {

    Manager manager = new Manager("Joao", "joao@example.com");

    private String email = "joao@example.com";

    public ManagerTest() throws IOException {
    }

    @Test
    public void hasEmail() {
        assertTrue(manager.hasEmail(email));
    }
}