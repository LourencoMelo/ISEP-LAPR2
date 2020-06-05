package lapr2.isep.authorization.model;

import sun.security.util.Password;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class RegistUser implements Serializable {

    private final Set<User> listUsers = new HashSet<User>();

    public User novoUtilizador(String name, String email) throws IOException {
        return new User(name,email);
    }

    public boolean addUtilizador(User user)
    {
        if (user != null)
            return this.listUsers.add(user);
        return false;
    }

}
