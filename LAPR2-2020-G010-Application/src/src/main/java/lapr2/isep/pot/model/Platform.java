package lapr2.isep.pot.model;

import lapr2.isep.authorization.model.User;
import lapr2.isep.pot.model.List.PaymentTransactionList;
import lapr2.isep.pot.model.List.TaskList;

import java.io.*;
import java.util.List;

public class Platform implements Serializable {


    private Freelancer selectedFreelancer;

    private Task selectedTask;

    private TransactionsRegist transactionsRegist;

    /**
     * Regist Freelancer instance
     */

    private  RegistFreelancer registFreelancer;

    /**
     * Regist Organization instance
     */
    private RegistOrganization registOrganization;

    private TaskList taskList;

    public Platform() throws FileNotFoundException {
        this.registFreelancer = new RegistFreelancer();
        this.registOrganization = new RegistOrganization();
        this.taskList = new TaskList();
        //this.transactionsRegist = new TransactionsRegist();
    }

    /*private void readInfo() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File("dados.bin")));
            this.registOrganization = (RegistOrganization) in.readObject();
            System.out.println("Abriu para ler");
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

     */

    public ExternAlgorithmPasswordGenerator getAlgorithmPasswordGenerator() {
        return null;
    }


    /**
     * Returns Organization
     *
     * @return Organization
     */
    public RegistOrganization getRegistOrganization() {
        return this.registOrganization;
    }

    /**
     * Returns RegistFreelancer
     * @return registFreelancer
     */

    public RegistFreelancer getRegistFreelancer(){
        return this.registFreelancer;
    }

    public boolean addOrganization(Organization organization) {
        return registOrganization.addOrganization(organization);
    }

    /**
     * confirms if the the list has the organization
     * @param organization to confirm
     * @return true if has or false if not
     */
    public boolean hasOrganization(Organization organization) {
        return registOrganization.hasOrganization(organization);
    }
    

//    public String getRoleUser(User user) {
//        return user.getRole();
//    }

    public Freelancer getSelectedFreelancer() {
        return selectedFreelancer;
    }

    public void setSelectedFreelancer(Freelancer selectedFreelancer) {
        this.selectedFreelancer = selectedFreelancer;
    }

    public Task getSelectedTask() {
        return selectedTask;
    }

    public void setSelectedTask(Task selectedTask) {
        this.selectedTask = selectedTask;
    }

    public boolean userIsCollaborator(User user){
        for (Collaborator collaborator : getListCollaboratorsAllOrganizations()) {
            if (collaborator.getEmail().equalsIgnoreCase(user.getEmail()) && collaborator.getName().equalsIgnoreCase(user.getName())) {
                for (User userAux : getListUsers()) {
                    if (userAux.getPassword().equalsIgnoreCase(user.getPassword())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean userIsManager(User user){
        for (Manager manager : getListManagersAllOrganizations()) {
            if (manager.getEmail().equalsIgnoreCase(user.getEmail()) && manager.getName().equalsIgnoreCase(user.getName())) {
                for (User userAux : getListUsers()) {
                    if (userAux.getPassword().equalsIgnoreCase(user.getPassword())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean userExist(String email, String password) {
        boolean exist = false;
        for(User user: User.getListUsers()) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                exist = true;
                break;
            }
        }
        return exist;
    }

    public boolean userExist(User user) {
        return User.getListUsers().contains(user);
    }

    public Organization getOrganizationByCollaborator(Collaborator collaborator) {
        return registOrganization.getOrganizationByCollaborator(collaborator);
    }

    public List<Collaborator> getListCollaboratorsAllOrganizations() {
        return registOrganization.getListCollaboratorsAllOrganizations();
    }

    public List<Manager> getListManagersAllOrganizations() {
        return registOrganization.getListManagersAllOrganizations();
    }

    public List<User> getListUsers() {
        return User.getListUsers();
    }

    /*public List<Task> getListOfTasks() {
        return registOrganization.getListOfTasks();
    }

     */

    public List<Task> getListOfTasksFromOrganization(Organization organization) {
        return organization.getTaskList();
    }

    public Collaborator getCollaborator() {
        return registOrganization.getCollaborator();
    }

    public TaskList getTasksList() {
        return taskList;
    }

}
