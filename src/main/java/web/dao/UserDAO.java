package web.dao;

import web.model.User;

import java.util.List;

public interface UserDAO {
    User getUserByLogin(String login);
    void add(User user);
    void delete(Long id);
    void edit(User user);
    List<User> listUsers();
    User find(Long id);
}
