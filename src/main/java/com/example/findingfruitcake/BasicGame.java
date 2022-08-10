package com.example.findingfruitcake;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import javafx.scene.input.KeyCode;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameWorld;

public class BasicGame extends GameApplication {

    private int tileSize = 16;
    private int windowHeight = tileSize * 25;
    private int windowWidth = tileSize * 30;
    private Entity player;

    @Override
    protected void initInput() {
        FXGL.getInput().addAction(new UserAction("Right") {
            @Override
            protected void onAction() {
                player.getComponent(AnimationComponent.class).moveRight();
            }

            @Override
            protected void onActionEnd() {
                player.getComponent(AnimationComponent.class).idleRight();
            }
        }, KeyCode.D);

        FXGL.getInput().addAction(new UserAction("Left") {
            @Override
            protected void onAction() {
                player.getComponent(AnimationComponent.class).moveLeft();
            }

            @Override
            protected void onActionEnd() {
                player.getComponent(AnimationComponent.class).idleLeft();
            }
        }, KeyCode.A);
        FXGL.getInput().addAction(new UserAction("Up") {

            @Override
            protected void onAction() {
                player.getComponent(AnimationComponent.class).moveUp();
            }

            @Override
            protected void onActionEnd() {
                player.getComponent(AnimationComponent.class).idleUp();
            }
        }, KeyCode.W);

        FXGL.getInput().addAction(new UserAction("Down") {
            @Override
            protected void onAction() {
                player.getComponent(AnimationComponent.class).moveDown();
            }

            @Override
            protected void onActionEnd() {
                player.getComponent(AnimationComponent.class).idleDown();
            }
        }, KeyCode.S);
    }

    @Override
    protected void initGame() {

        getGameWorld().addEntityFactory(new MapFactory());
        FXGL.setLevelFromMap("HomeFirstFloor.tmx");
        player = FXGL.entityBuilder()
                .at(135, 135)
                .with(new AnimationComponent())
                .buildAndAttach();
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(windowWidth);
        settings.setHeight(windowHeight);
        settings.setTitle("Basic Game App");
        settings.setVersion("0.1");
    }

    public static void main(String[] args) {
        launch(args);
    }

}