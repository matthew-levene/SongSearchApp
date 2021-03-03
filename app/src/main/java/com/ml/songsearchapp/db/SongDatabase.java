package com.ml.songsearchapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.ml.songsearchapp.domain.SongMatches;

import static com.ml.songsearchapp.db.DatabaseConstants.DATABASE_VERSION;

@Database(entities = {SongMatches.class}, version = DATABASE_VERSION, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class SongDatabase extends RoomDatabase {
    public abstract SongDao getSongDao();
}
