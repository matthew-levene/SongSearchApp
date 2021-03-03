package com.ml.songsearchapp.ui.details.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ml.songsearchapp.db.entities.SongMatches;
import com.ml.songsearchapp.db.entities.similar.SimilarSong;
import com.ml.songsearchapp.network.similar.SimilarSongMatches;
import com.ml.songsearchapp.repository.SongRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SimilarSongsViewModel extends ViewModel {

    private final SongRepository songRepository;
    public LiveData<SimilarSongMatches> songMatchesLiveData;

    @Inject
    public SimilarSongsViewModel(SongRepository songRepository) {
        this.songRepository = songRepository;
        songMatchesLiveData = songRepository.similarMatchesLiveData;
    }

    public void retrieveSimilarSongs(String songTitle, String artistName) {
        songRepository.retrieveSimilarSongs(songTitle, artistName);
    }
}