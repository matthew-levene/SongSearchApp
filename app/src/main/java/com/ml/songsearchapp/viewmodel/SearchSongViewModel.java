package com.ml.songsearchapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ml.songsearchapp.domain.SongMatches;
import com.ml.songsearchapp.repository.SongRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

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
