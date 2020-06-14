import lapr2.isep.pot.model.Freelancer;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Assert;

public class FreelancerTest {

    Freelancer freelancer = new Freelancer("1","João", "Junior", "joao@example.com", "12313124", "PT50 0000320432", "Avenida da Boavista", "Portugal");

    @Test
    public void getId() {
        Assert.assertEquals("1", freelancer.getId());
    }

    @Test
    public void getName() {
        Assert.assertEquals("João", freelancer.getName());
    }

    @Test
    public void getLevelOfExpertise() {
        Assert.assertEquals("Junior", freelancer.getLevelOfExpertise());
    }

    @Test
    public void getEmail() {
        Assert.assertEquals("joao@example.com" , freelancer.getEmail());
    }

    @Test
    public void getNIF() {
        Assert.assertEquals("12313124", freelancer.getNIF());
    }

    @Test
    public void getBankAccountIBAN() {
        Assert.assertEquals("PT50 0000320432", freelancer.getBankAccountIBAN());
    }

    @Test
    public void getAddress() {
        Assert.assertEquals("Avenida da Boavista", freelancer.getAddress());
    }

    @Test
    public void getCountry() {
        Assert.assertEquals("Portugal", freelancer.getCountry());
    }
}