package com.example.khali.popularmoviesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khali.popularmoviesapp.POJOs.Result;
import com.squareup.picasso.Picasso;

public class MovieDetails extends AppCompatActivity {

    TextView mReleaseDateTV;
    TextView mVoteAvgTV;
    TextView mOverviewTV;
    TextView mOriginal_titleTV;
    ImageView mImageView ;
    Long voteAvg ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Result result = getIntent().getParcelableExtra("custom_object");

        mReleaseDateTV = (TextView) findViewById(R.id.tv_release_date);
        mVoteAvgTV = (TextView) findViewById(R.id.tv_vote_average);
        mOverviewTV = (TextView) findViewById(R.id.tv_overview);
        mImageView = (ImageView) findViewById(R.id.iv_movie_img);
        mOriginal_titleTV = (TextView) findViewById(R.id.tv_original_name);
        voteAvg =result.getVote_average();

        mReleaseDateTV.setText(result.getRelease_date());
        mVoteAvgTV.setText(voteAvg.toString()+"/10");
        mOriginal_titleTV.setText(result.getOriginal_title());
        mOverviewTV.setText(result.getOverview());
        Picasso.with(mImageView.getContext())
                .load("http://image.tmdb.org/t/p/"+"w185"+result.getPoster_path())
                .into(mImageView);

    }
}
