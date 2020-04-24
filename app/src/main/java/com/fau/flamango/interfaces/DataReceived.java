package com.fau.flamango.interfaces;

import com.fau.flamango.models.Movie;

import java.util.ArrayList;

public interface DataReceived {
    void onDataReceived(ArrayList<Movie> movies);
}
