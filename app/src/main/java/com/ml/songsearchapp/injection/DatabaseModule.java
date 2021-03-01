package com.ml.songsearchapp.injection;

import android.content.Context;

import androidx.room.Room;

import com.ml.songsearchapp.db.DatabaseConstants;
import com.ml.songsearchapp.db.SongDao;
import com.ml.songsearchapp.db.SongDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {

    @Provides
    SongDao provideSongDao(SongDatabase songDatabase) {
        return songDatabase.getSongDao();
    }

    @Provides
    @Singleton
    SongDatabase provideDatabase(@ApplicationContext Context appContext) {
        return Room.databaseBuilder(
                appContext,
                SongDatabase.class,
                DatabaseConstants.DATABASE_NAME
        )
                .allowMainThreadQueries()
                .build();
    }

}
