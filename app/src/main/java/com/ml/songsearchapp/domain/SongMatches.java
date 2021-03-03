package com.ml.songsearchapp.domain;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ml.songsearchapp.domain.Song;

import java.util.List;

@Entity
public class SongMatches {

    @PrimaryKey
    public int songMatchesId = 1;

    @SerializedName("track")
    @Expose
    public List<Song> songList;

    public SongMatches(List<Song> songList) {
        this.songList = songList;
    }

    public int getSongMatchesId() {
        return this.songMatchesId;
    }

    public List<Song> getSongList() {
        return songList;
    }
}