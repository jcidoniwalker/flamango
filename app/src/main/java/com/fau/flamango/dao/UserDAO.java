package com.fau.flamango.dao;

import com.fau.flamango.models.Movie;
import com.fau.flamango.models.User;

import java.util.HashMap;

public class UserDAO {
    public static HashMap<String, User> users = new HashMap<>();

    public UserDAO() {
        /* For debugging purposes */
        if(!users.containsKey("admin")) {
            users.put("admin", new User("admin", "admin"));
        }
    }

    /**
     * Logs in a user
     * @param username
     * @param password
     * @return User
     */
    public User login(String username, String password) {
        if(users.containsKey(username)) {
            if(users.get(username).getPassword().equals(password)) {
                return users.get(username);
            }
        }
        return null;
    }

    /**
     * Creates a user.
     * @param username
     * @param password
     * @return User
     */
    public User create(String username, String password) {
        User user = new User(username, password);
        users.put(username, user);
        return user;
    }

    /**
     * Checks if a username exists in the database.
     * @param username
     * @return
     */
    public boolean exists(String username) {
        return (users.containsKey(username)) ? true : false;
    }

    /**
     * Updates the information saved about a user.
     */
    public void update(User user) {
        User _user = users.get(user.getUsername());
        _user.setFavorites(user.getFavorites());
    }
}
