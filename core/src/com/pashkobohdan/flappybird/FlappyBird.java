package com.pashkobohdan.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pashkobohdan.flappybird.states.GameStateManeger;
import com.pashkobohdan.flappybird.states.MenuState;

public class FlappyBird extends ApplicationAdapter {
    public static final int WIDTH = 480;
    public static final int HEIGHT = 800;

    public static final String TITLE = "Flappy Bird";


    private GameStateManeger gameStateManeger;
    private SpriteBatch batch;

    private Music music;

    @Override
    public void create() {
        batch = new SpriteBatch();

        gameStateManeger = new GameStateManeger();

        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();

        Gdx.gl.glClearColor(1, 0, 0, 1);
        gameStateManeger.push(new MenuState(gameStateManeger));
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameStateManeger.update(Gdx.graphics.getDeltaTime());
        gameStateManeger.render(batch);
    }

    @Override
    public void dispose() {
        music.dispose();
    }
}
