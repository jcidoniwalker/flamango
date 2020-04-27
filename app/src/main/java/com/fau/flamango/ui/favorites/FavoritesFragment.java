package com.fau.flamango.ui.favorites;

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
import com.fau.flamango.SecondActivity;
import com.fau.flamango.models.Movie;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment {

    private Adapter adapter;
    private ArrayList<Movie> items;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favorites, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setAdapter(new Adapter(getActivity().getApplicationContext(), new ArrayList<Movie>()));

        items = new ArrayList<Movie>();
        /* Retrieve the list of favorited movies from the "favorites" list in the User object */
        for(Movie movie : SecondActivity.getUser().getFavorites()) {
            items.add(movie);
            adapter = new Adapter(getActivity().getApplicationContext(), items, this); // Pass the list of objects to use for dynamic CardView content
            recyclerView.setAdapter(adapter);
        }

        return root;
    }

    /**
     * Called when a favorited movie has been unfavorited and the UI must update.
     */
    public void trigger() {
        for(Movie m : SecondActivity.getUser().getFavorites()) {
            System.out.println(m.getTitle());
        }

        items = new ArrayList<Movie>();
        for(Movie movie : SecondActivity.getUser().getFavorites()) {
            items.add(movie);
        }

        adapter = new Adapter(getActivity().getApplicationContext(), items, this);
        recyclerView.setAdapter(adapter);
    }
}
