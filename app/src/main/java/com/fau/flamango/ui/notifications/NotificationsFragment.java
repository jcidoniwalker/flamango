package com.fau.flamango.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fau.flamango.Adapter;
import com.fau.flamango.R;
import com.fau.flamango.SecondActivity;
import com.fau.flamango.dao.MovieDAO;
import com.fau.flamango.interfaces.DataReceived;
import com.fau.flamango.models.Movie;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    private Adapter adapter;
    private ArrayList<Movie> items;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setAdapter(new Adapter(getActivity().getApplicationContext(), new ArrayList<Movie>()));

        items = new ArrayList<Movie>();
        for(Movie movie : SecondActivity.getUser().getFavorites()) {
            items.add(movie);
            adapter = new Adapter(getActivity().getApplicationContext(), items, this);
            recyclerView.setAdapter(adapter);
        }

        return root;
    }

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
