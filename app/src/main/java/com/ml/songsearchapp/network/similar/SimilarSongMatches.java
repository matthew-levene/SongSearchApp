package com.ml.songsearchapp.network.similar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ml.songsearchapp.db.entities.similar.SimilarSong;

import java.util.List;

public class SimilarSongMatches {

    @SerializedName("track")
    @Expose
    public List<SimilarSong> track;

    public SimilarSongMatches(List<SimilarSong> track) {
        this.track = track;
    }

    public List<SimilarSong> getTrack() {
        return track;
    }
}