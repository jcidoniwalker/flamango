package com.fau.flamango.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class models a User.
 */
public class User implements Serializable {
    private String username;
    private String password;

    private ArrayList<Movie> favorites;

    public User(String username, String password) {
        favorites = new ArrayList();
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the username.
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the password.
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns a list of favorite movie objects.
     * @return ArrayList<Movie></Movie>
     */
    public ArrayList<Movie> getFavorites() {
        return favorites;
    }

    /**
     * Sets a list of favorite movie objects.
     */
    public void setFavorites(ArrayList<Movie> favorites) {
        this.favorites = favorites;
    }

    /**
     * Checks if a movie exists within the favorites list. Checks if two objects are equivalent using the Movie title.
     * @return Boolean
     */
    public boolean movieExistsInFavorites(Movie movie) {
        for(Movie m : favorites) {
            if(m.getTitle().equals(movie.getTitle())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a movie to the favorites list
     */
    public void addMovieToFavorites(Movie movie) {
        for(Movie m : favorites) {
            if(m.getTitle().equals(movie.getTitle()))
                return;
        }

        favorites.add(movie);
    }

    /**
     * Removes a movie from the favorites list.
     */
    public void removeFromFavorites(Movie movie) {
        Movie tmp = null;
        for(Movie m : favorites) {
            if(m.getTitle().equals(movie.getTitle())) {
                tmp = m;
            }
        }

        if(tmp != null) {
            favorites.remove(tmp);
        }
    }
}
