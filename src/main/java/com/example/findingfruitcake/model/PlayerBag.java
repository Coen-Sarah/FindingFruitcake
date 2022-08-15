package com.example.findingfruitcake.model;

import com.almasb.fxgl.inventory.Inventory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.util.ArrayList;

public class PlayerBag {
    static ObservableList<FoodItem> inventory = FXCollections.observableArrayList();
    static ObservableList<FoodItem> recipes = FXCollections.observableArrayList();

    public static ObservableList<FoodItem> getInventory() {
        return inventory;
    }

    public static void setInventory(ObservableList<FoodItem> inventory) {
        PlayerBag.inventory = inventory;
    }

    public static ObservableList<FoodItem> getDrinks(){
        return inventory.filtered(food -> food.getFoodType().contains(FoodItem.FoodType.DRINK));
    }

    public static ObservableList<FoodItem> getMains(){
        return inventory.filtered(food -> food.getFoodType().contains(FoodItem.FoodType.MAIN));
    }

    public static ObservableList<FoodItem> getSides(){
        return inventory.filtered(food -> food.getFoodType().contains(FoodItem.FoodType.SIDE));
    }

    public static ObservableList<FoodItem> getDesserts(){
        return inventory.filtered(food -> food.getFoodType().contains(FoodItem.FoodType.DESSERT));
    }

    public static void addFoodToInventory(FoodItem foodItem){
        if(!inventory.contains(foodItem)) {
            inventory.add(foodItem);
            foodItem.setAsFound();
        }
    }

    public static void removeFoodFromInventory(FoodItem foodItem) {
        if(inventory.contains(foodItem)){
            inventory.remove(foodItem);
        }
    }

    public static void addRecipeToInventory(FoodItem recipe){
        if(!inventory.contains(recipe)) {
            inventory.add(recipe);
            recipe.setAsFound();
        }
    }

    public static void removeRecipeFromInventory(Recipe recipe) {
        if(inventory.contains(recipe)){
            inventory.remove(recipe);
        }
    }
}
