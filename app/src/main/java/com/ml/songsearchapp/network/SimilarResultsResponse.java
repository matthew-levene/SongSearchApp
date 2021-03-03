package com.ml.songsearchapp.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ml.songsearchapp.domain.SimilarSongMatches;

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
