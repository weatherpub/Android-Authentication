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
import okhttp3.OkHttpClient;

import edu.sfsu.authentication.model.home.LatestTradesModel;
import edu.sfsu.authentication.vm.LatestTradesViewModel;
import okhttp3.Request;
import okhttp3.Response;

/**
 * LiveData overview
 * LiveData is an Observable data holder class. Unlike a regular observable, LiveData is lifecycle-aware,
 * meaning it respects the lifecycle of other app components, such as activities, fragments, or services.
 * This awareness ensures LiveData only updates app component observers that are iin an active lifecycle state.
 * .
 * Android App Architecture
 * <a href="https://developer.android.com/topic/libraries/architecture/livedata">...</a>
 *
 * Purpose of the HomeViewModel
 * The HomeViewModel provides data to HomeViewFragment, by way of AsyncTask
 **/

public class HomeViewModel extends ViewModel {

    // create a new MutableLiveData object
    private MutableLiveData<ArrayList<LatestTradesModel>> liveData;

    /**
     * LatestTradesViewModel.getInstance()
     * Singleton at work, we only get one instance of the object.
     * (this ensures we are dealing the same data set throughout the app)
     */
    private LatestTradesViewModel viewModel = LatestTradesViewModel.getInstance();

    /**
     * Get a copy of the model, which contains the request.
     */
    private ArrayList<LatestTradesModel> model = viewModel.getModel();

    // liveData = viewModel.getModel();

    public MutableLiveData<ArrayList<LatestTradesModel>> getMutableLiveData() {
        return new MutableLiveData<>();
    }

    // constructor
    public HomeViewModel() {
        Log.i("log", "[ DashboardViewModel loaded ]");
        liveData = getMutableLiveData();
        new LatestTradesAsyncTask().execute("https://data.alpaca.markets/v2/stocks/trades/latest?symbols=AAPL");
    }

    /**
     * Nested Class can access outer variables
     */
    public class LatestTradesAsyncTask extends AsyncTask<String, String, String> {
        OkHttpClient client;
        Request request;
        Response response;
        @Override
        protected String doInBackground(String... strings) {
            client = new OkHttpClient(); // Using OkHttp library for threads
            request = new Request.Builder().url(strings[0]).build(); // pass the url in as an array index

            Log.i("log", "doInBackground() - 4.28.25"); // debug

            try {
                response = client.newCall(request).execute();

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
                JSONArray obj = jsonObject.getJSONArray("album");

                Log.i("log", "onPostExecute() => ");

                // Create a model for each object returned from the json results.
                for(int i = 0; i < obj.length(); i++) {
                    Log.i("log", "for loop => " + i);

                    /*
                    model.add(new AlbumModel(
                            obj.getJSONObject(i).getString("idAlbum"),
                            obj.getJSONObject(i).getString("idArtist"),
                            obj.getJSONObject(i).getString("idLabel"),
                            obj.getJSONObject(i).getString("strAlbum"),
                            obj.getJSONObject(i).getString("strAlbumStripped"),
                            obj.getJSONObject(i).getString("strArtist"),
                            obj.getJSONObject(i).getString("strArtistStripped"),
                            obj.getJSONObject(i).getString("intYearReleased"),
                            obj.getJSONObject(i).getString("strStyle"),
                            obj.getJSONObject(i).getString("strGenre"),
                            obj.getJSONObject(i).getString("strLabel"),
                            obj.getJSONObject(i).getString("strReleaseFormat"),
                            obj.getJSONObject(i).getString("intSales"),
                            obj.getJSONObject(i).getString("strAlbumThumb"),
                            obj.getJSONObject(i).getString("strAlbumThumbHQ"),
                            obj.getJSONObject(i).getString("strAlbumBack"),
                            obj.getJSONObject(i).getString("strAlbumCDart"),
                            obj.getJSONObject(i).getString("strAlbumSpine"),
                            obj.getJSONObject(i).getString("strAlbum3DCase"),
                            obj.getJSONObject(i).getString("strAlbum3DFlat"),
                            obj.getJSONObject(i).getString("strAlbum3DFace"),
                            obj.getJSONObject(i).getString("strAlbum3DThumb"),
                            //obj.getJSONObject(i).getString("strDescriptionEN"),
                            obj.getJSONObject(i).getString("strDescriptionDE"),
                            obj.getJSONObject(i).getString("strDescriptionFR"),
                            obj.getJSONObject(i).getString("strDescriptionCN"),
                            obj.getJSONObject(i).getString("strDescriptionIT"),
                            obj.getJSONObject(i).getString("strDescriptionJP"),
                            obj.getJSONObject(i).getString("strDescriptionRU"),
                            obj.getJSONObject(i).getString("strDescriptionES"),
                            obj.getJSONObject(i).getString("strDescriptionPT"),
                            obj.getJSONObject(i).getString("strDescriptionSE"),
                            obj.getJSONObject(i).getString("strDescriptionNL"),
                            obj.getJSONObject(i).getString("strDescriptionHU"),
                            obj.getJSONObject(i).getString("strDescriptionNO"),
                            obj.getJSONObject(i).getString("strDescriptionIL"),
                            obj.getJSONObject(i).getString("strDescriptionPL"),
                            obj.getJSONObject(i).getString("intLoved"),
                            obj.getJSONObject(i).getString("intScore"),
                            obj.getJSONObject(i).getString("intScoreVotes"),
                            obj.getJSONObject(i).getString("strReview"),
                            obj.getJSONObject(i).getString("strMood"),
                            obj.getJSONObject(i).getString("strTheme"),
                            obj.getJSONObject(i).getString("strSpeed"),
                            obj.getJSONObject(i).getString("strLocation"),
                            obj.getJSONObject(i).getString("strMusicBrainzID"),
                            obj.getJSONObject(i).getString("strMusicBrainzArtistID"),
                            obj.getJSONObject(i).getString("strAllMusicID"),
                            obj.getJSONObject(i).getString("strBBCReviewID"),
                            obj.getJSONObject(i).getString("strRateYourMusicID"),
                            obj.getJSONObject(i).getString("strDiscogsID"),
                            obj.getJSONObject(i).getString("strWikidataID"),
                            obj.getJSONObject(i).getString("strWikipediaID"),
                            obj.getJSONObject(i).getString("strGeniusID"),
                            obj.getJSONObject(i).getString("strLyricWikiID"),
                            obj.getJSONObject(i).getString("strMusicMozID"),
                            obj.getJSONObject(i).getString("strItunesID"),
                            obj.getJSONObject(i).getString("strAmazonID"),
                            obj.getJSONObject(i).getString("strLocked")));
                     */
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            liveData.setValue(model);
        }
    }
}