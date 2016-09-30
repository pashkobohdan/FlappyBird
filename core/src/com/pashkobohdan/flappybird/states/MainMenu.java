package com.pashkobohdan.flappybird.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pashkobohdan.flappybird.FlappyBird;
import com.pashkobohdan.flappybird.googlePlayServices.PlayServices;
import com.pashkobohdan.flappybird.library.button.Button;
import com.pashkobohdan.flappybird.library.button.core.interfaces.ClickListener;
import com.pashkobohdan.flappybird.sprites.AssetsLoader;

/**
 * Created by bohdan on 28.09.16.
 */
public class MainMenu extends State {

    public MainMenu(GameStateManeger gsm) {
        super(gsm);

        getCamera().setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);

        final GameStateManeger gameStateManeger = gsm;
        AssetsLoader.playButton.addClickListener(new ClickListener() {
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

    }

    @Override
    public void update(float dt) {
        // bird and text updating (sin, cos)
        AssetsLoader.playButton.update(dt);
    }

    @Override
    public void render(SpriteBatch spriteBatch) {

        spriteBatch.setProjectionMatrix(getCamera().combined);
        spriteBatch.begin();


        spriteBatch.draw(AssetsLoader.backgroundDay, 0, 0);
        AssetsLoader.playButton.render(spriteBatch);
        AssetsLoader.scoreButton.render(spriteBatch);
        AssetsLoader.rateButton.render(spriteBatch);

        AssetsLoader.flappyBirdTextShadow.render(spriteBatch);
        AssetsLoader.flappyBirdTextFont.render(spriteBatch);


        spriteBatch.end();
    }

    @Override
    public void dispose() {

    }
}
