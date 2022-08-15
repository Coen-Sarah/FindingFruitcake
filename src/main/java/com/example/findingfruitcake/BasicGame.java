package com.example.findingfruitcake;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.*;
import com.almasb.fxgl.physics.box2d.dynamics.BodyDef;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import com.example.findingfruitcake.model.*;
import javafx.scene.input.KeyCode;

import java.io.Serializable;
import java.util.ArrayList;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameWorld;
import static com.example.findingfruitcake.model.CookBook.getCookBook;
import static com.example.findingfruitcake.model.Meal.getMeal;

public class BasicGame extends GameApplication {

    private int SPEED = 75;
    private int tileSize = 16;
    private int windowHeight = tileSize * 25;
    private int windowWidth = tileSize * 30;

    private Player player;

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

        KeyCode up = KeyCode.UP;
        KeyCode down = KeyCode.DOWN;
        KeyCode left = KeyCode.LEFT;
        KeyCode right = KeyCode.RIGHT;
        KeyCode inventory = KeyCode.I;
        KeyCode cookBook = KeyCode.C;
        KeyCode interact = KeyCode.ENTER;

        FXGL.getInput().addAction(new UserAction("Up") {

            @Override
            protected void onAction() {
                player.getPhysics().setVelocityX(0);
                player.getPhysics().setVelocityY(-SPEED);
                player.getAnimation().moveUp();
            }

            @Override
            protected void onActionEnd() {
                player.getPhysics().setVelocityX(0);
                player.getPhysics().setVelocityY(0);
                player.getAnimation().idleUp();
            }
        }, up);
        FXGL.getInput().addAction(new UserAction("Down") {
            @Override
            protected void onAction() {
                player.getPhysics().setVelocityX(0);
                player.getPhysics().setVelocityY(SPEED);
                player.getAnimation().idleDown();
            }

            @Override
            protected void onActionEnd() {
                player.getPhysics().setVelocityX(0);
                player.getPhysics().setVelocityY(0);
                player.getAnimation().idleDown();
            }
        }, down);
        FXGL.getInput().addAction(new UserAction("Left") {
            @Override
            protected void onAction() {
                player.getPhysics().setVelocityX(-SPEED);
                player.getPhysics().setVelocityY(0);
                player.getAnimation().moveLeft();
            }

            @Override
            protected void onActionEnd() {
                player.getPhysics().setVelocityX(0);
                player.getPhysics().setVelocityY(0);
                player.getAnimation().idleLeft();
            }
        }, left);
        FXGL.getInput().addAction(new UserAction("Right") {
            @Override
            protected void onAction() {
                player.getPhysics().setVelocityX(SPEED);
                player.getPhysics().setVelocityY(0);
                player.getAnimation().moveRight();
            }

            @Override
            protected void onActionEnd() {
                player.getPhysics().setVelocityX(0);
                player.getPhysics().setVelocityY(0);
                player.getAnimation().idleRight();
            }
        }, right);

        FXGL.getInput().addAction(new UserAction("Interact") {
            @Override
            protected void onActionEnd() {

                ArrayList<Entity> entities = (ArrayList<Entity>) getGameWorld().getEntitiesInRange(player.getPlayerPickupRange());

                entities.forEach(entity -> {
                    Serializable type = entity.getType();
                    if (entity.getType() == EntityType.FOOD_ITEM) {
                        FoodItem food = getCookBook().getFoodByName(entity.getProperties().getString("name"));
                        PlayerBag.addFoodToInventory(food);
                        entity.removeFromWorld();
                    } else if (entity.getType() == EntityType.MEAL_PLATE) {
                        if(!UIViewer.currentUI.equals(UIViewer.MEAL)){
                            getMeal().setDrink(getCookBook().getFoodByName("Milk"));
                            getMeal().setMain(getCookBook().getFoodByName("Ribs"));
                            UIViewer.showMeal();
                        } else {
                            UIViewer.clearUI();
                        }
                    }
                });
            }
        }, interact);
        FXGL.getInput().addAction(new UserAction("Inventory") {
            @Override
            protected void onActionEnd() {
                if(!UIViewer.getActiveUI().equals(UIViewer.INVENTORY)){
                    UIViewer.showInventory();
                } else {
                    UIViewer.showGameUI();
                }

            }
        }, inventory);
        FXGL.getInput().addAction(new UserAction("Cookbook") {
            @Override
            protected void onActionEnd() {
                if(!UIViewer.getActiveUI().equals(UIViewer.COOKBOOK)){
                    UIViewer.showCookbook();
                } else {
                    UIViewer.showGameUI();
                }
            }
        }, cookBook);
        FXGL.getInput().addAction(new UserAction("Cook") {
            @Override
            protected void onActionEnd() {
                FoodItem bread = getCookBook().getFoodByName("Bread");
                FoodItem jam = getCookBook().getFoodByName("Strawberry Jam");
                if(PlayerBag.getInventory().contains(bread) && PlayerBag.getInventory().contains(jam)){
                    FoodItem sandwich =  getCookBook().getRecipeByOutput("Sandwich").getOutput();
                    PlayerBag.addFoodToInventory(sandwich);
                    PlayerBag.removeFoodFromInventory(bread);
                    PlayerBag.removeFoodFromInventory(jam);
                }
            }
        }, KeyCode.V);

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

        player = new Player(physics);
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setDeveloperMenuEnabled(true);
        settings.setWidth(windowWidth);
        settings.setHeight(windowHeight);
        settings.setTitle("Finding Fruitcake: A Stolen Supper Story");
        settings.setVersion("0.1");
    }

    public static void main(String[] args) {
        launch(args);

    }

}