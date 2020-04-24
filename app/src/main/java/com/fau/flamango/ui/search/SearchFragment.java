package com.fau.flamango.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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

public class SearchFragment extends Fragment {

    private Adapter adapter;
    private ArrayList<Movie> items;
    private RecyclerView recyclerView;
    private MovieDAO movieDAO;

    private EditText search_box;
    private RadioGroup radiogroup;
    private RadioButton radio_movie;
    private RadioButton radio_person;
    private RadioButton radio_genre;
    private Button search_button;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search, container, false);

        search_box = root.findViewById(R.id.editText3);
        radiogroup = root.findViewById(R.id.radiogroup1);
        radio_movie = root.findViewById(R.id.radioButton);
        radio_person = root.findViewById(R.id.radioButton2);
        search_button = root.findViewById(R.id.button3);

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(getContext().INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                    // TODO: handle exception
                }

                switch(radiogroup.getCheckedRadioButtonId()) {
                    case R.id.radioButton: // movie title
                        items.clear();
                        displayMovies();
                        break;

                    case R.id.radioButton2: // director
                        items.clear();
                        displayPerson();
                        break;
                }
            }
        });

        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.setAdapter(new Adapter(root.getContext(), new ArrayList<Movie>()));

        items = new ArrayList<Movie>();

        movieDAO = new MovieDAO();

        return root;
    }

    public void displayMovies() {
        String title = search_box.getText().toString();
        title = title.trim();
        title.replaceAll("\\s", "%20");

        movieDAO.getMovieByTitle(title, new DataReceived() {
            @Override
            public void onDataReceived(ArrayList<Movie> movies) {
                for(Movie movie : movies) {
                    items.add(movie);
                    System.out.println(movie.getTitle());
                }

                adapter = new Adapter(getActivity().getApplicationContext(), items);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    public void displayPerson() {
        String name = search_box.getText().toString();
        name = name.trim();
        name.replaceAll("\\s", "%20");

        movieDAO.getMovieByPerson(name, new DataReceived() {
            @Override
            public void onDataReceived(ArrayList<Movie> movies) {
                for(Movie movie : movies) {
                    items.add(movie);
                    System.out.println(movie.getTitle());
                }

                adapter = new Adapter(getActivity().getApplicationContext(), items);
                recyclerView.setAdapter(adapter);
            }
        });
    }
}
