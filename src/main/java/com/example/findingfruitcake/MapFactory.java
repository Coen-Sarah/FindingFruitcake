package com.example.findingfruitcake;

import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;

public class MapFactory implements EntityFactory {

    @Spawns("ExteriorWall")
    public Entity newExteriorWall(SpawnData data){
        return entityBuilder()
                .from(data)
                .bbox(new HitBox(BoundingShape.box((Integer) data.get("width"), (Integer) data.get("height"))))
                .with(new PhysicsComponent())
                        .build();
    }

    @Spawns("FoodItem")
    public Entity newFoodItem(SpawnData data){
        return entityBuilder()
                .from(data)
                .viewWithBBox(new Circle(((Integer) data.get("width")/2), Color.GREEN))
                .build();
    }

    @Spawns("")
    public Entity newEmpty(SpawnData data) {
        return entityBuilder(data)
                .view(new Rectangle(30, 30, Color.RED))
                .build();
    }

}
