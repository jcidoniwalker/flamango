package com.fau.flamango.models;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String username;
    private String password;

    private ArrayList<Movie> favorites;

    public User(String username, String password) {
        favorites = new ArrayList();

        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Movie> getFavorites() {
        return favorites;
    }

    public void setFavorites(ArrayList<Movie> favorites) {
        this.favorites = favorites;
    }

    public boolean movieExistsInFavorites(Movie movie) {
        for(Movie m : favorites) {
            if(m.getTitle().equals(movie.getTitle())) {
                return true;
            }
        }
        return false;
    }

    public void addMovieToFavorites(Movie movie) {
        for(Movie m : favorites) {
            if(m.getTitle().equals(movie.getTitle()))
                return;
        }

        favorites.add(movie);
    }

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
