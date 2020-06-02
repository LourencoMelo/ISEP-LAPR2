package lapr2.isep.pot.controller;

import lapr2.isep.pot.model.*;

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

    public Organization getOrganization() {
        return organization;
    }

    public boolean registOrganization() {
        return this.registOrganization.registOrganization(this.organization);
    }
}