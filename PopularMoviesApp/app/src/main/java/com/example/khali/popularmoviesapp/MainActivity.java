package com.example.khali.popularmoviesapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.khali.popularmoviesapp.POJOs.Result;
import com.example.khali.popularmoviesapp.utilities.MoviesQueryTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.MoviesAdapterOnClickHandler {

    public static MoviesAdapter mMoviesAdapter ;
    @BindView(R.id.rv_movies) RecyclerView mRecyclerView;
    @BindView(R.id.my_toolbar) Toolbar mToolbar;
    String sortingType= "popularity.asc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        switch (isOnline()) {
            case 1 :
            new MoviesQueryTask().execute(sortingType);
                break;
            case 0:
                Context context = this;
                String ErrorMessage ="No Internet Connection !";
                Toast.makeText(context,ErrorMessage,Toast.LENGTH_LONG).show();
        }
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        mMoviesAdapter = new MoviesAdapter(this);
        mRecyclerView.setAdapter(mMoviesAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        setSupportActionBar(mToolbar);
    }
    public byte isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return ((byte)(netInfo != null && netInfo.isConnectedOrConnecting() ? 1 : 0));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        switch (isOnline()) {
            case 1:
                getMenuInflater().inflate(R.menu.menu, menu);
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.popularity_sorting :
                sortingType="popularity.asc";
                new MoviesQueryTask().execute(sortingType);
                return true;
            case R.id.rating_sorting :
                sortingType = "vote_average.asc";
                new MoviesQueryTask().execute(sortingType);
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void clickHandling(Result results) {
        Context context = this;
        Class destinationClass = MovieDetails.class;
        Intent intentToDetailsActivity = new Intent(context ,destinationClass );
        intentToDetailsActivity.putExtra("custom_object",results);
        startActivity(intentToDetailsActivity);
    }



}
