package com.example.springcrudexample2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public class UserService {

    private UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public int save(User user){
        return userDAO.save(user);
    }

    public List<User> getAll(){
        return userDAO.getAll();
    }

    public User getOne(int id){
        return userDAO.getOne(id);
    }

    public int edit(User user){
        return userDAO.edit(user);
    }

    public void delete(int id){
        userDAO.delete(id);
    }
}
