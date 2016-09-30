package com.pashkobohdan.flappybird.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.pashkobohdan.flappybird.library.libGdxWorker.CollisionWorker.PolygonArray;

import java.util.Random;

/**
 * Created by bohdan on 23.07.16.
 *
 */
public class Tube {
    //public static final int TUBE_WIDTH = 26;
    public static final int FLUCTUATION = 65;
    public static final int TUBE_CAP = 50;
    public static final int LOWEST_OPENING = 75;

    public static final float[] bottomTubeVertices= {
            0,159,
            25,159,
            25,148,
            24, 147,
            24,0,
            1,0,
            1, 147,
            0,148
    };

    public static final float[] topTubeVertices  = {
            1,159,
            24,159,
            24,12,
            25, 11,
            25,0,
            0,0,
            0,11,
            1,12
    };

//    public static final in    t MAX_TOP_TUBE_POSITION = 120;
//    public static final int MIN_TOP_TUBE_POSITION = 125;
//    public static final int TUBE_CAP = 50;
//    public static final int LOWEST_OPENING = 60;

    private Vector2 posTopTube, posBottomTube;
    private Random rand;

    private PolygonArray boundsTop;
    private PolygonArray boundsBottom;
    private boolean wasUsed;

    public Tube(float x) {

        rand = new Random();

        posTopTube = new Vector2(x, rand.nextInt(FLUCTUATION) + TUBE_CAP + LOWEST_OPENING);
        posBottomTube = new Vector2(x, posTopTube.y - TUBE_CAP - AssetsLoader.bottomTube.getHeight());

        boundsTop = new PolygonArray(topTubeVertices);
        getBoundsTop().getPolygon().setOrigin(12.5f, 75.5f);
        getBoundsTop().getPolygon().setPosition(posTopTube.x, posTopTube.y);

        boundsBottom = new PolygonArray(bottomTubeVertices);
        getBoundsBottom().getPolygon().setOrigin(12.5f, 75.5f);
        getBoundsBottom().getPolygon().setPosition(posBottomTube.x, posBottomTube.y);

//        boundsTop = new Rectangle(posTopTube.x, posTopTube.y, AssetsLoader.topTube.getWidth(), AssetsLoader.topTube.getHeight());
//        boundsBottom = new Rectangle(posBottomTube.x, posBottomTube.y, AssetsLoader.bottomTube.getWidth(), AssetsLoader.bottomTube.getHeight());
    }

    public Texture getTopTube() {
        return AssetsLoader.topTube;
    }

    public Texture getBottomTube() {
        return AssetsLoader.bottomTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBottomTube() {
        return posBottomTube;
    }

    public void changex(float x) {
        posTopTube.x = x;
        posBottomTube.x = x;

        getBoundsTop().getPolygon().setPosition(posTopTube.x, posTopTube.y);
        getBoundsBottom().getPolygon().setPosition(posBottomTube.x, posBottomTube.y);
    }

    public void reposition(float x) {
        posTopTube.set(x, rand.nextInt(FLUCTUATION) + TUBE_CAP + LOWEST_OPENING);
        posBottomTube.set(x, posTopTube.y - TUBE_CAP - AssetsLoader.bottomTube.getHeight());

        getBoundsTop().getPolygon().setPosition(posTopTube.x, posTopTube.y);
        getBoundsBottom().getPolygon().setPosition(posBottomTube.x, posBottomTube.y);
    }

//    public boolean collides(Rectangle player) {
//        return player.overlaps(boundsTop) || player.overlaps(boundsBottom);
//    }

    public void dispose() {
        //topTube.dispose();
        //bottomTube.dispose();
    }

    public boolean isWasUsed() {
        return wasUsed;
    }

    public void setWasUsed(boolean wasUsed) {
        this.wasUsed = wasUsed;
    }

    public PolygonArray getBoundsTop() {
        return boundsTop;
    }

    public PolygonArray getBoundsBottom() {
        return boundsBottom;
    }
}
