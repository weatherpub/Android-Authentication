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

import edu.sfsu.authentication.model.home.DrinkModel;
import edu.sfsu.authentication.vm.DrinkViewModel;
import okhttp3.OkHttpClient;

import edu.sfsu.authentication.model.home.LatestTradesModel;
import okhttp3.Request;
import okhttp3.Response;

/**
 * LiveData overview
 * LiveData is an Observable data holder class. Unlike a regular observable, LiveData is lifecycle-aware,
 * meaning it respects the lifecycle of other app components, such as activities, fragments, or services.
 * This awareness ensures LiveData only updates app component observers that are iin an active lifecycle state.
 * .
 * Android App Architecture https://developer.android.com/topic/libraries/architecture/livedata
 *
 * Update LiveData objects
 * LiveData has no publicly available methods to update the stored data.
 *
 * The MutableLiveData class exposes the setValue(T) and postValue(T) methods
 * publicly and you must use these if you need to edit the value stored in a LiveView object.
 * Usually MutableLiveData is used in the viewModel and then the ViewModel only exposes immutable
 * LiveData objects to the observers.
 **/

public class HomeViewModel extends ViewModel {
    // Create an instance of LiveData to hold a certain type of data.
    // This is usually done within your ViewModel Class.
    // -Android Documentation
    //private final MutableLiveData<ArrayList<LatestTradesModel>> liveData;
    private final MutableLiveData<ArrayList<DrinkModel>> liveData;

    /**
     * LatestTradesViewModel.getInstance()
     * Singleton at work, we only get one instance of the object.
     * (this ensures we are dealing the same data set throughout the app)
     */
    // private final LatestTradesViewModel viewModel = LatestTradesViewModel.getInstance();
    private final DrinkViewModel viewModel = DrinkViewModel.getInstance();

    /**
     * Get a copy of the model, which contains the request.
     */
    // private final ArrayList<LatestTradesModel> model = viewModel.getModel();
    private final ArrayList<DrinkModel> model = viewModel.getModel();
    /*
    * This method: MutableLiveData<ArrayList<LatestTradesModel>> has to be part of this class.
    * */
    /*
    public MutableLiveData<ArrayList<LatestTradesModel>> getMutableLiveData() {
        return new MutableLiveData<>();
    }
    */

    public MutableLiveData<ArrayList<DrinkModel>> getMutableLiveData() {
        return new MutableLiveData<>();
    }

    // constructor
    public HomeViewModel() {
        Log.i("log", "[ HomeViewModel ]");
        liveData = getMutableLiveData(); // viewModel is defined above, so I might as well use it. I commented out getMutableLiveData above.
        // new LatestTradesAsyncTask().execute("https://data.alpaca.markets/v2/stocks/trades/latest?symbols=AAPL");
        new LatestTradesAsyncTask().execute("https://www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita");
    }

    /**
     * Nested Class can access outer variables
     */
    public class LatestTradesAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... param) {
            Log.i("log", "[ doInBackground(String... param)] 1");

            OkHttpClient client = new OkHttpClient(); // Using OkHttp library for threads
            Request request = new Request.Builder().url(param[0]).build(); // pass the url in as an array index

            Log.i("log", "[ doInBackground(String... param)] 2");

            try {
                Response response = client.newCall(request).execute();

                if(!response.isSuccessful())
                    return null;

                return response.body().string();
            } catch(IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray obj = jsonObject.getJSONArray("drinks");

                Log.i("log", "onPostExecute() => ");

                // Create a model for each object returned from the json results.
                for(int i = 0; i < obj.length(); i++) {
                    Log.i("log", "for loop => " + i);

                    /*
                    model.add(new LatestTradesModel(
                            obj.getJSONObject(i).getString("trades"),
                            obj.getJSONObject(i).getString("c"),
                            obj.getJSONObject(i).getString("i"),
                            obj.getJSONObject(i).getString("p"),
                            obj.getJSONObject(i).getString("s"),
                            obj.getJSONObject(i).getString("t"),
                            obj.getJSONObject(i).getString("x"),
                            obj.getJSONObject(i).getString("z")));
                     */
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            liveData.setValue(model);
        }
    }

        /*
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray obj = jsonObject.getJSONArray("album");

                Log.i("log", "onPostExecute() => ");

                // Create a model for each object returned from the json results.
                for(int i = 0; i < obj.length(); i++) {
                    Log.i("log", "for loop => " + i);

                    model.add(new LatestTradesModel(
                            obj.getJSONObject(i).getString("trades"),
                            obj.getJSONObject(i).getString("c"),
                            obj.getJSONObject(i).getString("i"),
                            obj.getJSONObject(i).getString("p"),
                            obj.getJSONObject(i).getString("s"),
                            obj.getJSONObject(i).getString("t"),
                            obj.getJSONObject(i).getString("x"),
                            obj.getJSONObject(i).getString("z")));
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            liveData.setValue(model);
        }
        */
    }