package fr.sma.webconfboard.repository.User;

import fr.sma.webconfboard.entities.User;

import java.util.ArrayList;
import java.util.List;

abstract class UserRepositoryImpl implements UserRepository {

    private UserRepository userRepository;

    /*@Override
    public User getUserById(Long id) {
        List<User> userList = new ArrayList();
        userRepository.findAll().forEach((e -> userList.add(e)));
        for(User u : userList) {
            if( u.getId() == id ) return u;
        }
        return null;
    }*/

    @Override
    public User findByUsername(String username) {
        List<User> userList = new ArrayList();
        userRepository.findAll().forEach((e -> userList.add(e)));
        for(User u : userList) {
            if( u.getUsername() == username ) return u;
        }
        return null;
    }
}

