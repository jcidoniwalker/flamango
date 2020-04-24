package com.fau.flamango.dao;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.fau.flamango.MainActivity;
import com.fau.flamango.interfaces.DataReceived;
import com.fau.flamango.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MovieDAO {
    private String api_base = "https://api.themoviedb.org/3";
    private String api_key = "ff7c2e230c20460f35b90035e3b9ca11";

    public MovieDAO() {

    }

    public void getTrending(final DataReceived dr) {
        String url = api_base + "/trending/movie/day?api_key=" + api_key;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    ArrayList<Movie> tmp = new ArrayList<>();
                    JSONArray json_array = response.getJSONArray("results");
                    for(int i = 0; i < json_array.length(); i++) {
                        String title = (String) json_array.getJSONObject(i).get("title");
                        String description = (String) json_array.getJSONObject(i).get("overview");
                        String uri = (String) json_array.getJSONObject(i).get("poster_path");
                        Movie movie = new Movie(title, description, "http://image.tmdb.org/t/p/w185///" + uri);
                        tmp.add(movie);
                    }
                    dr.onDataReceived(tmp);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.toString());
            }
        });

        // Access the RequestQueue through your singleton class.
        MainActivity.getVolley().addToRequestQueue(jsonObjectRequest);
    }

    public void getMovieByTitle(String title, final DataReceived dr) {
        // https://api.themoviedb.org/3/search/movie?api_key=ff7c2e230c20460f35b90035e3b9ca11&language=en-US&query=texas%20chainsaw&page=1&include_adult=false
        String url = api_base + "/search/movie?api_key=" + api_key + "&query=" + title;
        System.out.println("requesting " + url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    ArrayList<Movie> tmp = new ArrayList<>();
                    JSONArray json_array = response.getJSONArray("results");
                    for(int i = 0; i < json_array.length(); i++) {
                        String title = (String) json_array.getJSONObject(i).get("title");
                        String description = (String) json_array.getJSONObject(i).get("overview");
                        System.out.println(title);
                        System.out.println(description);
                        String uri = json_array.getJSONObject(i).getString("poster_path");
                        System.out.println(uri);
                        Movie movie = new Movie(title, description, "http://image.tmdb.org/t/p/w185///" + uri);
                        tmp.add(movie);
                    }
                    dr.onDataReceived(tmp);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.toString());
            }
        });

        // Access the RequestQueue through your singleton class.
        MainActivity.getVolley().addToRequestQueue(jsonObjectRequest);
    }


    public void getMovieByPerson(String name, final DataReceived dr) {
        // https://api.themoviedb.org/3/search/movie?api_key=ff7c2e230c20460f35b90035e3b9ca11&language=en-US&query=texas%20chainsaw&page=1&include_adult=false
        String url = api_base + "/search/person?api_key=" + api_key + "&query=" + name;
        System.out.println("requesting " + url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    ArrayList<Movie> tmp = new ArrayList<>();
                    JSONArray json_array = response.getJSONArray("results").getJSONObject(0).getJSONArray("known_for");

                    for(int i = 0; i < json_array.length(); i++) {
                        String title = (String) json_array.getJSONObject(i).get("title");
                        String description = (String) json_array.getJSONObject(i).get("overview");
                        System.out.println(title);
                        System.out.println(description);
                        String uri = json_array.getJSONObject(i).getString("poster_path");
                        System.out.println(uri);
                        Movie movie = new Movie(title, description, "http://image.tmdb.org/t/p/w185///" + uri);
                        tmp.add(movie);
                    }
                    dr.onDataReceived(tmp);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.toString());
            }
        });

        // Access the RequestQueue through your singleton class.
        MainActivity.getVolley().addToRequestQueue(jsonObjectRequest);
    }
}
