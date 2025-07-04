package edu.sfsu.authentication.ui.home;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.squareup.picasso.Picasso;
import java.util.ArrayList;

import edu.sfsu.authentication.R;
import edu.sfsu.authentication.Settings.SettingsActivity;
import edu.sfsu.authentication.async.HomeViewAsyncTask;
import edu.sfsu.authentication.databinding.FragmentHomeBinding;
import edu.sfsu.authentication.model.home.DrinkModel;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private FragmentHomeBinding binding;
    private static int counter = 0;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        new ViewModelProvider(this).get(HomeViewAsyncTask.class); // this uses HomeViewModel.java

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        View view = binding.getRoot(); // Renamed 'View root' to 'View view'.

        ImageView img_holder = (ImageView) view.findViewById(R.id.image_holder);
        TextView textView = (TextView) view.findViewById(R.id.text_home);

        /*
        Spinner spinner = (Spinner) view.findViewById(R.id.alphabet_spinner);
        ArrayAdapter<CharSequence> dataAdapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.alphabet_array,
                android.R.layout.simple_spinner_item
        );

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        */

        //Intent settings_intent = new Intent(view.getContext(), SettingsActivity.class);

        Button prev = (Button) view.findViewById(R.id.btn_prev);
        Button next = (Button) view.findViewById(R.id.btn_next);

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(settings_intent);
                // Log.i("log", "Previous Selection");
                Log.i("log", "Settings Activity.");
                // counter--;
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;
            }
        });

        final Observer<ArrayList<DrinkModel>> homeObserver = new Observer<ArrayList<DrinkModel>>() {
            final int i = 4;
            @Override
            public void onChanged(ArrayList<DrinkModel> model) {
                textView.setText(model.get(i).getStrDrink());
                Picasso.get().load(model.get(i).getStrDrinkThumb()).into(img_holder);
            }
        };

        HomeViewAsyncTask.getLiveData().observe(getViewLifecycleOwner(), homeObserver);

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