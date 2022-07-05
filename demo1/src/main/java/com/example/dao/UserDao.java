package com.example.dao;


import com.example.model.User;


import java.util.List;
import java.util.Optional;


public interface UserDao {
   public void addUser (User user);
   public void updateUser(User user);
   public void removeUser(int id);
   public Optional<User> getUserById(int id);
   public List<User> listUsers();
   public Optional<User> findByUsername(String username);
   public User getUserByNameWithRoles (String name);
}
