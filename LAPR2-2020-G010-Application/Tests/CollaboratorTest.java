import lapr2.isep.pot.model.Collaborator;
import lapr2.isep.pot.model.Manager;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

import org.junit.Assert;

public class CollaboratorTest {

    Collaborator collaborator= new Collaborator ("Joao", "joao@example.com");


    String email = "joao@example.com";

    public CollaboratorTest() throws IOException {
    }

    @Test
    public void hasEmail() {
        assertTrue(collaborator.hasEmail(email));
    }
}