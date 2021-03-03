package com.ml.songsearchapp.network;

import com.ml.songsearchapp.BuildConfig;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SongApi {
    String endpoint = "2.0/";
    String apiKey = BuildConfig.API_KEY;
    String baseUrl = "https://ws.audioscrobbler.com/";
    String responseFormat = "json";
    String search = "track.search";
    String searchSimilar = "track.getSimilar";

    @GET(endpoint)
    Call<ResultsResponse> searchSongName(
            @Query("method") String method,
            @Query("track") String track,
            @Query("api_key") String apiKey,
            @Query("format") String format
    );


    @GET(endpoint)
    Call<SimilarResultsResponse> searchSimilarSongs(
            @Query("method") String method,
            @Query("track") String track,
            @Query("artist") String artistName,
            @Query("api_key") String apiKey,
            @Query("format") String format
    );
}

