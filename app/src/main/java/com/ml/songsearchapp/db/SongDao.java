package com.ml.songsearchapp.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.ml.songsearchapp.db.entities.SongMatches;

@Dao
public interface SongDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void storeSongList(SongMatches songMatches);

    @Query("SELECT * FROM SongMatches")
    LiveData<SongMatches> getAllSongs();

    @Query("DELETE FROM SongMatches")
    void deleteAllSongs();
}
