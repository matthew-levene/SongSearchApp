package com.ml.songsearchapp.db;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ml.songsearchapp.domain.Song;

import java.util.List;

public class Converters {
    @TypeConverter
    public String songListToJson(List<Song> songList) {
        return new Gson().toJson(songList);
    }

    @TypeConverter
    public List<Song> songListFromJson(String value) {
        return new Gson().fromJson(value, new TypeToken<List<Song>>(){}.getType());
    }
}
