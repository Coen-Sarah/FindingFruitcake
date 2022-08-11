package com.example.findingfruitcake.model;

import java.util.ArrayList;

public class Inventory {
    static CookBook cookBook;
    static ArrayList<FoodItem> foodsInInventory = new ArrayList<>();

    public Inventory(){}

    public static CookBook getCookBook() {
        return cookBook;
    }

    public static void setCookBook(CookBook cookBook) {
        Inventory.cookBook = cookBook;
    }

    public static ArrayList<FoodItem> getFoodsInInventory() {
        return foodsInInventory;
    }

    public static void setFoodsInInventory(ArrayList<FoodItem> foodsInInventory) {
        Inventory.foodsInInventory = foodsInInventory;
    }
    public static void addFoodToInventory(FoodItem foodItem){
        if(!foodsInInventory.contains(foodItem)) {
            foodsInInventory.add(foodItem);
            foodItem.setAsFound();
            CookBook.getFoundFoods().add(foodItem);
        }
    }
}
