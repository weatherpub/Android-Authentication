package edu.sfsu.authentication.vm;

import java.util.ArrayList;

import edu.sfsu.authentication.model.home.DrinkModel;

public class DrinkViewModel {
    private static final DrinkViewModel drinksViewModel = new DrinkViewModel();

    private static ArrayList<DrinkModel> model;

    public static DrinkViewModel getInstance() {
        return drinksViewModel;
    }

    private DrinkViewModel() {
        model = new ArrayList<>();
    }

    public static ArrayList<DrinkModel> getModel() {
        return model;
    }
}