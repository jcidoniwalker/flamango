package com.fau.flamango.models;

import java.io.Serializable;

public class Movie implements Serializable {
    public String title;
    public String description;
    public String uri;

    public Movie(String title, String description, String uri) {
        this.title = title;
        this.description = description;
        this.uri = uri;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUri() { return uri; }
}
