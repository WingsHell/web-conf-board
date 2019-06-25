package fr.sma.webconfboard.services.User;

import fr.sma.webconfboard.entities.User;
import fr.sma.webconfboard.repository.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) { this.userRepository = userRepository; }

    @Override
    public User getUserById(Long id) { return this.userRepository.getUserById(id);}

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList();

        userRepository.findAll().forEach(e -> userList.add(e));

        return userList;
    }

    @Override
    public User findByUsername(String username) {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(e -> users.add(e));

        for(User user : users) {
            if(user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User save(User user) { return userRepository.save(user);}

    @Override
    public Map<String, Boolean> delete(Long id) throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
        userRepository.delete(user);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }

    @Override
    public void deleteAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(e -> users.add(e));
        users.forEach(user -> userRepository.delete(user));
    }

    @Override
    public void updateUser(User user) { userRepository.save(user); }

    @Override
    public boolean isUserExist(User user) { return findByUsername(user.getUsername()) != null; }
}


























