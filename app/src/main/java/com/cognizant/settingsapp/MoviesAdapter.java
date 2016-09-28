package com.cognizant.settingsapp;

/**
 * Created by 538015 on 9/28/2016.
 */

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import jp.wasabeef.recyclerview.animators.holder.AnimateViewHolder;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private Context context;
    private List<Movie> moviesList;

    public class MyViewHolder extends AnimateViewHolder {
        public TextView title, year, genre;
        public LinearLayout boxWrapperLinearLayout;
        public LinearLayout boxChildWrapperLinearLayout;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);
            boxWrapperLinearLayout = (LinearLayout) view.findViewById(R.id.boxWrapperLinearLayout);
            boxChildWrapperLinearLayout = (LinearLayout) view.findViewById(R.id.boxChildWrapperLinearLayout);
            //year = (TextView) view.findViewById(R.id.year);

        }

        @Override
        public void animateRemoveImpl(ViewPropertyAnimatorListener listener) {
            ViewCompat.animate(itemView)
                    .translationY(-itemView.getHeight() * 0.3f)
                    .alpha(0)
                    .setDuration(300)
                    .setListener(listener)
                    .start();
        }

        @Override
        public void preAnimateAddImpl() {
            ViewCompat.setTranslationY(itemView, -itemView.getHeight() * 0.3f);
            ViewCompat.setAlpha(itemView, 0);
        }

        @Override
        public void animateAddImpl(ViewPropertyAnimatorListener listener) {
            ViewCompat.animate(itemView)
                    .translationY(0)
                    .alpha(1)
                    .setDuration(300)
                    .setListener(listener)
                    .start();
        }
    }


    public MoviesAdapter(List<Movie> moviesList, Context context) {
        this.moviesList = moviesList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    public void remove(int position) {
        moviesList.remove(position);
        notifyItemRemoved(position);
    }

    public void add(Movie movie, int position) {
        moviesList.add(position, movie);
        notifyItemInserted(position);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Movie movie = moviesList.get(position);
        holder.title.setText(movie.getTitle());
        holder.genre.setText(movie.getGenre());
        int year = Integer.parseInt(movie.getYear());

        if (year % 2 == 0) {
            holder.boxChildWrapperLinearLayout.setBackgroundColor(context.getResources().getColor(R.color.lightPink));
            holder.boxWrapperLinearLayout.setGravity(Gravity.LEFT);
            holder.boxChildWrapperLinearLayout.setGravity(Gravity.LEFT);
        } else {
            holder.boxChildWrapperLinearLayout.setBackgroundColor(context.getResources().getColor(R.color.lightBlue));
            holder.boxWrapperLinearLayout.setGravity(Gravity.RIGHT);
            holder.boxChildWrapperLinearLayout.setGravity(Gravity.RIGHT);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 2, 0, 0);
            holder.boxChildWrapperLinearLayout.setLayoutParams(params);
        }
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
