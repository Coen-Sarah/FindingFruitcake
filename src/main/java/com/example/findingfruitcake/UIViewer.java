package com.example.findingfruitcake;

import com.almasb.fxgl.inventory.view.InventoryView;
import com.example.findingfruitcake.model.FoodItem;
import com.example.findingfruitcake.scenes.CookBookSubScene;
import com.example.findingfruitcake.scenes.InventorySubScene;
import com.example.findingfruitcake.scenes.MealSubScene;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameScene;

public class UIViewer {

    public static final String INVENTORY = "INVENTORY";
    public static final String COOKBOOK = "COOKBOOK";
    public static final String DEFAULT = "DEFAULT";
    public static final String MEAL = "MEAL";
    public static String currentUI = DEFAULT;

    private static CookBookSubScene cookbook = null;
    private static InventorySubScene inventory = null;
    private static MealSubScene meal = null;
    private static InventoryView<FoodItem> inventoryView;

    public static void showGameUI(){
        currentUI = DEFAULT;
        clearUI();
    }

    public static void showInventory() {
        currentUI = INVENTORY;
        clearUI();

        InventorySubScene inventory = new InventorySubScene();
        getGameScene().addUINode(inventory.getContentRoot());

    }

    public static void showCookbook(){

        currentUI = COOKBOOK;
        clearUI();

        if(cookbook == null) {
            cookbook = new CookBookSubScene();
        }
        getGameScene().addUINode(cookbook.getContentRoot());

    }

    public static void showMeal(){
        currentUI = MEAL;
        clearUI();

        if(meal == null) {
            meal = new MealSubScene();
        }
        getGameScene().addUINode(meal.getContentRoot());

    }

    public static String getActiveUI(){
        return currentUI;
    }

    public static void clearUI(){
        getGameScene().clearUINodes();
    }

}
