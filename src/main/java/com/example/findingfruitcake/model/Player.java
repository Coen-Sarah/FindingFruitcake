package com.example.findingfruitcake.model;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.inventory.Inventory;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.example.findingfruitcake.EntityType;
import com.example.findingfruitcake.PlayerAnimationComponent;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

public class Player{
    Entity player;

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

    public Rectangle2D getPlayerPickupRange(){
        int xRange = 20;
        int yRange = 16;
        int xOffset = 2;
        int yOffset = 4;
        double sideFacingMultiplier = 2.5;
        int thinSideModifier = 4;

        String direction = getPlayerDirection();
        Point2D center = getEntity().getCenter();
        Rectangle2D range = new Rectangle2D(0,0,0,0);

        if(direction.equalsIgnoreCase("LEFT")){
            range = new Rectangle2D(
                    center.getX() - xRange,
                    (center.getY()) - (sideFacingMultiplier/2 * yRange) - yOffset,
                    xRange + xOffset, sideFacingMultiplier * yRange);
        }
        else if(direction.equalsIgnoreCase("RIGHT")){
            range = new Rectangle2D(
                    center.getX(),
                    (center.getY()) - (sideFacingMultiplier/2 * yRange) - yOffset,
                    xRange + xOffset, sideFacingMultiplier * yRange);
        }
        else if(direction.equalsIgnoreCase("UP")){
            range = new Rectangle2D(
                    center.getX() - (sideFacingMultiplier/2 * xRange) + 2 ,
                    (center.getY() - yRange),
                    sideFacingMultiplier *  xRange, yRange + thinSideModifier);
        }
        else if(direction.equalsIgnoreCase("DOWN")){
            range = new Rectangle2D(
                    center.getX() - (sideFacingMultiplier/2 * xRange) + 2 ,
                    (center.getY()),
                    sideFacingMultiplier *  xRange, yRange + thinSideModifier);
        }
        return range;
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

    public Entity getEntity(){
        return player;
    }
}
