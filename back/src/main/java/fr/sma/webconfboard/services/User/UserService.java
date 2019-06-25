package fr.sma.webconfboard.services.User;

import fr.sma.webconfboard.entities.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    User getUserById(Long id);

    User findByUsername(String noCliSma);

    List<User> getAllUsers();

    User save(User user);

    Map<String, Boolean> delete(Long id);

    void deleteAllUsers();

    void updateUser(User user);

    boolean isUserExist(User user);

}
