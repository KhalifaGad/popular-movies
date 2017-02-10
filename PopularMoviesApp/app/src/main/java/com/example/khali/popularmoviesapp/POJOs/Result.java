package com.example.khali.popularmoviesapp.POJOs;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by khali on 2/4/2017.
 */

public class Result implements Parcelable {

    private long vote_average;

    private String backdrop_path;

    private boolean adult;

    private int id;

    private String title;

    private String overview;

    private String original_language;

    private int[] genre_ids;

    private String release_date;

    private String original_title;

    private int vote_count;

    private String poster_path;

    private boolean video;

    private long popularity;

    public Result(){}

    public long getVote_average() {
        return vote_average;
    }

    public void setVote_average(long vote_average) {
        this.vote_average = vote_average;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public boolean getAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public int[] getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(int[] genre_ids) {
        this.genre_ids = genre_ids;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public String getPoster_path() {
        return this.poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public boolean getVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public long getPopularity() {
        return popularity;
    }

    public void setPopularity(long popularity) {
        this.popularity = popularity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeLong(vote_average);
        dest.writeString(backdrop_path);
        dest.writeByte((byte) (adult ? 1 : 0));
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(overview);
        dest.writeString(original_language);
        dest.writeIntArray(genre_ids);
        dest.writeString(release_date);
        dest.writeString(original_title);
        dest.writeInt(vote_count);
        dest.writeString(poster_path);
        dest.writeByte((byte) (video ? 1 : 0));
        dest.writeLong(popularity);

    }

    public static final Parcelable.Creator<Result> CREATOR = new Parcelable.Creator<Result>(){

        @Override
        public Result createFromParcel(Parcel source) {
            return new Result(source);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };

    public Result(Parcel source){

        vote_average = source.readLong();
        backdrop_path= source.readString();
        //adult == true if != 0
        adult = source.readByte() != 0 ;
        id = source.readInt();
        title = source.readString();
        overview = source.readString();
        original_language = source.readString();
        genre_ids = source.createIntArray();
        release_date = source.readString();
        original_title = source.readString();
        vote_count = source.readInt();
        poster_path = source.readString();
        //video == true if != 0
        video = source.readByte() != 0;
        popularity = source.readLong();

    }
}
