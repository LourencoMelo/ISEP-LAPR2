package lapr2.isep.authorization.model;

public class UserSession {

    /**
     * Object of the class user inicialized as a null.
     */
    private User user = null;

    private UserSession() {

    }

    /**
     * Inicializes the session of the user that is running the program.
     *
     * @param user object user.
     */
    public void UserSession(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Argument can't be null.");
        }
        this.user = user;
    }

    /**
     * Logout of the user´s session. Takes the user´s object and turns it to null.
     *
     */
    public void doLogout() {
        this.user = null;
    }

    /**
     * Verifies if there is an User logged in.
     *
     * @return boolean (true or false) if there is a user logged in
     */
    public boolean isLoggedIn() {
        return this.user != null;
    }

    /*public boolean isLoogedInWithRole(String role) {
        if (isLoggedIn()) {
            return this.user.hasRole(role);                                    ARE WE GOING TO USE ROLES???
        }
        return false;
    }



    public String getUserName() {
        if (isLoggedIn()) {
            return this.user.getName();
        }
        return null;
    }

    /**
     * Returns the User's email
     *
     * @return User's email
     */
    public String getUserEmail() {
        if (isLoggedIn()) {
            return this.user.getEmail();
        }
        return null;
    }
}
