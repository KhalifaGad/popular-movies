package com.example.khali.popularmoviesapp.utilities;

import android.os.AsyncTask;
import android.view.View;

import com.example.khali.popularmoviesapp.MainActivity;
import com.example.khali.popularmoviesapp.POJOs.Result;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

/**
 * Created by khali on 2/8/2017.
 */

public class MoviesQueryTask extends AsyncTask<String , Void , Result[]> {

    @Override
    protected Result[] doInBackground(String... params) {
        String sortingType = params[0];
        URL sortingUrl = NetworkUtils.buildSortingUrl(sortingType);
        String jsonStr = NetworkUtils.getResponseFromHttpURL(sortingUrl);
        Result[] results =null;
        if (jsonStr != null) {
            try {

                JSONObject jsonObject = new JSONObject(jsonStr);

                JSONArray jsonArray = jsonObject.getJSONArray("results");

                results = new Result[jsonArray.length()];

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject tmp = jsonArray.getJSONObject(i);
                    results[i] = setData(tmp);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    @Override
    protected void onPostExecute(Result[] results) {
        super.onPostExecute(results);
        MainActivity.mMoviesAdapter.setMoviesData(results);
        MainActivity.mRecyclerView.setVisibility(View.VISIBLE);
    }
    private Result setData(JSONObject tmp){

        Result result = new Result();

        try {
            result.setPoster_path(tmp.getString("poster_path"));
            result.setAdult(tmp.getBoolean("adult"));
            result.setOverview(tmp.getString("overview"));
            result.setRelease_date(tmp.getString("release_date"));

            JSONArray jsonArrOfGenreIds = tmp.getJSONArray("genre_ids");
            int[] genreIdsArr = new int[jsonArrOfGenreIds.length()];
            for(int j=0;j<jsonArrOfGenreIds.length();j++){
                genreIdsArr[j]=jsonArrOfGenreIds.getInt(j);
            }
            result.setGenre_ids(genreIdsArr);
            result.setId(tmp.getInt("id"));
            result.setOriginal_title(tmp.getString("original_title"));
            result.setOriginal_language(tmp.getString("original_language"));
            result.setTitle(tmp.getString("title"));
            result.setBackdrop_path(tmp.getString("backdrop_path"));
            result.setPopularity(tmp.getLong("popularity"));
            result.setVote_count(tmp.getInt("vote_count"));
            result.setVideo(tmp.getBoolean("video"));
            result.setVote_average(tmp.getLong("vote_average"));
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        return  result;
    }
}
