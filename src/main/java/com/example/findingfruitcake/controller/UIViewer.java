package com.example.findingfruitcake.controller;

import com.almasb.fxgl.ui.UI;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getAssetLoader;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameScene;

public class UIViewer {

    public static final String INVENTORY = "INVENTORY";
    public static final String COOKBOOK = "COOKBOOK";
    public static final String DEFAULT = "DEFAULT";
    public static final String MEAL = "MEAL";
    public static String currentUI = DEFAULT;

    public static void showGameUI(){
        currentUI = DEFAULT;
        clearUI();
    }

    public static void showInventory() {
        currentUI = INVENTORY;
        clearUI();

        InventoryUIController controller = new InventoryUIController();
        UI ui = getAssetLoader().loadUI("inventory.fxml", controller);
        getGameScene().addUI(ui);
        controller.update();

    }

    public static void showCookbook(){

        currentUI = COOKBOOK;
        clearUI();

        CookBookController controller = new CookBookController();
        UI ui = getAssetLoader().loadUI("inventory.fxml", controller);
        getGameScene().addUI(ui);
        controller.update();

    }

    public static void showMeal(){
        currentUI = MEAL;
        clearUI();

        MealController controller = new MealController();
        UI ui = getAssetLoader().loadUI("meal.fxml", controller);
        getGameScene().addUI(ui);

    }

    public static String getActiveUI(){
        return currentUI;
    }

    public static void clearUI(){
        getGameScene().clearUINodes();
    }

}
