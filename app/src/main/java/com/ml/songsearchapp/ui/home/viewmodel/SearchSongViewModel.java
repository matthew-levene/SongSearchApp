package com.ml.songsearchapp.ui.home.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ml.songsearchapp.db.entities.SongMatches;
import com.ml.songsearchapp.repository.SongRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import timber.log.Timber;

@HiltViewModel
public class SearchSongViewModel extends ViewModel {

    private final SongRepository songRepository;
    private final MutableLiveData<SongMatches> _songMatchesMutableLiveData = new MutableLiveData<>();
    public LiveData<SongMatches> songMatchesLiveData = _songMatchesMutableLiveData;

    private SongMatches songMatches;

    @Inject
    public SearchSongViewModel(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public void retrieveSongs(String songTitle) {
        setSongMatches(
                songRepository.retrieveSongs(songTitle)
        );

        _songMatchesMutableLiveData.setValue(getSongMatches());
    }

    private void setSongMatches(SongMatches songMatches) {
        this.songMatches = songMatches;
    }

    private SongMatches getSongMatches() {
        return this.songMatches;
    }
}
