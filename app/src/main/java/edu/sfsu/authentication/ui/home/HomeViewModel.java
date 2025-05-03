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

public class HomeViewModel extends ViewModel {

    static MutableLiveData<ArrayList<DrinkModel>> getMutableLiveData() {
        return new MutableLiveData<>();
    }

    public HomeViewModel() {
        new LatestTradesAsyncTask().execute("https://www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita");
    }

    // Moved all the login into LatestTradesAsyncTask and made LatestTradesAsyncTask static
    public static class LatestTradesAsyncTask extends AsyncTask<String, String, String> {
        private final MutableLiveData<ArrayList<DrinkModel>> liveData = getMutableLiveData();
        private final ArrayList<DrinkModel> model = DrinkViewModel.getModel();

        @Override
        protected String doInBackground(String... param) {
            OkHttpClient client = new OkHttpClient(); // Using OkHttp library for threads
            Request request = new Request.Builder().url(param[0]).build(); // pass the url in as an array index

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