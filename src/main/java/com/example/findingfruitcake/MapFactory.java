package com.example.findingfruitcake;

import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.example.findingfruitcake.model.CookBook;
import com.example.findingfruitcake.model.FoodItem;
import com.example.findingfruitcake.model.Meal;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getAssetLoader;
import static com.example.findingfruitcake.model.CookBook.getCookBook;

public class MapFactory implements EntityFactory {

    @Spawns("Wall")
    public Entity newExteriorWall(SpawnData data){
        return entityBuilder()
                .from(data)
                .bbox(new HitBox(BoundingShape.box((Integer) data.get("width"), (Integer) data.get("height"))))
                .with(new PhysicsComponent())
                .collidable()
                .build();
    }

    @Spawns("FoodItem")
    public Entity newFoodItem(SpawnData data){
        FoodItem food = getCookBook().getFoodByName(data.get("name"));
        double scale = 16 / food.getSprite().getWidth();

        Entity foodEntity = entityBuilder(data)
                .type(EntityType.FOOD_ITEM)
                .view(food.getFileName())
                .scale(scale, scale)
                .bbox(new HitBox(data.get("name"),new Point2D(4,4), BoundingShape.box(8 / scale, 8 / scale)))
                .with(new CollidableComponent(true))
                .zIndex(2)
                .anchorFromCenter()
                .build();
        return foodEntity;
    }

    @Spawns("Plate")
    public Entity newPlate(SpawnData data) {;
        Entity plate = entityBuilder(data)
                .type(EntityType.MEAL_PLATE)
                .view(Meal.getFileName())
                .bbox(new HitBox(data.get("name"),new Point2D(0,0), BoundingShape.box(16, 16)))
                .scale(.5,.5)
                .zIndex(0)
                .build();
        return plate;
    }

    @Spawns("MapExit")
    public Entity newMapExit(SpawnData data) {
        System.out.println(data.get("name").toString());
        Entity mapExit = entityBuilder(data)
                .bbox(new HitBox(BoundingShape.box((Integer) data.get("width"), (Integer) data.get("height"))))
                .collidable()
                .build();
        return mapExit;
    }

    @Spawns("Plant")
    public Entity newPlant(SpawnData data) {
        System.out.println(data.get("name").toString());
        Entity plant = entityBuilder(data)
                .bbox(new HitBox(BoundingShape.box((Integer) data.get("width"), (Integer) data.get("height"))))
                .with(new PhysicsComponent())
                .collidable()
                .build();
        return plant;
    }

    @Spawns("Chair")
    public Entity newChair(SpawnData data) {
        System.out.println(data.get("name").toString());
        Entity chair = entityBuilder(data)
                .bbox(new HitBox(BoundingShape.box((Integer) data.get("width"), (Integer) data.get("height"))))
                .with(new PhysicsComponent())
                .collidable()
                .build();
        return chair;
    }

    @Spawns("Table")
    public Entity newTable(SpawnData data) {
        System.out.println(data.get("name").toString());
        Entity table = entityBuilder(data)
                .bbox(new HitBox(BoundingShape.box((Integer) data.get("width"), (Integer) data.get("height"))))
                .with(new PhysicsComponent())
                .collidable()
                .build();
        return table;
    }

    @Spawns("Bookshelf")
    public Entity newBookshelf(SpawnData data) {
        System.out.println(data.get("name").toString());
        Entity bookshelf = entityBuilder(data)
                .bbox(new HitBox(BoundingShape.box((Integer) data.get("width"), (Integer) data.get("height"))))
                .with(new PhysicsComponent())
                .collidable()
                .build();
        return bookshelf;
    }

    @Spawns("KitchenAppliance")
    public Entity newKitchenAppliance(SpawnData data) {
        System.out.println(data.get("name").toString());
        Entity kitchenAppliance = entityBuilder(data)
                .bbox(new HitBox(BoundingShape.box((Integer) data.get("width"), (Integer) data.get("height"))))
                .with(new PhysicsComponent())
                .collidable()
                .build();
        return kitchenAppliance;
    }

    @Spawns("Light")
    public Entity newLight(SpawnData data) {
        System.out.println(data.get("name").toString());
        Entity light = entityBuilder(data)
                .bbox(new HitBox(BoundingShape.box((Integer) data.get("width"), (Integer) data.get("height"))))
                .with(new PhysicsComponent())
                .collidable()
                .build();
        return light;
    }

    @Spawns("Window")
    public Entity newWindow(SpawnData data) {
        Entity window = entityBuilder(data)
                .bbox(new HitBox(BoundingShape.box((Integer) data.get("width"), (Integer) data.get("height"))))
                .with(new PhysicsComponent())
                .collidable()
                .build();
        return window;
    }

    @Spawns("Cabinet")
    public Entity newCabinet(SpawnData data) {
        System.out.println(data.get("name").toString());
        Entity cabinet = entityBuilder(data)
                .bbox(new HitBox(BoundingShape.box((Integer) data.get("width"), (Integer) data.get("height"))))
                .with(new PhysicsComponent())
                .collidable()
                .build();
        return cabinet;
    }

    @Spawns("BathroomAppliance")
    public Entity newBathroomAppliance(SpawnData data) {
        System.out.println(data.get("name").toString());
        Entity bathroomAppliance = entityBuilder(data)
                .bbox(new HitBox(BoundingShape.box((Integer) data.get("width"), (Integer) data.get("height"))))
                .with(new PhysicsComponent())
                .collidable()
                .build();
        return bathroomAppliance;
    }

    @Spawns("Furniture")
    public Entity newFurniture(SpawnData data) {
        Entity furniture = entityBuilder(data)
                .bbox(new HitBox(BoundingShape.box((Integer) data.get("width"), (Integer) data.get("height"))))
                .with(new PhysicsComponent())
                .collidable()
                .build();
        return furniture;
    }

    @Spawns("Appliance")
    public Entity newAppliance(SpawnData data) {
        Entity appliance = entityBuilder(data)
                .bbox(new HitBox(BoundingShape.box((Integer) data.get("width"), (Integer) data.get("height"))))
                .with(new PhysicsComponent())
                .collidable()
                .build();
        return appliance;
    }


    @Spawns("")
    public Entity newEmpty(SpawnData data) {
        return entityBuilder(data)
                .view(new Rectangle(30, 30, Color.TRANSPARENT))
                .build();
    }

}
