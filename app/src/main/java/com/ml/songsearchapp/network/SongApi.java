package com.ml.songsearchapp.network;

import androidx.lifecycle.LiveData;

import com.ml.songsearchapp.BuildConfig;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SongApi {
    String search = "2.0/";
    String apiKey = BuildConfig.API_KEY;
    String baseUrl = "https://ws.audioscrobbler.com/";
    String responseFormat = "json";
    String method = "track.search";

    @GET(search)
    LiveData<ResponseResult> searchSongName(
            @Query("method") String method,
            @Query("track") String track,
            @Query("api_key") String apiKey,
            @Query("format") String format
    );
}

