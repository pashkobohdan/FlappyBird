package com.pashkobohdan.flappybird.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.pashkobohdan.flappybird.library.button.Button;
import com.pashkobohdan.flappybird.library.textView.TextView;

/**
 * Created by bohdan on 14.09.16.
 */
public class AssetsLoader {
    public static Texture backgroundDay, backgroundNight;
    public static Texture birdAnimation;
    public static Texture bottomTube;
    public static Texture topTube;
    public static Texture flappyBirdText;
    public static Texture gameOverText;
    public static Texture getReadyText;
    public static Texture ground;
    public static Texture okButtonTexture;
    public static Texture pauseButtonTexture;
    public static Texture playBigButtonTexture;
    public static Texture rateBigButtonTexture;
    public static Texture scoreBigButtonTexture;
    public static Texture shareBigButtonTexture;
    public static Texture tapToPlay;
    public static Texture scoreBackground;

    public static TextView getReadTextFont;
    public static TextView getReadyTextShadow;
    public static TextView gameOverTyextFont;
    public static TextView gameOverTextShadow;
    public static TextView flappyBirdTextFont;
    public static TextView flappyBirdTextShadow;

    public static Button okButton;
    public static Button pauseButton;
    public static Button playButton;
    public static Button rateButton;
    public static Button scoreButton;
    public static Button shareButton;

    public static BitmapFont font;
    public static BitmapFont shadow;
    public static BitmapFont smallFont;
    public static BitmapFont smallShadow;

    public static Sound fly, death, scored;

    public static int currentHighScore;

    //    public static Sound dead, flap, coin;
//    public static BitmapFont font, shadow;
    private static Preferences prefs;

    public static void load(final OrthographicCamera camera) {

        backgroundDay = new Texture(Gdx.files.internal("data/pic/background_day.png"));
        backgroundNight = new Texture(Gdx.files.internal("data/pic/background_night.png"));
        birdAnimation = new Texture(Gdx.files.internal("data/pic/birdanimation.png"));
        bottomTube = new Texture(Gdx.files.internal("data/pic/bottom_tube.png"));
        topTube = new Texture(Gdx.files.internal("data/pic/top_tube.png"));
        flappyBirdText = new Texture(Gdx.files.internal("data/pic/flappy_bird_text.png"));
        gameOverText = new Texture(Gdx.files.internal("data/pic/game_over.png"));
        getReadyText = new Texture(Gdx.files.internal("data/pic/get_ready.png"));
        ground = new Texture(Gdx.files.internal("data/pic/ground.png"));
        okButtonTexture = new Texture(Gdx.files.internal("data/pic/ok_button.png"));
        pauseButtonTexture = new Texture(Gdx.files.internal("data/pic/pause_button.png"));
        playBigButtonTexture = new Texture(Gdx.files.internal("data/pic/play_big_button.png"));
        rateBigButtonTexture = new Texture(Gdx.files.internal("data/pic/rate_big_button.png"));
        scoreBigButtonTexture = new Texture(Gdx.files.internal("data/pic/score_big_button.png"));
        shareBigButtonTexture = new Texture(Gdx.files.internal("data/pic/share_button.png"));
        tapToPlay = new Texture(Gdx.files.internal("data/pic/tap_to_play.png"));
        scoreBackground = new Texture(Gdx.files.internal("data/pic/score_background.png"));


        font = new BitmapFont(Gdx.files.internal("data/font/text.fnt"));
        shadow = new BitmapFont(Gdx.files.internal("data/font/shadow.fnt"));
        smallFont = new BitmapFont(Gdx.files.internal("data/font/text.fnt"));
        smallShadow = new BitmapFont(Gdx.files.internal("data/font/shadow.fnt"));

        // game menu
        getReadTextFont = new TextView("Get Ready", (int) camera.position.x, (int) (camera.position.y + camera.viewportHeight / 4),
                0.25f, font, Color.WHITE);
        getReadyTextShadow = new TextView("Get Ready", (int) camera.position.x - 1, (int) (camera.position.y + camera.viewportHeight / 4) - 1,
                0.25f, shadow, Color.WHITE);

        // game iver menu
        gameOverTyextFont = new TextView("Game Over", (int) camera.position.x, (int) (camera.position.y + camera.viewportHeight / 4),
                0.25f, font, Color.WHITE);
        gameOverTextShadow = new TextView("Game Over", (int) camera.position.x - 1, (int) (camera.position.y + camera.viewportHeight / 4) - 1,
                0.25f, shadow, Color.WHITE);

        // main menu
        flappyBirdTextFont = new TextView("Flappy Bird", (int) camera.position.x, (int) (camera.position.y + camera.viewportHeight / 4),
                0.25f, font, Color.WHITE);
        flappyBirdTextShadow = new TextView("Flappy Bird", (int) camera.position.x - 1, (int) (camera.position.y + camera.viewportHeight / 4) - 1,
                0.25f, shadow, Color.WHITE);


        // game menu
        pauseButton = new Button(camera, pauseButtonTexture, pauseButtonTexture,
                (int) (camera.position.x - 3 * camera.viewportWidth / 8), (int) (camera.position.y + 3 * camera.viewportHeight / 8),
                pauseButtonTexture.getWidth(), pauseButtonTexture.getHeight());


        // game over menu
        okButton = new Button(camera, okButtonTexture, okButtonTexture,
                (int) (camera.position.x - camera.viewportWidth / 4), (int) (camera.position.y - camera.viewportHeight / 4),
                okButtonTexture.getWidth(), okButtonTexture.getHeight());

        shareButton = new Button(camera, shareBigButtonTexture, shareBigButtonTexture,
                (int) (camera.position.x + camera.viewportWidth / 4), (int) (camera.position.y - camera.viewportHeight / 4),
                shareBigButtonTexture.getWidth(), shareBigButtonTexture.getHeight());


        // main menu
        playButton = new Button(camera, playBigButtonTexture, playBigButtonTexture,
                (int) (camera.position.x - camera.viewportWidth / 4), (int) (camera.position.y - camera.viewportHeight / 4),
                playBigButtonTexture.getWidth(), playBigButtonTexture.getHeight());

        scoreButton = new Button(camera, scoreBigButtonTexture, scoreBigButtonTexture,
                (int) (camera.position.x + camera.viewportWidth / 4), (int) (camera.position.y - camera.viewportHeight / 4),
                scoreBigButtonTexture.getWidth(), scoreBigButtonTexture.getHeight());

        rateButton = new Button(camera, rateBigButtonTexture, rateBigButtonTexture,
                (int) (camera.position.x + camera.viewportWidth / 4), scoreButton.getCenterY() + scoreButton.getHeight(),
                rateBigButtonTexture.getWidth(), rateBigButtonTexture.getHeight());


        fly = Gdx.audio.newSound(Gdx.files.internal("data/sound/fly.mp3"));
        death = Gdx.audio.newSound(Gdx.files.internal("data/sound/death.mp3"));
        scored = Gdx.audio.newSound(Gdx.files.internal("data/sound/scored.mp3"));

//        texture = new Texture(Gdx.files.internal("data/texture.png"));
//        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
//
//        bg = new TextureRegion(texture, 0, 0, 136, 43);
//        bg.flip(false, true);
//
//        grass = new TextureRegion(texture, 0, 43, 143, 11);
//        grass.flip(false, true);
//
//        birdDown = new TextureRegion(texture, 136, 0, 17, 12);
//        birdDown.flip(false, true);
//
//        bird = new TextureRegion(texture, 153, 0, 17, 12);
//        bird.flip(false, true);
//
//        birdUp = new TextureRegion(texture, 170, 0, 17, 12);
//        birdUp.flip(false, true);
//
//        TextureRegion[] birds = {birdDown, bird, birdUp};
//        birdAnimation = new Animation(0.06f, birds);
//        birdAnimation.setPlayMode(Animation.LOOP_PINGPONG);
//
//        skullUp = new TextureRegion(texture, 192, 0, 24, 14);
//        skullDown = new TextureRegion(skullUp);
//        skullDown.flip(false, true);
//
//        bar = new TextureRegion(texture, 136, 16, 22, 3);
//        bar.flip(false, true);

//        dead = Gdx.audio.newSound(Gdx.files.internal("data/dead.wav"));
//        flap = Gdx.audio.newSound(Gdx.files.internal("data/flap.wav"));
//        coin = Gdx.audio.newSound(Gdx.files.internal("data/coin.wav"));

//        font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
//        font.setScale(.25f, -.25f);
//        shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
//        shadow.setScale(.25f, -.25f);

        // Получим (или создадим) preferences
        prefs = Gdx.app.getPreferences("highScore_flappyBird");

        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
            prefs.flush();
        }
        currentHighScore = prefs.getInteger("highScore", 0);
    }

    public static void writeNewScore(int newScore) {
        if (newScore > currentHighScore){
            currentHighScore = newScore;
            prefs.putInteger("highScore", currentHighScore);
            prefs.flush();
        }
    }

}