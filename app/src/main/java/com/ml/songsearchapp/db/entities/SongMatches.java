package com.ml.songsearchapp.db.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
public class SongMatches {

    @PrimaryKey
    public Integer songMatchesId = 1;

    @SerializedName("track")
    @Expose
    private List<Song> songList;

    public SongMatches(List<Song> songList) {
        this.songList = songList;
    }

    public Integer getSongMatchesId() {
        return songMatchesId;
    }

    public List<Song> getSongList() {
        return songList;
    }
}