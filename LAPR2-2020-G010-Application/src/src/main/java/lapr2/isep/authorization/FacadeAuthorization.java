package lapr2.isep.authorization;

import lapr2.isep.authorization.model.RegistUser;
import lapr2.isep.authorization.model.User;
import lapr2.isep.authorization.model.UserSession;
import sun.security.util.Password;

public class FacadeAuthorization {

    private final RegistUser users = new RegistUser();
    private UserSession session = null;

    public boolean registUser(String name, String email, Password password)
    {
        User user = this.users.novoUtilizador(name,email,password);
        return this.users.addUtilizador(user);
    }

    /**
     * Returns current user session
     *
     * @return current session
     */
    public UserSession getCurrentSession() {
        return this.session;
    }
}
