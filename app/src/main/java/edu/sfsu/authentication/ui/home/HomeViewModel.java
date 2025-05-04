package edu.sfsu.authentication.ui.home;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import edu.sfsu.authentication.async.HomeViewAsyncTask;
import edu.sfsu.authentication.model.home.DrinkModel;
import edu.sfsu.authentication.vm.DrinkViewModel;
import okhttp3.OkHttpClient;

import okhttp3.Request;
import okhttp3.Response;

public class HomeViewModel extends ViewModel {

    // MutableLiveData<ArrayList<DrinkModel>> is also used in HomeFragment.
    public MutableLiveData<ArrayList<DrinkModel>> getMutableLiveData() {
        return new MutableLiveData<>();
    }

    public HomeViewModel() {
        new HomeViewAsyncTask.LatestTradesAsyncTask().execute("https://www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita");
    }
}