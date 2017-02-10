package com.example.khali.popularmoviesapp.utilities;

import android.net.Uri;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by khali on 2/2/2017.
 */

public class NetworkUtils {

    private static final String TMDB_BASE_URL = "https://api.themoviedb.org/3/";

    private static final String api_key = "my_api_key";

    private static final String API_KEY_PARAM = "api_key";

    private static final String sorting_param ="sort_by";

    public static URL buildSortingUrl(String sortingType){

        Uri builtUri = Uri.parse(TMDB_BASE_URL).buildUpon()
                .appendEncodedPath("discover/movie")
                .appendQueryParameter(API_KEY_PARAM,api_key)
                .appendQueryParameter(sorting_param,sortingType)
                .build();

        URL url = null ;

        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url ;
    }

    public static String getResponseFromHttpURL(URL url){

        String response = null;

        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            response =  convertStreamToString(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    private static String convertStreamToString(InputStream inputStream){
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();

        String line ;
        try {
            while((line = reader.readLine()) != null){
                stringBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }
}
