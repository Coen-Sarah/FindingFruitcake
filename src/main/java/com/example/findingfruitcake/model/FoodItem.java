package com.example.findingfruitcake.model;

import com.almasb.fxgl.dsl.FXGL;
import com.google.gson.annotations.Expose;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class FoodItem {
    String name;
    String fileName;
    Image sprite;
    ImageView spriteView;
    String description;
    ArrayList<FoodType> foodType;
    private final BooleanProperty foundProperty = new SimpleBooleanProperty(false);

    Lighting lighting = new Lighting(new Light.Distant(45, 90, Color.BLACK));
    ColorAdjust bright = new ColorAdjust(0, 1, 1, 1);

    public FoodItem(){
        lighting.setContentInput(bright);
        lighting.setSurfaceScale(0.0);
    }

    public FoodItem(String name, String description, ArrayList<FoodType> foodType) {
        lighting.setContentInput(bright);
        lighting.setSurfaceScale(0.0);

        setName(name);
        this.description = description;
        this.foodType = foodType;
    }

    public enum FoodType {
        DRINK( "DRINK"),
        MAIN("MAIN"),
        SIDE("SIDE"),
        DESSERT("DESSERT"),
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
        this.fileName = "food/" + name + ".png";
        this.sprite = FXGL.getAssetLoader().loadImage(fileName);
        this.spriteView = new ImageView(this.sprite);
        this.spriteView.setPreserveRatio(true);
        this.spriteView.setFitWidth(16);
    }

    public String getFileName() {
        return fileName;
    }

    public Image getSprite() {
        return sprite;
    }

    public ImageView getSpriteView() {
        spriteView.setEffect(null);
        return spriteView;
    }

    public ImageView getSpriteOutline(){
        spriteView.setEffect(lighting);
        return spriteView;
    }

    public Lighting getOutlineEffect(){
        return lighting;
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

    public void setAsFound(){
        System.out.println("TRUE");
        foundProperty.setValue(true);
    }

    public BooleanProperty foundProperty() {
        return foundProperty;
    }

    public String toString(){
        return name;
    }
}

