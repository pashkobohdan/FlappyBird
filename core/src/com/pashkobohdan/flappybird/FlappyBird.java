package com.pashkobohdan.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pashkobohdan.flappybird.googlePlayServices.PlayServices;
import com.pashkobohdan.flappybird.sprites.AssetsLoader;
import com.pashkobohdan.flappybird.states.GameStateManeger;
import com.pashkobohdan.flappybird.states.MainMenu;

public class FlappyBird extends ApplicationAdapter {
    public static final int WIDTH = 288;
    public static final int HEIGHT = 512;

    public static final String TITLE = "Flappy Bird";


    private GameStateManeger gameStateManeger;
    private SpriteBatch batch;

    public static PlayServices playServices;

    public FlappyBird (PlayServices playServices){
        this.playServices = playServices;

        playServices.signIn();
        //playServices.signOut();
        if(!playServices.isSignedIn()){
            playServices = null;
        }
    }

    public FlappyBird(){

    }

    @Override
    public void create() {
        batch = new SpriteBatch();

        gameStateManeger = new GameStateManeger();

//        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
//        music.setLooping(true);
//        music.setVolume(0.1f);
//        music.play();

        Gdx.gl.glClearColor(1, 1, 1, 1);


        OrthographicCamera firstCamera = new OrthographicCamera();
        firstCamera.setToOrtho(false, FlappyBird.WIDTH/2, FlappyBird.HEIGHT/2);
        AssetsLoader.load(firstCamera);

        gameStateManeger.push(new MainMenu(gameStateManeger));

    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameStateManeger.update(Gdx.graphics.getDeltaTime());
        gameStateManeger.render(batch);
    }

    @Override
    public void dispose() {
        //music.dispose();
    }
}
