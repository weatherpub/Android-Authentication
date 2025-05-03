package edu.sfsu.authentication.vm;

import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;
import edu.sfsu.authentication.model.home.LatestTradesModel;

/**
 * Design Pattern Singleton
 * The goal of this file is to return a single instance of LatestTradesViewModel
 */

public class LatestTradesViewModel {
    // instantiate new object
    private static final LatestTradesViewModel latestTradesViewModel = new LatestTradesViewModel();

    // an ArrayList of "LatestTradesModel's"
    private final ArrayList<LatestTradesModel> model;
    /**
     * Return an instance of this object, can only be one.
     *
     * When a variable is declared static in Java, the variable belongs to
     * the class itself rather than to any specific instance of the class.
     *
     * Static methods are methods that belong to the class rather than to any specific instance of the class.
     * Static methods can be called directly on the class itself without needing to create an instance of the class itself.
     *
     * Called in HomeViewModel:
     * private LatestTradesViewModel viewModel = LatestViewModel.getInstance();
     */
    private static LatestTradesViewModel getInstance() {
        return latestTradesViewModel;
    }

    // Constructor is private to facilitate a closed system.
    // cannot be instantiated
    // only LatestTradesModel can access it.
    private LatestTradesViewModel() {
        model = new ArrayList<>();
    }

    // Return a copy of the model
    public ArrayList<LatestTradesModel> getModel() {
        return model;
    }

    // return a new MutableLiveData Object
    /*
    public MutableLiveData<ArrayList<LatestTradesModel>> getMutableLiveData() {
        return new MutableLiveData<>();
    }
     */
}