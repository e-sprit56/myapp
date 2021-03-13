package pl.coderslab.myapp.user.service;

import pl.coderslab.myapp.user.model.User;

public interface UserService {

    User findUserByUsername(String username);

    void saveUser(User user);
}
