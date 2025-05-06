package edu.sfsu.authentication.ui.home;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;

import edu.sfsu.authentication.async.HomeViewAsyncTask;
import edu.sfsu.authentication.model.home.DrinkModel;
public class HomeViewModel extends ViewModel {

    // HomeFragment calls this method
    public MutableLiveData<ArrayList<DrinkModel>> getMutableLiveData() {
        return new MutableLiveData<>();
    }

    public HomeViewModel() {
        new HomeViewAsyncTask.LatestTradesAsyncTask().execute("https://www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita");
    }
}