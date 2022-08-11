package com.example.findingfruitcake;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.animation.Animation;
import javafx.geometry.Point2D;
import javafx.util.Duration;

public class PlayerAnimationComponent extends Component {

    private int verticalSpeed = 0;
    private int horizontalSpeed = 0;

    private AnimatedTexture texture;
    private AnimationChannel idleLeft, idleRight, idleUp, idleDown;
    private AnimationChannel walkLeft, walkRight, walkUp, walkDown;

    public PlayerAnimationComponent() {
        int tilePixels = 16;
        idleLeft = new AnimationChannel(FXGL.image("player/playerIdle.png"), 24, tilePixels, tilePixels*2, Duration.seconds(1), 12, 17);
        idleRight = new AnimationChannel(FXGL.image("player/playerIdle.png"), 24, tilePixels, tilePixels*2, Duration.seconds(1), 0, 5);
        idleUp = new AnimationChannel(FXGL.image("player/playerIdle.png"), 24, tilePixels, tilePixels*2, Duration.seconds(1), 6, 11);
        idleDown = new AnimationChannel(FXGL.image("player/playerIdle.png"), 24, tilePixels, tilePixels*2, Duration.seconds(1), 18, 23);

        walkLeft = new AnimationChannel(FXGL.image("player/playerWalk.png"), 24, tilePixels, tilePixels*2, Duration.seconds(1), 12, 17);
        walkRight = new AnimationChannel(FXGL.image("player/playerWalk.png"), 24, tilePixels, tilePixels*2, Duration.seconds(1), 0, 5);
        walkUp = new AnimationChannel(FXGL.image("player/playerWalk.png"), 24, tilePixels, tilePixels*2, Duration.seconds(1), 6, 11);
        walkDown = new AnimationChannel(FXGL.image("player/playerWalk.png"), 24, tilePixels, tilePixels*2, Duration.seconds(1), 18, 23);

        texture = new AnimatedTexture(idleDown);
    }

    @Override
    public void onAdded() {
        entity.getTransformComponent().setScaleOrigin(new Point2D(16, 21));
        entity.getViewComponent().addChild(texture);
    }

    @Override
    public void onUpdate(double tpf) {

    }

    public void moveRight() {
        horizontalSpeed = 50;
        verticalSpeed = 0;
        if (texture.getAnimationChannel() != walkRight) {
            texture.loopAnimationChannel(walkRight);
        }
    }

    public void idleRight() {
        horizontalSpeed = 0;
        verticalSpeed = 0;
        if (texture.getAnimationChannel() != idleRight) {
            texture.loopAnimationChannel(idleRight);
        }
    }

    public void moveLeft() {
        horizontalSpeed = -50;
        verticalSpeed = 0;
        if (texture.getAnimationChannel() != walkLeft) {
            texture.loopAnimationChannel(walkLeft);
        }
    }

    public void idleLeft() {
        horizontalSpeed = 0;
        verticalSpeed = 0;
        if (texture.getAnimationChannel() != idleLeft) {
            texture.loopAnimationChannel(idleLeft);
        }
    }
    public void moveUp() {
        horizontalSpeed = 0;
        verticalSpeed = -50;
        if (texture.getAnimationChannel() != walkUp) {
            texture.loopAnimationChannel(walkUp);
        }
    }

    public void idleUp() {
        horizontalSpeed = 0;
        verticalSpeed = 0;
        if (texture.getAnimationChannel() != idleUp) {
            texture.loopAnimationChannel(idleUp);
        }
    }

    public void moveDown() {
        horizontalSpeed = 0;
        verticalSpeed = 50;
        if (texture.getAnimationChannel() != walkDown) {
            texture.loopAnimationChannel(walkDown);
        }
    }

    public void idleDown() {
        horizontalSpeed = 0;
        verticalSpeed = 0;
        if (texture.getAnimationChannel() != idleDown) {
            texture.loopAnimationChannel(idleDown);
        }
    }

    public String getCurrentDirection(){
        if(texture.getAnimationChannel() == idleLeft || texture.getAnimationChannel() == walkLeft){
            return "Left";
        } else if(texture.getAnimationChannel() == idleRight || texture.getAnimationChannel() == walkRight){
            return "Right";
        } else if(texture.getAnimationChannel() == idleUp || texture.getAnimationChannel() == walkUp){
            return "Up";
        } else if(texture.getAnimationChannel() == idleDown || texture.getAnimationChannel() == walkDown){
            return "Down";
        }
        return "";
    }
}
