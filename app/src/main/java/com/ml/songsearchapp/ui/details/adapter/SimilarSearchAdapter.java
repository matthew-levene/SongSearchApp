package com.ml.songsearchapp.ui.details.adapter;

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
import com.ml.songsearchapp.databinding.ItemGridSongBinding;
import com.ml.songsearchapp.domain.SimilarSong;


public class SimilarSearchAdapter extends ListAdapter<SimilarSong, SimilarSearchAdapter.SongViewHolder> {

    public interface SearchAdapterDelegate {
        void songClick(SimilarSong song);
    }

    SearchAdapterDelegate adapterDelegate;

    public SimilarSearchAdapter(SearchAdapterDelegate adapterDelegate) {
        super(DIFF_CALLBACK);
        this.adapterDelegate = adapterDelegate;
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemGridSongBinding binding = ItemGridSongBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new SongViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        SimilarSong song = getItem(position);
        holder.songTitleTextView.setText(song.getName());
        holder.artistNameTextView.setText(song.getArtist().getName());
        Glide.with(holder.itemView)
                .load(R.drawable.ic_music_note)
                .into(holder.songImageView);

        holder.itemView.setOnClickListener(v -> adapterDelegate.songClick(song));
    }

    public static class SongViewHolder extends RecyclerView.ViewHolder {
        ImageView songImageView;
        TextView artistNameTextView, songTitleTextView;

        public SongViewHolder(ItemGridSongBinding binding) {
            super(binding.getRoot());
            artistNameTextView = binding.artistNameTextview;
            songTitleTextView = binding.songTitleTextview;
            songImageView = binding.songIconImageview;
        }
    }

    private final static DiffUtil.ItemCallback<SimilarSong> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<SimilarSong>() {
                @Override
                public boolean areItemsTheSame(@NonNull SimilarSong oldItem, @NonNull SimilarSong newItem) {
                    return oldItem == newItem;
                }

                @Override
                public boolean areContentsTheSame(@NonNull SimilarSong oldItem, @NonNull SimilarSong newItem) {
                    return oldItem.getName().equals(newItem.getName());
                }
            };
}
