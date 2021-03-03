package com.ml.songsearchapp.repository;

import com.ml.songsearchapp.db.SongDao;
import com.ml.songsearchapp.network.ResultsResponse;
import com.ml.songsearchapp.db.entities.SongMatches;
import com.ml.songsearchapp.network.SongApi;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

@Singleton
public class SongRepository {
    private SongDao songDao;
    private SongApi songApi;

    @Inject
    public SongRepository(SongDao songDao, SongApi songApi) {
        this.songDao = songDao;
        this.songApi = songApi;
    }

    public SongMatches retrieveSongs(String songTitle) {

        Call<ResultsResponse> call = songApi.searchSongName(
                songApi.method,
                songTitle,
                songApi.apiKey,
                songApi.responseFormat
        );

        call.enqueue(new Callback<ResultsResponse>() {
                         @Override
                         public void onResponse(Call<ResultsResponse> call, Response<ResultsResponse> response) {
                             if (response.body() != null && response.isSuccessful()) {
                                 songDao.deleteAllSongs();
                                 songDao.storeSongList(
                                         response.body()
                                                 .getResults()
                                                 .getSongmatches()
                                 );
                             }
                         }

                         @Override
                         public void onFailure(Call<ResultsResponse> call, Throwable t) {
                             Timber.d("Network Request for Songs failed: %s", t.getLocalizedMessage());
                         }
                     }
        );

        return songDao.getAllSongs();
    }
}
