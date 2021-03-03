package com.ml.songsearchapp.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.ml.songsearchapp.domain.SongMatches;

@Dao
public interface SongDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void storeSongList(SongMatches songMatches);

    @Query("SELECT * FROM SongMatches")
    SongMatches getAllSongs();

    @Query("DELETE FROM SongMatches")
    void deleteAllSongs();
}
