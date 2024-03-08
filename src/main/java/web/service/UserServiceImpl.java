package web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;
import web.repository.UserRepository;


import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }


    @Transactional
    public void createUser(User user) {
        userRepository.createUser(user);
    }

    @Transactional
    public void updateUser(long id, User user) {
        userRepository.updateUser(user);
    }

    @Override
    @Transactional
    public User readUser(long id) {
        return userRepository.readUser(id);
    }

    @Override
    @Transactional
    public User deleteUser(long id) {
        User user = null;
        try {
            user = userRepository.deleteUser(id);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return user;
    }
}
