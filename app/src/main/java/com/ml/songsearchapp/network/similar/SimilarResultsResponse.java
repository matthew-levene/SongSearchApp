package com.ml.songsearchapp.network.similar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SimilarResultsResponse  {

    @SerializedName("similartracks")
    @Expose
    private SimilarSongMatches similartracks;

    public SimilarSongMatches getSimilartracks() {
        return similartracks;
    }

    public void setSimilartracks(SimilarSongMatches similartracks) {
        this.similartracks = similartracks;
    }

}
