package com.pashkobohdan.flappybird.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by bohdan on 23.07.16.
 */
public class Tube {
    public static final int TUBE_WIDTH = 52;
    public static final int FLUCTUATION = 130;
    public static final int TUBE_CAP = 100;
    public static final int LOWEST_OPENING = 120;

    private Texture topTube, bottomTube;
    private Vector2 posTopTube, posBottomTube;
    private Random rand;

    private Rectangle boundsTop, boundsBottom;

    public Tube(float x) {
        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");

        rand = new Random();

        posTopTube = new Vector2(x, rand.nextInt(FLUCTUATION) + TUBE_CAP + LOWEST_OPENING);
        posBottomTube = new Vector2(x, posTopTube.y - TUBE_CAP - bottomTube.getHeight());

        boundsTop = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight());
        boundsBottom = new Rectangle(posBottomTube.x, posBottomTube.y, bottomTube.getWidth(), bottomTube.getHeight());
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBottomTube() {
        return posBottomTube;
    }

    public void reposition(float x) {
        posTopTube.set(x, rand.nextInt(FLUCTUATION) + TUBE_CAP + LOWEST_OPENING);
        posBottomTube.set(x, posTopTube.y - TUBE_CAP - bottomTube.getHeight());

        boundsTop.setPosition(posTopTube.x, posTopTube.y);
        boundsBottom.setPosition(posBottomTube.x, posBottomTube.y);
    }

    public boolean collides(Rectangle player) {
        return player.overlaps(boundsTop) || player.overlaps(boundsBottom);
    }

    public void dispose() {
        topTube.dispose();
        bottomTube.dispose();
    }
}
