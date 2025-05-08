package edu.sfsu.authentication.ui.home;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;

import edu.sfsu.authentication.async.HomeViewAsyncTask;
import edu.sfsu.authentication.model.home.DrinkModel;
public class HomeViewModel extends ViewModel {

    MutableLiveData<ArrayList<DrinkModel>> liveData;

    public HomeViewModel() {
        liveData = new MutableLiveData<>();
        new HomeViewAsyncTask.LatestTradesAsyncTask().execute("https://www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita");
    }

    public MutableLiveData<ArrayList<DrinkModel>> getLiveData() {
        Log.i("log", "What's in mutableLiveData: " + liveData);
        return liveData;
    }
}