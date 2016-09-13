package com.pashkobohdan.flappybird.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Bohdan Pashko on 23.07.16.
 */
public class GameStateManeger {

    private State currentState;


    public void push(State state) {
        currentState = state;
    }

    public void set(State state) {
        currentState.dispose();

        push(state);
    }

    public void update(float dt) {
        currentState.update(dt);
    }

    public void render(SpriteBatch spriteBatch) {
        currentState.render(spriteBatch);
    }


}
