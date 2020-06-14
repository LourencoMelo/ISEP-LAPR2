import lapr2.isep.pot.model.Freelancer;
import lapr2.isep.pot.model.PaymentTransaction;
import lapr2.isep.pot.model.Task;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import static org.junit.Assert.*;

public class PaymentTransactionTest {

    Freelancer freelancer1 = new Freelancer("1","João", "Junior", "joao@example.com", "12313124", "PT50 0000320432", "Avenida da Boavista", "Portugal");

    Task task1 = new Task("1", "cook", 2, 2, "cooking");

    PaymentTransaction paymentTransaction1 = new PaymentTransaction("1", Formatter("2/04/2020"), 2, "Good", freelancer1, task1);

    private String date = "2/04/2020";

    private String DescQuality = "Good";

    public PaymentTransactionTest() throws ParseException {
    }

    @Test
    public void getTransId() {
        Assert.assertEquals("1", paymentTransaction1.getTransId());
    }

    @Test
    public void getEndDate() throws ParseException {
        Assert.assertEquals(Formatter(date), paymentTransaction1.getEndDate());
    }

    @Test
    public void getDelay() {
        Assert.assertEquals(2, paymentTransaction1.getDelay(), 2);
    }

    @Test
    public void getFreelancer() {
        Assert.assertEquals(freelancer1, paymentTransaction1.getFreelancer());
    }

    @Test
    public void getDescQualityOfWork() {
        Assert.assertEquals(DescQuality, paymentTransaction1.getDescQualityOfWork());
    }

    @Test
    public void getTask() {
        Assert.assertEquals(task1, paymentTransaction1.getTask());
    }

    public static Date Formatter(String date) throws ParseException {
        SimpleDateFormat formmater1 = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = formmater1.parse(date);
        return date1;
    }
}