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
//    private final MutableLiveData<ArrayList<DrinkModel>> liveData;
    // Create an instance of LiveData to hold a certain type of data.
    // This is usually done within your ViewModel Class.
    // -Android Documentation
    // private static final MutableLiveData<ArrayList<DrinkModel>> liveData;

    /**
     * LatestTradesViewModel.getInstance()
     * Singleton at work, we only get one instance of the object.
     * (this ensures we are dealing the same data set throughout the app)
     */
    // private final LatestTradesViewModel viewModel = LatestTradesViewModel.getInstance();
    //private final DrinkViewModel viewModel = DrinkViewModel.getInstance();

    /**
     * Get a copy of the model, which contains the request.
     */
    //private final ArrayList<DrinkModel> model = viewModel.getModel();
    /*
    * This has been updated for the DrinkModel
    * This methods needs to stay in this file
    * */

    static MutableLiveData<ArrayList<DrinkModel>> getMutableLiveData() {
        return new MutableLiveData<>();
    }

    // constructor
    public HomeViewModel() {
        Log.i("log", "[ HomeViewModel ]");
        //liveData = getMutableLiveData(); // viewModel is defined above, so I might as well use it. I commented out getMutableLiveData above.
        // new LatestTradesAsyncTask().execute("https://data.alpaca.markets/v2/stocks/trades/latest?symbols=AAPL");
        new LatestTradesAsyncTask().execute("https://www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita");
    }

    /**
     * Nested Class can access outer variables
     */
    // public class LatestTradesAsyncTask extends AsyncTask<String, String, String> {
    public static class LatestTradesAsyncTask extends AsyncTask<String, String, String> {

        private final MutableLiveData<ArrayList<DrinkModel>> liveData = getMutableLiveData();
        private final ArrayList<DrinkModel> model = DrinkViewModel.getModel();

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

            Log.i("log", "[ onPostExecute(String result) ]");
            Log.i("log", "[ result.toString() ] " + result);

            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray obj = jsonObject.getJSONArray("drinks");

                Log.i("log", "[ onPostExecute(String result) ] 2");

                // Create a model for each object returned from the json results.
                for(int i = 0; i < obj.length(); i++) {
                    Log.i("log", "[ onPostExecute(String result) ] 3");

                    model.add(new DrinkModel(
                            obj.getJSONObject(i).getString("idDrink"),
                            obj.getJSONObject(i).getString("strDrink"),
                            obj.getJSONObject(i).getString("strDrinkAlternate"),
                            obj.getJSONObject(i).getString("strTags"),
                            obj.getJSONObject(i).getString("strVideo"),
                            obj.getJSONObject(i).getString("strCategory"),
                            obj.getJSONObject(i).getString("strIBA"),
                            obj.getJSONObject(i).getString("strAlcoholic"),
                            obj.getJSONObject(i).getString("strGlass"),
                            obj.getJSONObject(i).getString("strInstructions"),
                            obj.getJSONObject(i).getString("strInstructionsES"),
                            obj.getJSONObject(i).getString("strInstructionsDE"),
                            obj.getJSONObject(i).getString("strInstructionsFR"),
                            obj.getJSONObject(i).getString("strInstructionsIT"),
                            obj.getJSONObject(i).getString("strInstructionsZH-HANS"),
                            obj.getJSONObject(i).getString("strInstructionsZH-HANT"),
                            obj.getJSONObject(i).getString("strDrinkThumb"),
                            obj.getJSONObject(i).getString("strIngredient1"),
                            obj.getJSONObject(i).getString("strIngredient2"),
                            obj.getJSONObject(i).getString("strIngredient3"),
                            obj.getJSONObject(i).getString("strIngredient4"),
                            obj.getJSONObject(i).getString("strIngredient5"),
                            obj.getJSONObject(i).getString("strIngredient6"),
                            obj.getJSONObject(i).getString("strIngredient7"),
                            obj.getJSONObject(i).getString("strIngredient8"),
                            obj.getJSONObject(i).getString("strIngredient9"),
                            obj.getJSONObject(i).getString("strIngredient10"),
                            obj.getJSONObject(i).getString("strIngredient11"),
                            obj.getJSONObject(i).getString("strIngredient12"),
                            obj.getJSONObject(i).getString("strIngredient13"),
                            obj.getJSONObject(i).getString("strIngredient14"),
                            obj.getJSONObject(i).getString("strIngredient15"),
                            obj.getJSONObject(i).getString("strMeasure1"),
                            obj.getJSONObject(i).getString("strMeasure2"),
                            obj.getJSONObject(i).getString("strMeasure3"),
                            obj.getJSONObject(i).getString("strMeasure4"),
                            obj.getJSONObject(i).getString("strMeasure5"),
                            obj.getJSONObject(i).getString("strMeasure6"),
                            obj.getJSONObject(i).getString("strMeasure7"),
                            obj.getJSONObject(i).getString("strMeasure8"),
                            obj.getJSONObject(i).getString("strMeasure9"),
                            obj.getJSONObject(i).getString("strMeasure10"),
                            obj.getJSONObject(i).getString("strMeasure11"),
                            obj.getJSONObject(i).getString("strMeasure12"),
                            obj.getJSONObject(i).getString("strMeasure13"),
                            obj.getJSONObject(i).getString("strMeasure14"),
                            obj.getJSONObject(i).getString("strMeasure15"),
                            obj.getJSONObject(i).getString("strImageSource"),
                            obj.getJSONObject(i).getString("strImageAttribution"),
                            obj.getJSONObject(i).getString("strCreativeCommonsConfirmed"),
                            obj.getJSONObject(i).getString("dateModified")));
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            liveData.setValue(model);
        }
    }
}