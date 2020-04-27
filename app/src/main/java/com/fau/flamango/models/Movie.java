package com.fau.flamango.models;

import java.io.Serializable;

/**
 * This class models a Movie.
 */
public class Movie implements Serializable {
    private String title;
    private String description;
    private String uri; // This is the uri of the poster image

    public Movie(String title, String description, String uri) {
        this.title = title;
        this.description = description;
        this.uri = uri;
    }

    /**
     * Gets the Movie title
     * @return String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the Movie description
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the poster image URI.
     * @return String
     */
    public String getImageUri() { return uri; }
}
