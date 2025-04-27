package edu.sfsu.authentication.vm;

import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;
import edu.sfsu.authentication.model.home.LatestTradesModel;

/**
 * The goal of this file is to return a single instance of LatestTradesViewModel
 */

public class LatestTradesViewModel {

    // instantiate new object
    private static final LatestTradesViewModel obj = new LatestTradesViewModel();

    private final ArrayList<LatestTradesModel> model;

    // return an instance of this object
    public static LatestTradesViewModel getInstance() {
        return obj;
    }

    // Constructor is private to facilitate a closed system. only LatestTradesModel can access it.
    private LatestTradesViewModel() {
        model = new ArrayList<>();
    }

    // Return a copy of the model
    public ArrayList<LatestTradesModel> getModel() {
        return model;
    }

    // return a new MutableLiveData Object
    public MutableLiveData<LatestTradesModel> get() {
        return new MutableLiveData<>();
    }
}