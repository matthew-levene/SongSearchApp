package com.ml.songsearchapp.ui.home.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.ml.songsearchapp.db.entities.Song;

public class SongDiffUtil extends DiffUtil.ItemCallback<Song> {
    @Override
    public boolean areItemsTheSame(@NonNull Song oldItem, @NonNull Song newItem) {
        return oldItem == newItem;
    }

    @Override
    public boolean areContentsTheSame(@NonNull Song oldItem, @NonNull Song newItem) {
        return oldItem.getName().equals(newItem.getName()) &&
                oldItem.getArtist().equals(newItem.getArtist());
    }
}
