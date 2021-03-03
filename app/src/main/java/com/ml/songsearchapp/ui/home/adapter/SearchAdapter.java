package com.ml.songsearchapp.ui.home.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ml.songsearchapp.R;
import com.ml.songsearchapp.databinding.ItemListSongBinding;
import com.ml.songsearchapp.domain.Song;


public class SearchAdapter extends ListAdapter<Song, SearchAdapter.SongViewHolder> {

    public interface SearchAdapterDelegate {
        void songClick(Song song);
    }

    SearchAdapterDelegate adapterDelegate;

    public SearchAdapter(SearchAdapterDelegate adapterDelegate) {
        super(DIFF_CALLBACK);
        this.adapterDelegate = adapterDelegate;
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListSongBinding binding = ItemListSongBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new SongViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        Song song = getItem(position);
        String imageURL = song.getImage().get(0).text;
        holder.songTitleTextView.setText(song.getName());
        holder.artistNameTextView.setText(song.getArtist());
        Glide.with(holder.itemView)
                .load(imageURL)
                .error(R.drawable.ic_music_library)
                .into(holder.songImageView);

        holder.itemView.setOnClickListener(v -> adapterDelegate.songClick(song));
    }

    public static class SongViewHolder extends RecyclerView.ViewHolder {
        ImageView songImageView;
        TextView artistNameTextView, songTitleTextView;

        public SongViewHolder(ItemListSongBinding binding) {
            super(binding.getRoot());
            artistNameTextView = binding.artistNameTextview;
            songTitleTextView = binding.songTitleTextview;
            songImageView = binding.songIconImageview;
        }
    }

    private final static DiffUtil.ItemCallback<Song> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Song>() {
                @Override
                public boolean areItemsTheSame(@NonNull Song oldItem, @NonNull Song newItem) {
                    return oldItem == newItem;
                }

                @Override
                public boolean areContentsTheSame(@NonNull Song oldItem, @NonNull Song newItem) {
                    return oldItem.getName().equals(newItem.getName());
                }
            };
}
