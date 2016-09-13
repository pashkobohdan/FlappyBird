package com.pashkobohdan.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pashkobohdan.flappybird.FlappyBird;

/**
 * Created by bohdan on 23.07.16.
 */
public class GameOver extends State {

    private Texture background;
    private Texture gameOver;

    public GameOver(GameStateManeger gsm) {
        super(gsm);
        camera.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);
        background = new Texture("bg.png");
        gameOver = new Texture("gameover.png");
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();

        spriteBatch.draw(background, 0, 0);
        spriteBatch.draw(gameOver, camera.position.x - gameOver.getWidth() / 2, camera.position.y);

        spriteBatch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        gameOver.dispose();
    }
}
