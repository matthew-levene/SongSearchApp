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
    public LiveData<SongMatches> songMatchesLiveData;

    @Inject
    public SearchSongViewModel(SongRepository songRepository) {
        this.songRepository = songRepository;
        songMatchesLiveData = songRepository.songMatchesLiveData;
    }

    public void retrieveSongs(String songTitle) {
        songRepository.retrieveSongs(songTitle);
    }

    public void retrieveStoredSongs() { songRepository.retrieveStoredSongs(); }
}
