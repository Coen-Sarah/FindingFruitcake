package com.example.findingfruitcake.model;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.example.findingfruitcake.EntityType;
import com.example.findingfruitcake.PlayerAnimationComponent;
import javafx.geometry.Point2D;

import java.util.ArrayList;

public class Player{
    Entity player;
    ArrayList<FoodItem> inventory = new ArrayList<FoodItem>();

    public Player(PhysicsComponent physics) {
        player = FXGL.entityBuilder()
                .type(EntityType.PLAYER)
                .at(135, 135)
                .with(physics)
                .with(new PlayerAnimationComponent())
                .bbox(new HitBox("PlayerHitBox", new Point2D(0,16) , BoundingShape.box(12, 16)))
                .with(new CollidableComponent(true))
                .buildAndAttach();
    }

    public String getPlayerDirection(){
        return getAnimation().getCurrentDirection();
    }

    public PhysicsComponent getPhysics(){
        return player.getComponent(PhysicsComponent.class);
    }

    public PlayerAnimationComponent getAnimation(){
        return player.getComponent(PlayerAnimationComponent.class);
    }

}
