import lapr2.isep.pot.model.*;
import org.junit.Test;

import org.junit.Assert;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrganizationTest {

    Task task1 = new Task("1", "cook", 2, 2, "cooking");

    Task task2 = new Task("1", "cook", 2, 2, "cooking");

    Freelancer freelancer1 = new Freelancer("1","João", "Junior", "joao@example.com", "12313124", "PT50 0000320432", "Avenida da Boavista", "Portugal");

    Freelancer freelancer2 = new Freelancer("1","João", "Junior", "joao@example.com", "12313124", "PT50 0000320432", "Avenida da Boavista", "Portugal");

    PaymentTransaction paymentTransaction1 = new PaymentTransaction("1", Formatter("2/04/2020"), 2, "Good", freelancer1, task1);

    PaymentTransaction paymentTransaction2 = new PaymentTransaction("2", Formatter("2/04/2020"), 4, "Bad", freelancer2, task2);

    Collaborator collaborator= new Collaborator ("Joao", "joao@example.com");

    Manager manager = new Manager("Joao", "joao@example.com");

    Organization organization = new Organization("Microsoft", "1234556", collaborator, manager);

    List<PaymentTransaction> paymentTransactionsList = new ArrayList<>();

    public OrganizationTest() throws IOException, ParseException {
        paymentTransactionsList.add(paymentTransaction1);
        paymentTransactionsList.add(paymentTransaction2);
    }

    @Test
    public void getCollaborator() {
        Assert.assertEquals(collaborator, organization.getCollaborator());
    }

    @Test
    public void getManager() {
        Assert.assertEquals(manager, organization.getManager());
    }

    @Test
    public void hasCollaboratorWithEmail() {
        Assert.assertTrue(organization.hasCollaboratorWithEmail(collaborator.getEmail()));
    }

    @Test
    public void hasManagerWithEmail() {
        Assert.assertTrue(organization.hasManagerWithEmail(manager.getEmail()));
    }

    public static Date Formatter(String date) throws ParseException {
        SimpleDateFormat formmater1 = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = formmater1.parse(date);
        return date1;
    }
}