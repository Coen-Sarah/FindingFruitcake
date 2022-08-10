package com.example.findingfruitcake;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.geometry.Point2D;
import javafx.util.Duration;

public class AnimationComponent extends Component {

    private int verticalSpeed = 0;
    private int horizontalSpeed = 0;

    private AnimatedTexture texture;
    private AnimationChannel idleLeft, idleRight, idleUp, idleDown;
    private AnimationChannel walkLeft, walkRight, walkUp, walkDown;

    public AnimationComponent() {
        int tilePixels = 16;
        idleLeft = new AnimationChannel(FXGL.image("playerIdle.png"), 24, tilePixels, tilePixels*2, Duration.seconds(1), 12, 17);
        idleRight = new AnimationChannel(FXGL.image("playerIdle.png"), 24, tilePixels, tilePixels*2, Duration.seconds(1), 0, 5);
        idleUp = new AnimationChannel(FXGL.image("playerIdle.png"), 24, tilePixels, tilePixels*2, Duration.seconds(1), 6, 11);
        idleDown = new AnimationChannel(FXGL.image("playerIdle.png"), 24, tilePixels, tilePixels*2, Duration.seconds(1), 18, 23);

        walkLeft = new AnimationChannel(FXGL.image("playerWalk.png"), 24, tilePixels, tilePixels*2, Duration.seconds(1), 12, 17);
        walkRight = new AnimationChannel(FXGL.image("playerWalk.png"), 24, tilePixels, tilePixels*2, Duration.seconds(1), 0, 5);
        walkUp = new AnimationChannel(FXGL.image("playerWalk.png"), 24, tilePixels, tilePixels*2, Duration.seconds(1), 6, 11);
        walkDown = new AnimationChannel(FXGL.image("playerWalk.png"), 24, tilePixels, tilePixels*2, Duration.seconds(1), 18, 23);

        texture = new AnimatedTexture(idleDown);
    }

    @Override
    public void onAdded() {
        entity.getTransformComponent().setScaleOrigin(new Point2D(16, 21));
        entity.getViewComponent().addChild(texture);
    }

    @Override
    public void onUpdate(double tpf) {
        entity.translateX(horizontalSpeed * tpf);
        entity.translateY(verticalSpeed * tpf);
        /*if(speed == 0){
            texture.loopAnimationChannel(idleUp);
        }*/
    }

    public void moveRight() {
        System.out.println("right");
        horizontalSpeed = 50;
        verticalSpeed = 0;
        if (texture.getAnimationChannel() != walkRight) {
            texture.loopAnimationChannel(walkRight);
        }
    }

    public void idleRight() {
        System.out.println("idle right");
        horizontalSpeed = 0;
        verticalSpeed = 0;
        if (texture.getAnimationChannel() != idleRight) {
            texture.loopAnimationChannel(idleRight);
        }
    }

    public void moveLeft() {
        System.out.println("left");
        horizontalSpeed = -50;
        verticalSpeed = 0;
        if (texture.getAnimationChannel() != walkLeft) {
            texture.loopAnimationChannel(walkLeft);
        }
    }

    public void idleLeft() {
        System.out.println("idle left");
        horizontalSpeed = 0;
        verticalSpeed = 0;
        if (texture.getAnimationChannel() != idleLeft) {
            texture.loopAnimationChannel(idleLeft);
        }
    }
    public void moveUp() {
        System.out.println("up");
        horizontalSpeed = 0;
        verticalSpeed = -50;
        if (texture.getAnimationChannel() != walkUp) {
            texture.loopAnimationChannel(walkUp);
        }
    }

    public void idleUp() {
        System.out.println("idle up");
        horizontalSpeed = 0;
        verticalSpeed = 0;
        if (texture.getAnimationChannel() != idleUp) {
            texture.loopAnimationChannel(idleUp);
        }
    }

    public void moveDown() {
        System.out.println("down");
        horizontalSpeed = 0;
        verticalSpeed = 50;
        if (texture.getAnimationChannel() != walkDown) {
            texture.loopAnimationChannel(walkDown);
        }
    }

    public void idleDown() {
        System.out.println("idle Down");
        horizontalSpeed = 0;
        verticalSpeed = 0;
        if (texture.getAnimationChannel() != idleDown) {
            texture.loopAnimationChannel(idleDown);
        }
    }
}
