package com.example.findingfruitcake.model;

import com.almasb.fxgl.inventory.Inventory;

public class PlayerBag {
    static CookBook cookBook;
    static Inventory<FoodItem> inventory = new Inventory<FoodItem>(64);

    public static CookBook getCookBook() {
        return cookBook;
    }

    public static void setCookBook(CookBook cookBook) {
        PlayerBag.cookBook = cookBook;
    }

    public static Inventory<FoodItem> getInventory() {
        return inventory;
    }

    public static void setInventory(Inventory<FoodItem> inventory) {
        PlayerBag.inventory = inventory;
    }
    public static void addFoodToInventory(FoodItem foodItem){
        if(!inventory.hasItem(foodItem)) {
            inventory.add(foodItem);
            foodItem.setAsFound();
            CookBook.getFoundFoods().add(foodItem);
        }
    }

}
