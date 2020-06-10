package lapr2.isep.pot.controller;

import lapr2.isep.authorization.model.User;
import lapr2.isep.pot.UI.console.utils.RegistOrganizationUI;
import lapr2.isep.pot.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RegistOrganizationController implements Serializable {

    /**
     * Plataform's initialization
     */
    private final Platform platform ;

    private final ApplicationPOT applicationPOT;

    /**
     * Organization's initialization
     */
    private Organization organization;

    private List<Collaborator> collaboratorListRegistered = new ArrayList<>();

    /**
     * RegistOrganization's initialization
     */
    private RegistOrganization registOrganization;

    public RegistOrganizationController() {
        this.applicationPOT = ApplicationPOT.getInstance();
        this.platform = applicationPOT.getPlatform();
        this.registOrganization = platform.getRegistOrganization();
        readInfo();
    }

    private void readInfo() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File("dados.bin")));
            this.registOrganization = (RegistOrganization) in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("ERRO: nao abriu o ficheiro para leitura");
        }
    }

    public void saveInfo(){
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File("dados.bin")));
            out.writeObject(this.registOrganization);
            out.close();
        } catch (IOException e) {
            System.out.println("ERRO: nao abriu o ficheiro para escrita");
        }

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
    public boolean newOrganization(String name, String NIF, String nameCollab, String emailCollab, String nameManager, String emailManager) throws IOException {                       //Is necessary to have password by param?
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
        return this.platform.addOrganization(organization);
    }

    /**
     * Confirms if has or not the organization
     * @param organization to compare
     * @return true if has or false if not
     */
    public boolean hasOrganization(Organization organization) {
        return this.platform.hasOrganization(organization);
    }

    public Organization getOrganization() {
        return this.organization;
    }

    public boolean registOrganization() {
        return this.registOrganization.registOrganization(this.organization);
    }

    public List<Organization> getListOrganizations() {
        return this.registOrganization.getListOrganizations();
    }


    public Platform getPlatform() {
        return this.platform;
    }

    public String getRoleUser(User user) {
        return this.platform.getRoleUser(user);
    }


    /*public boolean addCollaboratorRegistered(String name, String email, String password) throws IOException {
        collaboratorListRegistered.add(new Collaborator(name, email, password));
    }

     */
}