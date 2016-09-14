package com.pashkobohdan.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pashkobohdan.flappybird.FlappyBird;
import com.pashkobohdan.flappybird.library.button.Button;
import com.pashkobohdan.flappybird.library.button.core.interfaces.ClickListener;

/**
 * Created by bohdan on 23.07.16.
 */
public class MenuState extends State {

    private Texture background;
    private Texture playButton;

    private Button play;


    public MenuState(GameStateManeger gsm) {
        super(gsm);
        camera.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);
        background = new Texture("bg.png");
        //playButton = new Texture("playbtn.png");

        play = new Button(camera, "Ahaha", new Texture("playbtn.png"), new Texture("toptube.png"), (int) camera.position.x, (int) camera.position.y, 100, 30);

        final GameStateManeger gameStateManeger = gsm;
        play.addClickListener(new ClickListener() {
            @Override
            public void oneClick(Button button, int x, int y) {

            }

            @Override
            public void postClick(Button button, int x, int y) {
                gameStateManeger.set(new PlayState(gameStateManeger));
            }

            @Override
            public void doubleClick(Button button, int x, int y) {

            }
        });
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            //gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        play.update(dt);
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();

        spriteBatch.draw(background, 0, 0);
        play.render(spriteBatch);
        //spriteBatch.draw(playButton, camera.position.x - playButton.getWidth() / 2, camera.position.y);

        spriteBatch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        //playButton.dispose();
    }
}
