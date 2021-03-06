package com.fau.flamango.ui.trending;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fau.flamango.Adapter;
import com.fau.flamango.R;
import com.fau.flamango.dao.MovieDAO;
import com.fau.flamango.interfaces.DataReceived;
import com.fau.flamango.models.Movie;

import java.util.ArrayList;

public class TrendingFragment extends Fragment {

    private Adapter adapter;
    private ArrayList<Movie> items;
    private RecyclerView recyclerView;

    private MovieDAO movieDAO;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_trending, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setAdapter(new Adapter(getActivity().getApplicationContext(), new ArrayList<Movie>()));

        items = new ArrayList<Movie>();
        movieDAO = new MovieDAO();

        /* The list of trending movies will be retrieved via a callback method, declared by the DataReceived interface */
        movieDAO.getTrending(new DataReceived() {
            @Override
            public void onDataReceived(ArrayList<Movie> movies) {
                for(Movie movie : movies) {
                    items.add(movie);
                }

                adapter = new Adapter(getActivity().getApplicationContext(), items); // Pass the list of objects to use for dynamic CardView content
                recyclerView.setAdapter(adapter);
            }
        });


        return root;
    }
}
