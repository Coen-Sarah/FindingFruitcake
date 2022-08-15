package com.example.findingfruitcake.model;

import com.example.findingfruitcake.util.GameDataRetriever;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class CookBook {
    private static CookBook cookbook = null;
    static ObservableList<FoodItem> allFoods;
    static ObservableList<Recipe> allRecipes;

    CookBook() {
    }

    public static CookBook getCookBook(){
        if(cookbook == null){

            cookbook = new CookBook();
            cookbook.allFoods = FXCollections.observableArrayList(
                    (FoodItem food) -> new Observable[] {food.foundProperty()});
            allFoods.addAll(GameDataRetriever.getAllFoodItems());

            cookbook.allRecipes = FXCollections.observableArrayList(
                    (Recipe recipe) -> new Observable[] {recipe.foundProperty()});
            allRecipes.addAll(GameDataRetriever.getAllRecipes());
        }
        return cookbook;
    }

    public ObservableList<Recipe> getAllRecipes() {
        return allRecipes;
    }


    public Recipe getRecipeByOutput(String outputName){
        AtomicReference<Recipe> recipe = new AtomicReference<>(new Recipe());
        this.getAllRecipes().forEach(recipeItem -> {
            if(recipeItem.getOutput().getName().equalsIgnoreCase(outputName)){
                recipe.set(recipeItem);
            }
        });
        return recipe.get();
    }

    public ObservableList<FoodItem> getAllFoods() {
        return allFoods;
    }

    public FoodItem getFoodByName(String foodName){
        AtomicReference<FoodItem> foodItem = new AtomicReference<>(new FoodItem());
        this.getAllFoods().forEach(food -> {
            if(food.getName().equalsIgnoreCase(foodName)){
                foodItem.set(food);
            }
        });
        return foodItem.get();
    }

}
