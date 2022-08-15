package com.example.findingfruitcake.util;

import com.example.findingfruitcake.model.FoodItem;
import com.example.findingfruitcake.model.Recipe;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getAssetLoader;

public class GameDataRetriever {

    public static ArrayList<FoodItem> getAllFoodItems(){
        ArrayList<FoodItem> foods = new ArrayList<>();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(FoodItem.class, new FoodItemAdaptor())
                .create();
        Type collectionType = new TypeToken<Collection<FoodItem>>(){}.getType();
        Collection<FoodItem> foodsData;

        try {

            foodsData = gson.fromJson(new FileReader("src/main/resources/assets/json/FoodItem.json"), collectionType);
            foods.addAll(foodsData);

        }catch(IOException e){
            e.printStackTrace();
        }

        return foods;
    };
    public static ArrayList<Recipe> getAllRecipes() {

        ArrayList<Recipe> recipes = new ArrayList<>();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Recipe.class, new RecipeAdaptor())
                .create();
        Type collectionType = new TypeToken<Collection<Recipe>>() {
        }.getType();
        Collection<Recipe> recipeData;

        try {

            recipeData = gson.fromJson(new FileReader("src/main/resources/assets/json/Recipe.json"), collectionType);
            recipes.addAll(recipeData);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return recipes;
    }

}
