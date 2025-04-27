package edu.sfsu.authentication.ui.home;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.IOException;
import java.util.ArrayList;

import edu.sfsu.authentication.model.home.LatestTradesModel;
import edu.sfsu.authentication.vm.LatestTradesViewModel;

/**
 * LiveData overview
 * LiveData is an Observable data holder class. Unlike a regular observable, LiveData is lifecycle-aware,
 * meaning it respects the lifecycle of other app components, such as activities, fragments, or services.
 * This awareness ensures LiveData only updates app component observers that are iin an active lifecycle state.
 * .
 * Android App Architecture
 * <a href="https://developer.android.com/topic/libraries/architecture/livedata">...</a>
 **/

public class HomeViewModel extends ViewModel {

    private MutableLiveData<ArrayList<LatestTradesModel>> liveData;

    /**
     * LatestTradesViewModel.getInstance()
     * This is the singleton at work, we only get one instance of the object.
     * (this ensures we are dealing the same data set throughout the app)
     */
    private LatestTradesViewModel viewModel = LatestTradesViewModel.getInstance();

    /**
     * Get a copy of the model, which contains the request.
     */
    private ArrayList<LatestTradesModel> model = viewModel.getModel();

    public MutableLiveData<ArrayList<LatestTradesModel>> getMutableLiveData() {
        return new MutableLiveData<>();
    }

    public HomeViewModel() {
        Log.i("log", "[ DashboardViewModel loaded ]");
        liveData = getMutableLiveData();
        new LatestTradesAsyncTask().execute("https://data.alpaca.markets/v2/stocks/trades/latest?symbols=AAPL");
    }

    /**
     * Nested Class can access outer variables
     */
    public class LatestTradesAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            OkHttpClient client  = new OkHttpClient();
            Request request = new Request.Builder().url(strings[0]).build();
            Response response;
            Log.i("log", "doInBackground() - 6:31");

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

    private final MutableLiveData<ArrayList<LatestTradesModel>> liveData;

    public

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}