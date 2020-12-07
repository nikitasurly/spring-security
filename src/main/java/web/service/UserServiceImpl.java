package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDAO;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Transactional
    @Override
    public void add(User user) {
        userDAO.add(user);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        userDAO.delete(id);
    }

    @Transactional
    @Override
    public void edit(User user) {
        userDAO.edit(user);
    }

    @Transactional
    @Override
    public List<User> listUsers() {
        return userDAO.listUsers();
    }

    @Override
    public User find(Long id) {
        return userDAO.find(id);
    }

    @Override
    public User getUserByLogin( ) {
        return userDAO.getUserByLogin(getCurrentLogin());
    }

    public String getCurrentLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
