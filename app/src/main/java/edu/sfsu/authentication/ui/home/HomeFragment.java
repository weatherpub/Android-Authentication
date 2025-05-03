package edu.sfsu.authentication.ui.home;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.w3c.dom.Text;

import java.util.ArrayList;

import edu.sfsu.authentication.R;
import edu.sfsu.authentication.databinding.FragmentHomeBinding;
import edu.sfsu.authentication.model.home.DrinkModel;

public class HomeFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private FragmentHomeBinding binding;
    int i = 0;

    /**
     * In most cases, an app compontent's onCreate(0 method is the right place to begin observing
     * a LiveData object for the following reasons:
     * To ensure the system doesn't make redundant calls from an activity or fragment's onResume() method.
     * To ensure that the activity or fragment has data that it can display as soon as it becomes active.
     */
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // ViewModelProvider a utility class that provides ViewModels for a scope.
        // HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class); // this uses HomeViewModel.java
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class); // this uses HomeViewModel.java

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        View view = binding.getRoot(); // Renamed 'View root' to 'View view'.

        /* *
         * Spinner Implementation
         */
        Spinner spinner = (Spinner) view.findViewById(R.id.alphabet_spinner);
        //spinner.setOnItemClickListener((AdapterView.OnItemClickListener) this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.alphabet_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // update the UI
        final Observer<ArrayList<DrinkModel>> listObserver = new Observer<ArrayList<DrinkModel>>() {
            @Override
            public void onChanged(ArrayList<DrinkModel> m) {
                m.get(i).setIdDrink("100000");
                m.get(i).setStrDrink("Beer");
                m.get(i).setStrDrinkAlternate("Lemon Aid");
                m.get(i).setStrTags("Beer Tag");
                /*
                m.get(i).setStrVideo();
                m.get(i).setStrCategory();
                m.get(i).setStrIBA();
                m.get(i).setStrAlcoholic();
                m.get(i).setStrGlass();
                m.get(i).setStrInstructions();
                m.get(i).setStrInstructionsES();
                m.get(i).setStrInstructionsDE();
                m.get(i).setStrInstructionsFR();
                m.get(i).setStrInstructionsIT();
                m.get(i).setStrInstructionsZH_HANS();
                m.get(i).setStrInstructionsZH_HANT();
                m.get(i).setStrIngredient1();
                m.get(i).setStrIngredient2();
                m.get(i).setStrIngredient3();
                m.get(i).setStrIngredient4();
                m.get(i).setStrIngredient5();
                m.get(i).setStrIngredient6();
                m.get(i).setStrIngredient7();
                m.get(i).setStrIngredient8();
                m.get(i).setStrIngredient9();
                m.get(i).setStrIngredient10();
                m.get(i).setStrIngredient11();
                m.get(i).setStrIngredient12();
                m.get(i).setStrIngredient13();
                m.get(i).setStrIngredient14();
                m.get(i).setStrIngredient15();
                m.get(i).setStrMeasure1();
                m.get(i).setStrMeasure2();
                m.get(i).setStrMeasure3();
                m.get(i).setStrMeasure4();
                m.get(i).setStrMeasure5();
                m.get(i).setStrMeasure6();
                m.get(i).setStrMeasure7();
                m.get(i).setStrMeasure8();
                m.get(i).setStrMeasure9();
                m.get(i).setStrMeasure10();
                m.get(i).setStrMeasure11();
                m.get(i).setStrMeasure12();
                m.get(i).setStrMeasure13();
                m.get(i).setStrMeasure14();
                m.get(i).setStrMeasure15();
                */

                m.get(i).setStrImageSource("https://commons.wikimedia.org/wiki/File:Klassiche_Margarita.jpg");
                m.get(i).setStrImageAttribution("Beer Thing");
                m.get(i).setStrCreativeCommonsConfirmed("No");
                m.get(i).setDateModified("04-29-25");
            }
        };

        /*
        homeViewModel.getMutableLiveData().observe(getViewLifecycleOwner(), data -> {
            binding.rvHomeFragment.setAdapter(recyclerViewAdapter);
            binding.rvHomeFragment.setLayoutManager(new LinearLayoutManager(getContext()));
        });
        */

        // Observe the LiveData, passing in this activity as the LifeCycleOwner and the observer.
        homeViewModel.getMutableLiveData().observe(getViewLifecycleOwner(), listObserver);

        return view;

/**
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        Log.i("log"," adapterVIew.getItemAtPosition " + parent.getItemAtPosition(pos));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}