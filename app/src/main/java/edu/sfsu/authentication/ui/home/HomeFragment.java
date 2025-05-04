package edu.sfsu.authentication.ui.home;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

import edu.sfsu.authentication.R;
import edu.sfsu.authentication.SpinnerActivity;
import edu.sfsu.authentication.databinding.FragmentHomeBinding;
import edu.sfsu.authentication.model.home.DrinkModel;

public class HomeFragment extends Fragment implements View.OnClickListener {


    private FragmentHomeBinding binding;

    /**
     * In most cases, an app compontent's onCreate(0 method is the right place to begin observing
     * a LiveData object for the following reasons:
     * To ensure the system doesn't make redundant calls from an activity or fragment's onResume() method.
     * To ensure that the activity or fragment has data that it can display as soon as it becomes active.
     */
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class); // this uses HomeViewModel.java

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        View view = binding.getRoot(); // Renamed 'View root' to 'View view'.

        // Button Begin
        // Add button listener programmatically.
        Button button = (Button) view.findViewById(R.id.button);
        Spinner spinner = (Spinner) view.findViewById(R.id.alphabet_spinner);
        TextView textView = (TextView) view.findViewById(R.id.text_home);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinner.getSelectedItem();
                String type = String.valueOf(spinner.getSelectedItem());
                textView.setText(type);
                Log.i("log", "[ Sandra ]");
            }
        });
        // Button End

        ArrayAdapter<CharSequence> dataAdapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.alphabet_array,
                android.R.layout.simple_spinner_item
        );

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        /*
        // Spinner
        Spinner spinner = (Spinner) view.findViewById(R.id.alphabet_spinner);
        //spinner.setOnItemClickListener((AdapterView.OnItemClickListener) this);

        ArrayAdapter<CharSequence> dataAdapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.alphabet_array,
                android.R.layout.simple_spinner_item
        );

        // spinner.setOnItemClickListener((AdapterView.OnItemClickListener) this);
        // spinner.setOnItemClickListener((AdapterView.OnItemClickListener) this);

        // spinner.setOnItemClickListener((AdapterView.OnItemClickListener) this);
        */

        // update the UI
        final Observer<ArrayList<DrinkModel>> listObserver = new Observer<ArrayList<DrinkModel>>() {
            final int i = 0;
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
    }

    @Override
    public void onClick(View view) {

    }

    static class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {
        void accessOuter(HomeFragment homeFragment) {
            /*
                Spinner spinner = (Spinner) findViewById(R.id.alphabet_spinner);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.alphabet_array, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
            */
        }

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            Log.i("log", "");
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}