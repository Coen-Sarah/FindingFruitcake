package com.example.findingfruitcake.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.ArrayList;

public class Recipe {

    String name;
    FoodItem output;
    ArrayList<FoodItem> ingredients;
    private final BooleanProperty foundProperty = new SimpleBooleanProperty();

    public Recipe(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FoodItem getOutput() {
        return output;
    }

    public void setOutput(FoodItem output) {
        this.output = output;
    }

    public ArrayList<FoodItem> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<FoodItem> ingredients) {
        this.ingredients = ingredients;
    }

    public void setFound(boolean found) {
        foundProperty.setValue(true);
    }

    public BooleanProperty foundProperty(){
        return foundProperty;
    }
}
