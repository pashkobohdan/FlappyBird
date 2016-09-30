package com.pashkobohdan.flappybird.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.pashkobohdan.flappybird.library.libGdxWorker.CollisionWorker.PolygonArray;


/**
 * Created by bohdan on 23.07.16.
 */
public class Bird {


    //private Rectangle bounds;
    private Animation birdAnimation;

    public Bird(int x, int y) {
        birdAnimation = new Animation(new TextureRegion(AssetsLoader.birdAnimation), 3, 0.2f, x, y);

        //bounds = new Rectangle(x, y, AssetsLoader.birdAnimation.getWidth() / 3, AssetsLoader.birdAnimation.getHeight());

    }

    public Vector3 getPosition() {
        return birdAnimation.getPosition();
    }

    public Sprite getBird() {
        return birdAnimation.getFrame();
    }

    public void update(float dt) {
        birdAnimation.update(dt);



        //velocity.scl(1 / dt);

        //bounds.setPosition(birdAnimation.getPosition().x, birdAnimation.getPosition().y);
    }

    public void jump() {
        birdAnimation.jump();
        ///AssetsLoader.fly.play();
    }

    public PolygonArray getBounds() {
        return birdAnimation.getPenPolygon();
    }

    public void dispose() {
        //texture.dispose();
        //flap.dispose();
    }
}