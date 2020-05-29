package lapr2.isep.authorization;

import lapr2.isep.authorization.model.RegistUser;
import lapr2.isep.authorization.model.User;
import sun.security.util.Password;

public class FacadeAuthorization {

    private final RegistUser users = new RegistUser();

    public boolean registUser(String name, String email, Password password)
    {
        User user = this.users.novoUtilizador(name,email,password);
        return this.users.addUtilizador(user);
    }

}
