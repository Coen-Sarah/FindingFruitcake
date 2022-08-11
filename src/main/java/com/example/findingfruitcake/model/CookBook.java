package com.example.findingfruitcake.model;

import com.example.findingfruitcake.util.GameDataRetriever;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class CookBook {
    static ArrayList<Recipe> allRecipes = new ArrayList<>();
    static ArrayList<Recipe> unlockedRecipes = new ArrayList<>();
    static ArrayList<FoodItem> allFoods = new ArrayList<>();
    static ArrayList<FoodItem> foundFoods = new ArrayList<>();

    public CookBook(){}

    public CookBook(ArrayList<Recipe> allRecipes,
                    ArrayList<Recipe> unlockedRecipes,
                    ArrayList<FoodItem> allFoods,
                    ArrayList<FoodItem> foundFoods) {
        this.allRecipes = allRecipes;
        this.unlockedRecipes = unlockedRecipes;
        this.allFoods = allFoods;
        this.foundFoods = foundFoods;
    }

    public static ArrayList<Recipe> getAllRecipes() {
        return allRecipes;
    }

    public static void setAllRecipes(ArrayList<Recipe> allRecipes) {
        CookBook.allRecipes = allRecipes;
    }

    public static ArrayList<Recipe> getUnlockedRecipes() {
        return unlockedRecipes;
    }

    public static void setUnlockedRecipes(ArrayList<Recipe> unlockedRecipes) {
        CookBook.unlockedRecipes = unlockedRecipes;
    }

    public static ArrayList<FoodItem> getAllFoods() {
        if(allFoods.size() == 0){
            allFoods = GameDataRetriever.getAllFoodItems();
        }
        return allFoods;
    }

    public static void setAllFoods(ArrayList<FoodItem> allFoods) {
        CookBook.allFoods = allFoods;
    }

    public static ArrayList<FoodItem> getFoundFoods() {
        return foundFoods;
    }

    public static void setFoundFoods(ArrayList<FoodItem> foundFoods) {
        CookBook.foundFoods = foundFoods;
    }

    public static FoodItem getFoodByName(String foodName){
        AtomicReference<FoodItem> foodItem = new AtomicReference<>(new FoodItem());
        CookBook.getAllFoods().forEach(food -> {
            if(food.getName().equalsIgnoreCase(foodName)){
                foodItem.set(food);
            }
        });
        return foodItem.get();
    }

}
