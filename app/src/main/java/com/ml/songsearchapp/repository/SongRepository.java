package com.ml.songsearchapp.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ml.songsearchapp.db.SongDao;
import com.ml.songsearchapp.network.ResultsResponse;
import com.ml.songsearchapp.domain.SongMatches;
import com.ml.songsearchapp.network.SongApi;
import com.ml.songsearchapp.network.SimilarResultsResponse;
import com.ml.songsearchapp.domain.SimilarSongMatches;

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

    private final MutableLiveData<SongMatches> _songMatchesMutableLiveData = new MutableLiveData<>();
    public LiveData<SongMatches> songMatchesLiveData = _songMatchesMutableLiveData;

    private final MutableLiveData<SimilarSongMatches> _similarMatchesMutableLiveData = new MutableLiveData<>();
    public LiveData<SimilarSongMatches> similarMatchesLiveData = _similarMatchesMutableLiveData;

    public void retrieveSongs(String songTitle) {
        new Thread(() -> {
            Call<ResultsResponse> call = songApi.searchSongName(
                    songApi.search,
                    songTitle,
                    songApi.apiKey,
                    songApi.responseFormat
            );

            call.enqueue(new Callback<ResultsResponse>() {
                             @Override
                             public void onResponse(Call<ResultsResponse> call, Response<ResultsResponse> response) {
                                 if (response.body() != null && response.isSuccessful()) {
                                     _songMatchesMutableLiveData.setValue(response.body().getResults().getSongmatches());
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
        }).start();
    }

    public void retrieveSimilarSongs(String songTitle, String artistName) {
        new Thread(() -> {
            Call<SimilarResultsResponse> call = songApi.searchSimilarSongs(
                    songApi.searchSimilar,
                    songTitle,
                    artistName,
                    songApi.apiKey,
                    songApi.responseFormat
            );

            call.enqueue(new Callback<SimilarResultsResponse>() {
                             @Override
                             public void onResponse(Call<SimilarResultsResponse> call, Response<SimilarResultsResponse> response) {
                                 if (response.body() != null && response.isSuccessful()) {
                                     _similarMatchesMutableLiveData.setValue(response.body().getSimilartracks());
                                 }
                             }

                             @Override
                             public void onFailure(Call<SimilarResultsResponse> call, Throwable t) {
                                 Timber.d("Network Request for Songs failed: %s", t.getLocalizedMessage());
                             }
                         }
            );
        }).start();

    }

     public void retrieveStoredSongs() {
        new Thread(() -> _songMatchesMutableLiveData.postValue(songDao.getAllSongs())).start();
    }


}
