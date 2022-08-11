package com.example.findingfruitcake.model;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class FoodItem {
    String name;
    String description;
    ArrayList<FoodType> foodType;
    boolean isFound = false;

    public FoodItem(){

    }

    public FoodItem(String name, String description, ArrayList<FoodType> foodType) {
        this.name = name;
        this.description = description;
        this.foodType = foodType;
    }

    public enum FoodType {
        DRINK( "DRINK"),
        MAIN("MAIN"),
        SIDE("SIDE"),
        DESERT("DESERT"),
        INGREDIENT("INGREDIENT"),
        JOKE("JOKE");
        private String name;
        FoodType(String name){
            this.name = name.toUpperCase();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<FoodType> getFoodType() {
        return foodType;
    }

    public void setFoodType(ArrayList<FoodType> foodType) {
        this.foodType = foodType;
    }

    public boolean getIsFound(){
        return isFound;
    }

    public void setAsFound(){
        isFound = true;
    }

    public String toString(){
        return name;
    }
}

