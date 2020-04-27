package com.fau.flamango;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.fau.flamango.dao.UserDAO;
import com.fau.flamango.models.Movie;
import com.fau.flamango.ui.favorites.FavoritesFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * This class handles the dynamic CardView objects
 */
public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Movie> data;
    private Fragment frag = null;

    public Adapter(Context context, List<Movie> data) {
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
    }

    public Adapter(Context context, List<Movie> data, Fragment frag) {
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
        this.frag = frag;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.custom_view, parent, false);
        return new ViewHolder(view);
    }

    /**
     * This method sets the dynamically loaded content of a CardView
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Movie movie = data.get(position);
        holder.movie = movie;
        holder.textTitle.setText(movie.getTitle());
        holder.textDescripion.setText(movie.getDescription());
        Picasso.with(layoutInflater.getContext()).load(movie.getImageUri()).into(holder.imageView);

        holder.setcallback(frag); // This callback will allow the caller (Fragment) to know when the dynamic data has been displayed.

        if(SecondActivity.getUser().movieExistsInFavorites(movie)) {
            holder.toggle.setChecked(true);
        } else {
            holder.toggle.setChecked(false);
        }

        holder.toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!SecondActivity.getUser().movieExistsInFavorites(movie)) {
                    SecondActivity.getUser().addMovieToFavorites(movie);
                    holder.toggle.setChecked(true);
                } else {
                    SecondActivity.getUser().removeFromFavorites(movie);
                    holder.toggle.setChecked(false);
                }

                UserDAO userDAO = new UserDAO();
                userDAO.update(SecondActivity.getUser()); // Simple CRUD operation

                if(frag != null) {
                    ((FavoritesFragment) frag).trigger(); // A callback to tell the favorites fragment when to update the UI (after a change in favorites)
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * This class handles the Android setters of CardView content
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle, textDescripion;
        ImageView imageView;
        public Movie movie;
        private Fragment fragment = null;
        private ToggleButton toggle;

        public void setcallback(Fragment fragment) {
           this.fragment = (FavoritesFragment)fragment;
        }

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.cardTitle);
            textDescripion = itemView.findViewById(R.id.cardDescription);
            imageView = itemView.findViewById(R.id.imageView);
            toggle = itemView.findViewById(R.id.toggleButton);
        }
    }
}
