package com.ml.songsearchapp.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ml.songsearchapp.domain.Artist;

import java.io.Serializable;

public class SimilarSong implements Serializable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("artist")
    @Expose
    private Artist artist;

    @SerializedName("url")
    private String url;

    @SerializedName("playcount")
    private int playcount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPlaycount() {
        return playcount;
    }

    public void setPlaycount(int playcount) {
        this.playcount = playcount;
    }
}
