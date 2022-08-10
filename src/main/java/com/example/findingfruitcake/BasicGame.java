package com.example.findingfruitcake;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.*;
import com.almasb.fxgl.physics.box2d.dynamics.BodyDef;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameWorld;

public class BasicGame extends GameApplication {

    private int SPEED = 75;
    private int tileSize = 16;
    private int windowHeight = tileSize * 25;
    private int windowWidth = tileSize * 30;
    private Entity player;

    @Override
    protected void initPhysics() {
        PhysicsWorld physics = FXGL.getPhysicsWorld();
        physics.setGravity(0, 0);
        physics.addCollisionHandler(new CollisionHandler(EntityType.PLAYER, EntityType.WALL) {
            @Override
            protected void onCollisionBegin(Entity player, Entity wall) {
                System.out.println("HIT!!");
                player.getComponent(PhysicsComponent.class).setVelocityX(0);
                player.getComponent(PhysicsComponent.class).setVelocityY(0);
            }
        });

        physics.addCollisionHandler(new CollisionHandler(EntityType.PLAYER, EntityType.FOOD_ITEM) {
            @Override
            protected void onCollisionBegin(Entity player, Entity foodItem) {
                System.out.println("HIT!!" + foodItem.getProperties().getString("name"));
                //player.getComponent(PhysicsComponent.class).setVelocityX(0);
                //player.getComponent(PhysicsComponent.class).setVelocityY(0);
            }
        });
    }

    @Override
    protected void initInput() {

        FXGL.getInput().addAction(new UserAction("Right") {
            @Override
            protected void onAction() {
                player.getComponent(PhysicsComponent.class).setVelocityX(SPEED);
                player.getComponent(PhysicsComponent.class).setVelocityY(0);
                player.getComponent(AnimationComponent.class).moveRight();
            }

            @Override
            protected void onActionEnd() {
                player.getComponent(PhysicsComponent.class).setVelocityX(0);
                player.getComponent(PhysicsComponent.class).setVelocityY(0);
                player.getComponent(AnimationComponent.class).idleRight();
            }
        }, KeyCode.D);
        FXGL.getInput().addAction(new UserAction("Left") {
            @Override
            protected void onAction() {
                player.getComponent(PhysicsComponent.class).setVelocityX(-SPEED);
                player.getComponent(PhysicsComponent.class).setVelocityY(0);
                player.getComponent(AnimationComponent.class).moveLeft();
            }

            @Override
            protected void onActionEnd() {
                player.getComponent(PhysicsComponent.class).setVelocityX(0);
                player.getComponent(PhysicsComponent.class).setVelocityY(0);
                player.getComponent(AnimationComponent.class).idleLeft();
            }
        }, KeyCode.A);
        FXGL.getInput().addAction(new UserAction("Up") {

            @Override
            protected void onAction() {
                player.getComponent(PhysicsComponent.class).setVelocityX(0);
                player.getComponent(PhysicsComponent.class).setVelocityY(-SPEED);
                player.getComponent(AnimationComponent.class).moveUp();
            }

            @Override
            protected void onActionEnd() {
                player.getComponent(PhysicsComponent.class).setVelocityX(0);
                player.getComponent(PhysicsComponent.class).setVelocityY(0);
                player.getComponent(AnimationComponent.class).idleUp();
            }
        }, KeyCode.W);
        FXGL.getInput().addAction(new UserAction("Down") {
            @Override
            protected void onAction() {
                player.getComponent(PhysicsComponent.class).setVelocityX(0);
                player.getComponent(PhysicsComponent.class).setVelocityY(SPEED);
                player.getComponent(AnimationComponent.class).moveDown();
            }

            @Override
            protected void onActionEnd() {
                player.getComponent(PhysicsComponent.class).setVelocityX(0);
                player.getComponent(PhysicsComponent.class).setVelocityY(0);
                player.getComponent(AnimationComponent.class).idleDown();
            }
        }, KeyCode.S);
    }

    @Override
    protected void initGame() {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setFixtureDef(new FixtureDef().friction(0).density(0.1f));
        BodyDef bd = new BodyDef();
        bd.setFixedRotation(true);
        bd.setType(BodyType.DYNAMIC);
        physics.setBodyDef(bd);

        getGameWorld().addEntityFactory(new MapFactory());
        FXGL.setLevelFromMap("HomeFirstFloor.tmx");
        getGameWorld().getEntities().get(6).setZIndex(1);
        getGameWorld().getEntities().get(7).setZIndex(1);
        getGameWorld().getEntities().get(8).setZIndex(1);
        player = FXGL.entityBuilder()
                .type(EntityType.PLAYER)
                .at(135, 135)
                .with(physics)
                .with(new AnimationComponent())
                .bbox(new HitBox("PlayerHitBox", new Point2D(0,16) , BoundingShape.box(12, 16)))
                .with(new CollidableComponent(true))
                .buildAndAttach();
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setDeveloperMenuEnabled(true);
        settings.setWidth(windowWidth);
        settings.setHeight(windowHeight);
        settings.setTitle("Basic Game App");
        settings.setVersion("0.1");
    }

    public static void main(String[] args) {
        launch(args);
    }

}