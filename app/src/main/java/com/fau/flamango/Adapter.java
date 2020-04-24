package com.fau.flamango;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = data.get(position);
        holder.item = movie;
        holder.textTitle.setText(movie.getTitle());
        holder.setcallback(frag);
        holder.textDescripion.setText(movie.getDescription());
        Picasso.with(layoutInflater.getContext()).load(movie.getImageUri()).into(holder.imageView);
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

        public void setcallback(Fragment fragment) {
           this.fragment = (NotificationsFragment)fragment;
        }

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!SecondActivity.getUser().movieExistsInFavorites(item)) {
                        SecondActivity.getUser().addMovieToFavorites(item);
                    } else {
                        SecondActivity.getUser().removeFromFavorites(item);
                    }

                    userDAO.update(SecondActivity.getUser());
                    if(fragment != null) {
                        ((NotificationsFragment) fragment).trigger();
                        System.out.println("im trying");
                    }
                }
            });

            textTitle = itemView.findViewById(R.id.cardTitle);
            textDescripion = itemView.findViewById(R.id.cardDescription);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
