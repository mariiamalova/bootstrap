package com.example.services;
import com.example.dao.UserDAOImpl;
import com.example.model.Role;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl  {

    private final UserDAOImpl userDAO;

    @Autowired
    public UserServiceImpl(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional
    public void addUser(User user) {
        userDAO.addUser(user);
    }

    @Transactional
    public void updateUser(User user) {
        userDAO.addUser(user);
    }

    @Transactional
    public void removeUser(int id) {
        userDAO.removeUser(id);
    }

    public User getUserById(int id) {
        Optional<User> opt = userDAO.getUserById(id);
        if (opt.isEmpty()) {
            return null;
        } else {
            return opt.get();
        }

    }

    public List<User> listUsers() {
       return userDAO.listUsers();
    }

    public Optional<User> findByUsername(String username) {
      return userDAO.findByUsername(username);
    }

    public User getUserByNameWithRoles(String username) {
        return userDAO.getUserByNameWithRoles(username);
    }
}
