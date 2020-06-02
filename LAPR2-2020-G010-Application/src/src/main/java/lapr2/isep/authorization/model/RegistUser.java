package lapr2.isep.authorization.model;

import sun.security.util.Password;

import java.util.HashSet;
import java.util.Set;

public class RegistUser {

    private final Set<User> listUsers = new HashSet<User>();

    public User novoUtilizador(String name, String email, Password password)
    {
        return new User(name,email,password);
    }

    public boolean addUtilizador(User user)
    {
        if (user != null)
            return this.listUsers.add(user);
        return false;
    }

}
