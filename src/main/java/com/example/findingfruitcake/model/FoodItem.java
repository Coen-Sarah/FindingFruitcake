package com.example.findingfruitcake.model;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.example.findingfruitcake.EntityType;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.util.ArrayList;

import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getAssetLoader;

public class FoodItem {
    String name;
    String description;
    ArrayList<FoodType> foodType;

    public FoodItem(){

    }

    public enum FoodType {
        DRINK,
        MAIN,
        SIDE,
        DESERT,
        INGREDIENT,
        JOKE
    }
}
