package lapr2.isep.pot.model.List;

import lapr2.isep.pot.model.Freelancer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FreelancerList implements Serializable {

    private final List<Freelancer> freelancerList;

    public FreelancerList() {
        freelancerList = new ArrayList<>();
    }

    public List<Freelancer> getFreelancerList() {
        return freelancerList;
    }

}
