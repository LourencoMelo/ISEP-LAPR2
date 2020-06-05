package lapr2.isep.pot.model.List;

import lapr2.isep.pot.model.Collaborator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CollaboratorList implements Serializable {

    /**
     * Collaborator's list
     */
    private final List<Collaborator> collaboratorList;

    /**
     * Build a collaborator list
     */
    public CollaboratorList(){
        this.collaboratorList = new ArrayList<>();
    }

    /**
     * Returns Collaborator's list.
     *
     * @return collaborator list
     */
    public List<Collaborator> getCollaboratorList(){
        return collaboratorList;
    }

}
