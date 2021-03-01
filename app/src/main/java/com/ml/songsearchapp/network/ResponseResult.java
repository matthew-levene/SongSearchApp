package com.ml.songsearchapp.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ml.songsearchapp.db.entities.SongMatches;
public class ResponseResult {
    @SerializedName("trackmatches")
    @Expose
    public SongMatches songMatches;
}
