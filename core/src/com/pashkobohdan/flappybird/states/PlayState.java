package com.pashkobohdan.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.pashkobohdan.flappybird.FlappyBird;
import com.pashkobohdan.flappybird.sprites.Bird;
import com.pashkobohdan.flappybird.sprites.Tube;

/**
 * Created by bohdan on 23.07.16.
 */
public class PlayState extends State {
    public static final int TUBE_SPACING = 125;
    public static final int TUBE_COUNT = 4;
    public static final int GROUND_Y_OFFSET = -30;


    private Bird bird;
    private Texture bg;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;

    private Array<Tube> tubes;

    public PlayState(GameStateManeger gsm) {
        super(gsm);

        camera.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);

        bird = new Bird(50,300);
        bg = new Texture("bg.png");
        tubes = new Array<Tube>();
        ground = new Texture("ground.png");
        groundPos1 = new Vector2(camera.position.x - camera.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2(camera.position.x - camera.viewportWidth / 2 + ground.getWidth(), GROUND_Y_OFFSET);


        for (int i = 0; i < TUBE_COUNT; i++) {
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            bird.jump();
        }
    }


    private float totalTime = 0;
    private int frameCount = 0;
    private int fps = 0;
    private BitmapFont font = new BitmapFont();

    @Override
    public void update(float dt) {
        totalTime += dt;
        frameCount++;
        if (totalTime >= 1.0f) {
            fps = frameCount;

            frameCount = 0;
            totalTime = 0.0f;
        }

        handleInput();
        uodateGroud();

        bird.update(dt);

        camera.position.x = bird.getPosition().x + 80;

        for (int i = 0; i < tubes.size; i++) {
            Tube tube = tubes.get(i);

            if (camera.position.x - (camera.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }
            if (tube.collides(bird.getBounds())) {
                gsm.set(new GameOver(gsm));
            }
        }

        camera.update();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);

        spriteBatch.begin();

        spriteBatch.draw(bg, camera.position.x - camera.viewportWidth / 2, 0);
        spriteBatch.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);

        for (Tube tube : tubes) {
            spriteBatch.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            spriteBatch.draw(tube.getBottomTube(), tube.getPosBottomTube().x, tube.getPosBottomTube().y);
        }
        spriteBatch.draw(ground, groundPos1.x, groundPos1.y);
        spriteBatch.draw(ground, groundPos2.x, groundPos2.y);

        font.draw(spriteBatch, "fps : "+fps,camera.position.x - camera.viewportWidth / 2,camera.position.y + camera.viewportHeight / 2 -10);

        spriteBatch.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        ground.dispose();
        for (Tube tube : tubes) {
            tube.dispose();
        }
    }

    private void uodateGroud() {
        if (camera.position.y + (camera.viewportWidth / 2) < groundPos1.y + ground.getWidth()) {
            groundPos1.add(ground.getWidth() * 2, 0);
        }
        if (camera.position.x - (camera.viewportWidth / 2) > groundPos2.x + ground.getWidth()) {
            groundPos2.add(ground.getWidth() * 2, 0);
        }
    }
}
