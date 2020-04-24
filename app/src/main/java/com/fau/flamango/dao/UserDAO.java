package com.fau.flamango.dao;

import com.fau.flamango.models.Movie;
import com.fau.flamango.models.User;

import java.util.HashMap;

public class UserDAO {
    public static HashMap<String, User> users = new HashMap<>();

    public UserDAO() {
        users.put("admin", new User("admin", "admin"));
    }

    public User login(String username, String password) {
        if(users.containsKey(username)) {
            if(users.get(username).getPassword().equals(password)) {
                return users.get(username);
            }
        }
        return null;
    }

    public User create(String username, String password) {
        User user = new User(username, password);
        users.put(username, user);
        return user;
    }

    public boolean exists(String username) {
        return (users.containsKey(username)) ? true : false;
    }

    public void update(User user) {
        System.out.println("user is: " + user.getUsername());
        User _user = users.get(user.getUsername());
        System.out.println("it is..again " + _user.getUsername());
        _user.setFavorites(user.getFavorites());
    }
}
