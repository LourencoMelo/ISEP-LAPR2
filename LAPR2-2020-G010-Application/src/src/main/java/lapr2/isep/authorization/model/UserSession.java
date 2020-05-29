package lapr2.isep.authorization.model;

public class UserSession {

    private User user = null;

    private UserSession() {

    }

    public void SessaoUtilizador(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Argument can't be null.");
        }
        this.user = user;
    }

    public void doLogout() {
        this.user = null;
    }

    public boolean isLoggedIn() {
        return this.user != null;
    }

    /*public boolean isLoogedInWithRole(String role) {
        if (isLoggedIn()) {
            return this.user.hasRole(role);                                    ARE WE GOING TO USE ROLES???
        }
        return false;
    }

     */

    public String getUserName() {
        if (isLoggedIn()) {
            return this.user.getName();
        }
        return null;
    }

    public String getUserEmail() {
        if (isLoggedIn()) {
            return this.user.getEmail();
        }
        return null;
    }

}
