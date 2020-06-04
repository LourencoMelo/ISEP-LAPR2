package lapr2.isep.pot.controller;

import lapr2.isep.pot.model.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegistOrganizationController {

    /**
     * Plataform's initialization
     */
    private Platform platform;

    /**
     * Organization's initialization
     */
    private Organization organization;

    /**
     * RegistOrganization's initialization
     */
    private RegistOrganization registOrganization;

    public RegistOrganizationController() {
        platform = new Platform();
    }

    /**
     * creates new organization
     * @param name of organization
     * @param NIF of organization
     * @param nameCollab collaborator's name
     * @param emailCollab collaborator's email
     * @param nameManager managaer's name
     * @param emailManager manager's email
     * @return
     */
    public boolean newOrganization(String name, String NIF, String nameCollab, String emailCollab, String nameManager, String emailManager) {                       //Is necessary to have password by param?
        //try
        //{
        Collaborator collaborator = Organization.newCollaborator(nameCollab, emailCollab);
        Manager manager = Organization.newManager(nameManager, emailManager);
        this.organization = this.registOrganization.newOrganization(name, NIF, collaborator, manager);
        return this.registOrganization.validatesOrganization(this.organization);
    }
        /*catch(RuntimeException ex)
        {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            this.m_oOrganizacao = null;
            return false;
        }

         */

    /**
     * Add the received organization
     * @param organization to add
     * @return true if added or false if not
     */
    public boolean addOrganization(Organization organization) {
        return platform.addOrganization(organization);
    }

    /**
     * Confirms if has or not the organization
     * @param organization to compare
     * @return true if has or false if not
     */
    public boolean hasOrganization(Organization organization) {
        return platform.hasOrganization(organization);
    }

    public Organization getOrganization() {
        return organization;
    }

    public boolean registOrganization() {
        return this.registOrganization.registOrganization(this.organization);
    }

    public List<Organization> getListOrganizations() {
        return platform.getListOrganizations();
    }
}