package com.pashkobohdan.flappybird.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by bohdan on 23.07.16.
 */
public abstract class State {

    private OrthographicCamera camera;
    protected Vector3 mouse;
    static protected GameStateManeger gsm;

    public State(GameStateManeger gsm){
        this.gsm = gsm;

        camera =new OrthographicCamera();
        mouse = new Vector3();
    }

    protected abstract void handleInput();

    public abstract void update(float dt);

    public abstract void render(SpriteBatch spriteBatch);

    public abstract void dispose();

    public OrthographicCamera getCamera() {
        return camera;
    }
}
