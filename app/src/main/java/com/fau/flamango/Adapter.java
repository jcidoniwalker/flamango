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
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.fau.flamango.dao.UserDAO;
import com.fau.flamango.models.Movie;
import com.fau.flamango.ui.notifications.NotificationsFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

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

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Movie movie = data.get(position);
        holder.textTitle.setText(movie.getTitle());
        holder.setcallback(frag);
        holder.textDescripion.setText(movie.getDescription());
        Picasso.with(layoutInflater.getContext()).load(movie.getImageUri()).into(holder.imageView);
        holder.item = movie;

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
                userDAO.update(SecondActivity.getUser());

                if(frag != null) {
                    ((NotificationsFragment) frag).trigger();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle, textDescripion;
        ImageView imageView;
        public Movie item;
        private UserDAO userDAO = new UserDAO();
        private Fragment fragment = null;
        private boolean isFavorite = false;
        private ToggleButton toggle;

        public void setcallback(Fragment fragment) {
           this.fragment = (NotificationsFragment)fragment;
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
