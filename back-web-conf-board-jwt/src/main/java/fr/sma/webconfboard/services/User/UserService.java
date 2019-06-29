package fr.sma.webconfboard.services.User;

import fr.sma.webconfboard.entities.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    User findById(Long id);

    User findByUsername(String username);

    List<User> findAll();

    User save(User user);

    void delete(Long id);

    void deleteAllUsers();

    void updateUser(User user);

    boolean isUserExist(User user);

}
