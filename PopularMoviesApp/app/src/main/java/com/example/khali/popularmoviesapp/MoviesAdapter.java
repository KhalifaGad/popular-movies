package com.example.khali.popularmoviesapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.khali.popularmoviesapp.POJOs.Result;
import com.squareup.picasso.Picasso;

/**
 * Created by khali on 2/4/2017.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesAdapterViewHolder>{


    final private MoviesAdapterOnClickHandler mClickHandler;

    public MoviesAdapter(MoviesAdapterOnClickHandler handler){
        mClickHandler = handler;
    }

   // public  MoviesAdapter(){}

    public interface MoviesAdapterOnClickHandler{
        void clickHandling(Result results);
    }

    private Result[] adapterResults;

    public void setMoviesData(Result[] results){
        adapterResults = results;
        notifyDataSetChanged();
    }

    @Override
    public MoviesAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        int layoutId = R.layout.movies_item;
        boolean attachToRoot = false ;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutId,parent,attachToRoot);

        return new MoviesAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviesAdapterViewHolder holder, int position) {
        //Picasso.with(context).load(url).into(view);
        Picasso.with(holder.imageView.getContext())
                .load("http://image.tmdb.org/t/p/"+"w185"+adapterResults[position].getPoster_path())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if(adapterResults == null) {
            return 0;
        }
        return adapterResults.length;
    }

    public class MoviesAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public final ImageView imageView;

        public MoviesAdapterViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_movie);
            imageView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            mClickHandler.clickHandling(adapterResults[position]);
        }
    }
}
