package lapr2.isep.pot.model;

import lapr2.isep.authorization.FacadeAuthorization;

public class Platform implements ExternAlgorithmPasswordGenerator {

    /**
     * FacadeAuthorization's initialization
     */
    private final FacadeAuthorization facadeAuthorization;

    /**
     * Platform's designation
     */
    private String designation;

    /**
     * Regist Freelancer instance
     */

    private final RegistFreelancer registFreelancer;

    /**
     * Organization's initialization
     */
    private Organization organization;

    public Platform(String name) {                                              //INCOMPLETE
        if ((name == null) ||
                (name.isEmpty()))
            throw new IllegalArgumentException("None of the arguments can be null or empty.");

        this.facadeAuthorization = new FacadeAuthorization();
        this.registFreelancer = new RegistFreelancer();
    }

    public ExternAlgorithmPasswordGenerator getAlgorithmPasswordGenerator() {
        return null;                                                            //WRITE CODE IF NECESSARY
    }

    public FacadeAuthorization getFacadeAutorization() {
        return facadeAuthorization;
    }

    @Override
    public ExternAlgorithmPasswordGenerator generatePassword(String name, String email) {
        return null;                                                            //WRITE CODE IF NECESSARY
    }

    /**
     * Returns Organization
     *
     * @return Organization
     */
    public Organization getRegistOrganization() {
        return organization;
    }

    /**
     * Returns RegistFreelancer
     * @return registFreelancer
     */
    public RegistFreelancer getRegistFreelancer(){
        return this.registFreelancer;
    }


}
