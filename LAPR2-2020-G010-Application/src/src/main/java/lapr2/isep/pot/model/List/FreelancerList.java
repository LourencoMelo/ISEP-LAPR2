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

    public boolean contains(Freelancer freelancer){
        for(Freelancer freelancerAux : freelancerList){
            return freelancer.equals(freelancerAux);
        }
        return false;
    }

    public boolean addFreelancer(Freelancer freelancer){
        return freelancerList.add(freelancer);
    }

}
