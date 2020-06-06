package lapr2.isep.authorization;

import lapr2.isep.authorization.model.RegistUser;
import lapr2.isep.authorization.model.User;
import lapr2.isep.authorization.model.UserSession;
import sun.security.util.Password;

import java.io.IOException;
import java.io.Serializable;

public class FacadeAuthorization implements Serializable {

    private final RegistUser users = new RegistUser();
    private final UserSession session = null;

    public boolean registUser(String name, String email) throws IOException {
        User user = this.users.novoUtilizador(name,email);
        return this.users.addUtilizador(user);
    }

    /*
     * Returns current user session
     *
     * @return current session
     */
    public UserSession getCurrentSession() {
        return this.session;
    }
}
