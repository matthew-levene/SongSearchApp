package com.ml.songsearchapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.Snackbar;
import com.ml.songsearchapp.R;
import com.ml.songsearchapp.databinding.FragmentHomeBinding;
import com.ml.songsearchapp.db.entities.Song;
import com.ml.songsearchapp.db.entities.SongMatches;
import com.ml.songsearchapp.ui.home.adapter.SearchAdapter;
import com.ml.songsearchapp.ui.home.viewmodel.SearchSongViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import timber.log.Timber;

import static com.ml.songsearchapp.ui.home.adapter.SearchAdapter.*;

@AndroidEntryPoint
public class HomeFragment extends Fragment implements SearchAdapterDelegate {

    private SearchAdapter searchAdapter;
    private FragmentHomeBinding binding;
    SearchSongViewModel viewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(SearchSongViewModel.class);
        setupRecyclerView();
        setupObserver();
        setupListeners();
    }

    private void setupRecyclerView() {
        searchAdapter = new SearchAdapter(this);
        binding.searchResultsRecycler.setAdapter(searchAdapter);
    }

    private void setupObserver() {
        viewModel.songMatchesLiveData.observe(getViewLifecycleOwner(), this::handleSongMatches);
    }

    private void setupListeners() {
        binding.searchButton.setOnClickListener(v -> {
            if(binding.searchEdittext.getText().toString().isEmpty())
                showSnackBar(getString(R.string.please_enter_song_title_text));
            else viewModel.retrieveSongs(binding.searchEdittext.getText().toString());
        });
    }

    private void showSnackBar(String message) {
        Snackbar sb = Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT);
        sb.setAnchorView(binding.searchCardview);
        sb.show();
    }

    private void handleSongMatches(SongMatches songMatches) {
        if(songMatches != null) searchAdapter.submitList(songMatches.songList);
    }

    @Override
    public void songClick(Song song) {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigate(HomeFragmentDirections.toDetailsFragment(song));
    }
}
