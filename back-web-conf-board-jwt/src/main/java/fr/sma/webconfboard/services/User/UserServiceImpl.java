package fr.sma.webconfboard.services.User;

import fr.sma.webconfboard.entities.User;
import fr.sma.webconfboard.repository.User.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service(value="userService")
public class UserServiceImpl implements UserDetailsService, UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;


    private List<SimpleGrantedAuthority> getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ADMIN"),new SimpleGrantedAuthority("MEMBRE"));
    }

    /*

    @Autowired
    public UserServiceImpl(UserRepository userRepository) { this.userRepository = userRepository; }

    */

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());
    }

    @Override
    public User findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.isPresent() ? optionalUser.get() : null;
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList();
        userRepository.findAll().iterator().forEachRemaining(userList::add);
        return userList;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User save(User user) {
        User newUser = new User(
                    user.getNom(),
                    user.getPrenom(),
                    user.getUsername(),
                    user.getEmail(),
                    bcryptEncoder.encode(user.getPassword()),
                    user.getRole());
                    if(newUser.getRole() == null) {
                         newUser.setRole("MEMBRE");
                    }
        return userRepository.save(newUser);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void deleteAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(e -> users.add(e));
        users.forEach(user -> userRepository.delete(user));
    }

    @Override
    public void updateUser(User user) {
        User userUpdated = findById(user.getId());
        if(userUpdated != null) {
            BeanUtils.copyProperties(user, userUpdated, "password");
        }
        userRepository.save(user);
    }

    @Override
    public boolean isUserExist(User user) { return findByUsername(user.getUsername()) != null; }
}


























