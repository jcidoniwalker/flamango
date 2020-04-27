package com.fau.flamango.interfaces;

import com.fau.flamango.models.Movie;

import java.util.ArrayList;

/**
 * This interface is to declare a callback method for data when it is received from the network.
 */
public interface DataReceived {
    void onDataReceived(ArrayList<Movie> movies);
}
