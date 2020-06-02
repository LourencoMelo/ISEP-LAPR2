package lapr2.isep.pot.model.List;

import com.sun.org.apache.xpath.internal.operations.Or;
import lapr2.isep.pot.model.Organization;

import java.util.ArrayList;
import java.util.List;

public class OrganizationList {

    /**
     * Inicialização da lista de organizações
     */
    private final List<Organization> organizationList;

    /**
     * Constructor creating organization's list
     */
    public OrganizationList() {
        this.organizationList = new ArrayList<>();
    }

    /**
     * return organization's list
     * @return list of organizations
     */
    public List<Organization> getOrganizationList() {
        return organizationList;
    }

    /**
     * Add one organization to the list
     * @param organization to add
     * @return true if add or false if is already in the list
     */
    public boolean addOrganization(Organization organization) {
        return !organizationList.contains(organization) && organizationList.add(organization);
    }

    /**
     * Description of the organizations in the list
     * @return organizations in string
     */
    public String toString() {
        List<Organization> aux = new ArrayList<>(organizationList);

        StringBuilder s = new StringBuilder();
        for (Organization organization : aux) {
            s.append(organization);
            s.append("\n");
        }

        return s.toString().trim();
    }
}
