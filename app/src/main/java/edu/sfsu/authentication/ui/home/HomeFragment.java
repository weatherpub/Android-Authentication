package edu.sfsu.authentication.ui.home;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import edu.sfsu.authentication.databinding.FragmentHomeBinding;
import edu.sfsu.authentication.model.home.DrinkModel;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    int i = 0;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // ViewModelProvider a utility class that provides ViewModels for a scope.
        // HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class); // this uses HomeViewModel.java
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class); // this uses HomeViewModel.java

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        View view = binding.getRoot(); // Renamed 'View root' to 'View view'.

        final Observer<ArrayList<DrinkModel>> listObserver = new Observer<ArrayList<DrinkModel>>() {
            @Override
            public void onChanged(ArrayList<DrinkModel> drinkModel) {
                drinkModel.get(i).getIdDrink();
            }
        };

        /**
         * Reserved for RecyclerView implementation
        homeViewModel.getMutableLiveData().observe(getViewLifecycleOwner(), data -> {
            binding.rvHomeFragment.setAdapter(recyclerViewAdapter);
            binding.rvHomeFragment.setLayoutManager(new LinearLayoutManager(getContext()));
        });
        */

        // Observe the LiveData, passing in this activity as the LifeCycleOwner and the observer.
        homeViewModel.getMutableLiveData().observe(getViewLifecycleOwner(), listObserver);

        return view;

/*
        // Create the observer which updates the UI.
        final Observer<ArrayList<AlbumModel>> albumObserver = new Observer<ArrayList<AlbumModel>>() {
            @Override
            public void onChanged(ArrayList<AlbumModel> albumModels) {
                albumModels.get(i).setStrAlbum("Life After College");
                albumModels.get(i).setIntYearReleased("1992");
                albumModels.get(i).setStrStyle("Logical Thinking");
                albumModels.get(i).setStrGenre("Philosophy");
                albumModels.get(i).setStrLabel("Own Label");
            }
        };

        recyclerViewAdapter.setListener(new RecyclerViewAdapter.Listener() {
            @Override
            public void itemClicked(int item) {
                Log.i("log", "adapter.setListener -> " + item);
                Intent intent = new Intent(getActivity(), AlbumDetailActivity.class);
                intent.putExtra(AlbumDetailActivity.IMAGE_ID, item);
                getActivity().startActivity(intent);
            }
        });

        homeViewModel.getLiveData().observe(getViewLifecycleOwner(), data -> {
            binding.rvHomeFragment.setAdapter(recyclerViewAdapter);
            binding.rvHomeFragment.setLayoutManager(new LinearLayoutManager(getContext()));
        });

        // Observe the LiveData, passing in this activity as the LifeCycleOwner and the observer.
        homeViewModel.getLiveData().observe(getViewLifecycleOwner(), albumObserver);

        return view;
        */



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}