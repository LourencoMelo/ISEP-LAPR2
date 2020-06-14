package lapr2.isep.pot.model;

import lapr2.isep.pot.controller.ApplicationPOT;

import java.io.*;
import java.util.Date;
import java.util.TimerTask;

public class DelayHigherThanThreeAutomaticWarning extends TimerTask implements Serializable {

    /**
     * Date's instance
     */
    private Date date;

    /**
     * Freelancer's instance
     */
    private Freelancer freelancer;

    /**
     * Plataform's instance
     */
    private final Platform platform;

    /**
     * ApplicationPot's instance
     */
    private final ApplicationPOT applicationPOT;

    /**
     * Creates a new File
     */
    private final File file = new File("files\\e-mail.txt");

    /**
     * Constructor that initializes dae, freelancer and applicationPot and platforms instances
     * @param freelancer                freelancer selected
     * @throws FileNotFoundException    if file not found
     */
    public DelayHigherThanThreeAutomaticWarning(Freelancer freelancer) throws FileNotFoundException {
        this.date = date;
        this.freelancer = freelancer;
        this.applicationPOT = ApplicationPOT.getInstance();
        this.platform = applicationPOT.getPlatform();
    }

    /**
     * Method that allows us to run the timer task
     */
    @Override
    public void run() {
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write("To: " + freelancer.getEmail()+ "\n");
            writer.write("Subject: Delay warning - T4J" + "\n");
            writer.write(" Message: \n" +
                    "\tDear " + freelancer.getName() + ", this is a warning automatic email that your shouldn't answer. " +
                    "\n\tYou can't have a delay mean higher than 3. At this moment you have the following mean: " + platform.meanDelayAllTasksByFreelancer(freelancer) + " days." +
                    "\n" +
                    "\n" +
                    "\nBest regards," +
                    "\n T4J Team.\n" +
                    "\n" +
                    "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
