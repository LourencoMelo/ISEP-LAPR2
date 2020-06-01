package lapr2.isep.pot.model.List;

import lapr2.isep.pot.model.Collaborator;

import java.util.ArrayList;
import java.util.List;

public class CollaboratorList {

    /**
     * Collaborator's list
     */
    private final List<Collaborator> collaboratorList;

    /**
     * Build a collaborator list
     */
    public CollaboratorList(){
        collaboratorList = new ArrayList<>();
    }

    /**
     * Returns Collaborator's list.
     *
     * @return collaborator list
     */
    public List<Collaborator> getCollaboratorList(){
        return collaboratorList;
    }

    /**
     * Searches the list for the Collaborator
     *
     * @param email Collaborator's email
     * @return true, when finds the same Collaborator's email
     */
    public boolean hasCollaboratorWithEmail (String email){
        boolean found = false;
        for (int i=0; i<this.collaboratorList.size(); i++){
            Collaborator colab = this.collaboratorList.get(i);
            found = colab.hasEmail(email);
            if (found)
                break;
        }
        return found;
    }
}
