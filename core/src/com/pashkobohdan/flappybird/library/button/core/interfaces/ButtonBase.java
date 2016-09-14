package com.pashkobohdan.flappybird.library.button.core.interfaces;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Bohdan Pashko on 14.09.16.
 */
public interface ButtonBase {

    void update(float dt);

    void render(SpriteBatch spriteBatch);

    void dispose();

    void handleInput();

    void addClickListener(ClickListener oneClickListener);

    void resetButtonEdges();
}
