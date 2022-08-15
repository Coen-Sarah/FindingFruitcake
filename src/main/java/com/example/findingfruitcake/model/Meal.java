package com.example.findingfruitcake.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getAssetLoader;
import static com.example.findingfruitcake.model.CookBook.getCookBook;

public class Meal {

    private static Meal meal = null;

    public static String fileName = "plate.png";
    public static Image sprite = getAssetLoader().loadImage(fileName);

    public static ImageView spriteView = new ImageView(sprite);;

    FoodItem drink;
    FoodItem main;
    FoodItem side;
    FoodItem dessert;

    static boolean completedMeal = false;

    private Meal(){
    }

    public void setTestMeal(){
        this.setDrink(getCookBook().getFoodByName("Milk"));
        this.setMain(getCookBook().getFoodByName("Ribs"));
        this.setSide(getCookBook().getFoodByName("Bread"));
        this.setDessert(getCookBook().getFoodByName("Cookies"));
        this.setCompletedMeal(true);
    }

    public static Meal getMeal(){
        if(meal == null){
           meal = new Meal();
        }
        return meal;
    }

    public static String getFileName() {
        return fileName;
    }

    public static Image getSprite() {
        return sprite;
    }

    public static ImageView getSpriteView() {
        return spriteView;
    }

    public FoodItem getDrink() {
        return drink;
    }

    public void setDrink(FoodItem drink) {
        this.drink = drink;
        checkIfComplete();
    }

    public FoodItem getMain() {
        return main;
    }

    public void setMain(FoodItem main) {
        this.main = main;
    }

    public FoodItem getSide() {
        return side;
    }

    public void setSide(FoodItem side) {
        this.side = side;
    }

    public FoodItem getDessert() {
        return dessert;
    }

    public void setDessert(FoodItem dessert) {
        this.dessert = dessert;
    }

    public boolean isCompletedMeal() {
        return completedMeal;
    }

    public void setCompletedMeal(boolean completedMeal) {
        this.completedMeal = completedMeal;
    }

    public void checkIfComplete() {
        if(this.drink != null && this.main != null && this.side != null && this.dessert != null){
            this.setCompletedMeal(true);
        }
    }

    public void judgeMeal() {
        System.out.println("You had a " + main.getName() + " with a side of " + side.getName() +
                ", " + drink.getName() + " to drink, and " + dessert.getName() + " for dessert.");

    }
}
