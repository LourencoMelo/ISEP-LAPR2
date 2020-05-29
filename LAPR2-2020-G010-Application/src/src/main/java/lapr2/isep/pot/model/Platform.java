package lapr2.isep.pot.model;

import lapr2.isep.authorization.FacadeAuthorization;

public class Platform implements ExternAlgorithmPasswordGenerator {

    /**
     * FacadeAuthorization's initialization
     */
    private final FacadeAuthorization facadeAuthorization;

    /**
     * Organization's initialization
     */
    private Organization organization;

    public Platform(String name) {                                              //INCOMPLETE
        if ((name == null) ||
                (name.isEmpty()))
            throw new IllegalArgumentException("None of the arguments can be null or empty.");

        this.facadeAuthorization = new FacadeAuthorization();
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

    public Organization getRegistOrganization() {
        return organization;
    }
}
