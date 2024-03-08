package web.service;



import web.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User readUser(long id);

    User deleteUser(long parseUnsignedInt);


    void createUser(User user);

    void updateUser(long id, User user);
}
