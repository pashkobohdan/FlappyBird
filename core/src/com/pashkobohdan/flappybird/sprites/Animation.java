package com.pashkobohdan.flappybird.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.pashkobohdan.flappybird.library.libGdxWorker.CollisionWorker.PolygonArray;

/**
 * Created by bohdan on 24.07.16.
 */
//public class Animation {
//    private Array<TextureRegion> sprites;
//    private float maxFrameTime;
//    private float currentFrameTime;
//    private int frameCount;
//    private int frame;
//
//    public Animation(TextureRegion textureRegion, int frameCount, float cycleTime) {
//        sprites = new Array<TextureRegion>();
//        int frameWidth = textureRegion.getRegionWidth() / frameCount;
//
//        for (int i = 0; i < frameCount; i++) {
//            sprites.add(new TextureRegion(textureRegion, i * frameWidth, 0, frameWidth, textureRegion.getRegionHeight()));
//        }
//
//
//        this.frameCount = frameCount;
//        maxFrameTime = cycleTime / frameCount;
//        frame = 0;
//    }
//
//
//    public void update(float dt) {
//        currentFrameTime += dt;
//        if (currentFrameTime > maxFrameTime) {
//            frame++;
//            currentFrameTime = 0;
//        }
//        if (frame >= frameCount) {
//            frame = 0;
//        }
//    }
//
//    public TextureRegion getFrame() {
//        return sprites.get(frame);
//    }
//
//}

public class Animation {
    public static final int MOVEMENT = 60;
    public static final int GRAVITY = -800;

    private Array<Sprite> sprites;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int frame;

    private PolygonArray penPolygon;

    private Vector3 position;
    private Vector3 velocity;

    private int rotate = 0;

    public Animation(TextureRegion textureRegion, int frameCount, float cycleTime, int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);

        sprites = new Array<Sprite>();
        int frameWidth = textureRegion.getRegionWidth() / frameCount;

        Sprite sprite;
        for (int i = 0; i < frameCount; i++) {
            sprite = new Sprite(textureRegion, i * frameWidth, 0, frameWidth, textureRegion.getRegionHeight());
            sprites.add(sprite);
            sprite.setPosition(position.x, position.y);
        }

        float[] vertices = new float[]{
                6, 11,
                11, 11,
                14, 8,
                16, 4,
                14, 1,
                9, 0,
                5, 0,
                0, 5,
                0, 7
        };

        penPolygon = new PolygonArray(vertices);
        getPenPolygon().getPolygon().setOrigin(8f, 5.5f);
        penPolygon.getPolygon().setPosition(position.x, position.y);

        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }


    public void update(float dt) {
        currentFrameTime += dt;
        if (currentFrameTime > maxFrameTime) {
            frame++;
            currentFrameTime = 0;
        }
        if (frame >= frameCount) {
            frame = 0;
        }


        if (getPosition().y > 0)
            velocity.add(0, GRAVITY * dt, 0);
        //velocity.scl(dt);

        //System.out.println("    y speed : " + velocity.y);

        getPosition().add(0, velocity.y * dt, 0);//MOVEMENT * dt


        rotate += velocity.y * dt;
        if (rotate <= -90) {
            rotate = -90;
        }
        //
        //
        //
        for (Sprite sprite : sprites) {
            sprite.setPosition(position.x, position.y);
            sprite.setRotation(rotate);
        }

        getPenPolygon().getPolygon().setPosition(position.x, position.y);
        getPenPolygon().getPolygon().setRotation(rotate);
        getPenPolygon().transformPolygonToPoints();

        //
        //


        //System.out.println("pos : " + position);

        if (getPosition().y < 0)
            getPosition().y = 0;
    }

    public void jump() {
        velocity.y = 230;//250;
        setRotate(5);

        AssetsLoader.fly.play();
    }

    public Sprite getFrame() {
        return sprites.get(frame);
    }

    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public int getRotate() {
        return rotate;
    }

    public void setRotate(int rotate) {
        this.rotate = rotate;
    }

    public PolygonArray getPenPolygon() {
        return penPolygon;
    }
}
