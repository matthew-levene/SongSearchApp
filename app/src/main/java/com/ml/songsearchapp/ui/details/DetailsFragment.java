package com.ml.songsearchapp.ui.details;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.ml.songsearchapp.R;
import com.ml.songsearchapp.databinding.FragmentDetailsBinding;
import com.ml.songsearchapp.domain.Song;
import com.ml.songsearchapp.domain.SimilarSong;
import com.ml.songsearchapp.ui.details.adapter.SimilarSearchAdapter;
import com.ml.songsearchapp.viewmodel.SimilarSongsViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetailsFragment extends Fragment implements SimilarSearchAdapter.SearchAdapterDelegate {
    private FragmentDetailsBinding binding;
    private SimilarSearchAdapter similarSearchAdapter;
    SimilarSongsViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(SimilarSongsViewModel.class);
        setupViews();
        setupObserver();

    }

    private void setupViews() {
        Song song = (Song) requireArguments().getSerializable("songArgs");
        SimilarSong similarSong = (SimilarSong) requireArguments().getSerializable("similarSongArgs");

        if(song != null) {
            viewModel.retrieveSimilarSongs(song.getName(), song.getArtist());

            binding.artistNameTextview.setText(song.getArtist());
            binding.songTitleTextview.setText(song.getName());
            binding.listenerCountTextview.setText(getString(R.string.x_listeners_text, song.getListeners()));
            binding.goToStreamButton.setOnClickListener(v -> openStream(song.getUrl()));

        } else {
            viewModel.retrieveSimilarSongs(similarSong.getName(), similarSong.getArtist().getName());

            binding.artistNameTextview.setText(similarSong.getArtist().getName());
            binding.songTitleTextview.setText(similarSong.getName());
            binding.goToStreamButton.setOnClickListener(v -> openStream(similarSong.getUrl()));

            binding.listenerCountTextview.setText(getString(R.string.x_times_played_text, similarSong.getPlaycount()));
            binding.listenerCountImageview.setImageResource(R.drawable.ic_play);
        }

        Glide.with(requireView())
                .load(R.drawable.ic_music_note)
                .into(binding.songIconImageview);

        similarSearchAdapter = new SimilarSearchAdapter(this);
        binding.similiarSongsRecycler.setAdapter(similarSearchAdapter);
    }

    private void setupObserver() {
        viewModel.songMatchesLiveData.observe(getViewLifecycleOwner(), songMatches -> {
            similarSearchAdapter.submitList(songMatches.track);
        });
    }

    private void openStream(String url) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(requireContext(), Uri.parse(url));
    }

    @Override
    public void songClick(SimilarSong song) {
        reloadFragment(song);
    }

    private void reloadFragment(SimilarSong song) {
        DetailsFragment dt = this;
        Bundle bundle = new Bundle();
        bundle.putSerializable("songArgs", null);
        bundle.putSerializable("similarSongArgs", song);
        dt.setArguments(bundle);

        getParentFragmentManager()
                .beginTransaction()
                .remove(this)
                .add(dt, getTag())
                .commit();
    }
}
