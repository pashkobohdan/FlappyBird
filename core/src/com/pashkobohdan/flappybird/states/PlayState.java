package com.pashkobohdan.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.pashkobohdan.flappybird.FlappyBird;
import com.pashkobohdan.flappybird.library.button.Button;
import com.pashkobohdan.flappybird.library.button.core.interfaces.ClickListener;
import com.pashkobohdan.flappybird.library.libGdxWorker.CollisionWorker.Collisions;
import com.pashkobohdan.flappybird.library.textView.TextView;
import com.pashkobohdan.flappybird.sprites.AssetsLoader;
import com.pashkobohdan.flappybird.sprites.Bird;
import com.pashkobohdan.flappybird.sprites.Tube;

/**
 * Created by bohdan on 23.07.16.
 */
public class PlayState extends State {
    public static final int TUBE_SPACING = 80;
    public static final int TUBE_COUNT = 4;
    public static final int GROUND_Y_OFFSET = 0; //-30;

    private Bird bird;
    private Vector2 groundPos1, groundPos2;

    private Array<Tube> tubes;

    private boolean getReadyActive = true, gameOverActive;
    private float getReadyBirdDelta = 0;

    private float totalTime = 0;

    private int score = 0;
    private TextView scoreText, scoreShadow, smallScoreText, smallScoreShadow, smallScoreTextHighScore, smallScoreShadowHighScore;


    private ShapeRenderer shapeRenderer;

//    private int frameCount = 0;
//    private int fps = 0;
//    private BitmapFont font = new BitmapFont();

    public PlayState(final GameStateManeger gsm) {
        super(gsm);

        getCamera().setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);

        bird = new Bird((int) (getCamera().position.x - getCamera().viewportWidth / 4), (int) getCamera().position.y);
        tubes = new Array<Tube>();
        groundPos1 = new Vector2(getCamera().position.x - getCamera().viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2(getCamera().position.x - getCamera().viewportWidth / 2 + AssetsLoader.ground.getWidth(), GROUND_Y_OFFSET);

        for (int i = 0; i < TUBE_COUNT; i++) {
            tubes.add(new Tube(i * (TUBE_SPACING) + getCamera().position.x + getCamera().viewportWidth));//+ Tube.TUBE_WIDTH
        }

        AssetsLoader.playButton.addClickListener(new ClickListener() {
            @Override
            public void oneClick(Button button, int x, int y) {

            }

            @Override
            public void postClick(Button button, int x, int y) {
                gsm.push(new MainMenu(gsm));
            }

            @Override
            public void doubleClick(Button button, int x, int y) {

            }
        });

        AssetsLoader.pauseButton.addClickListener(new ClickListener() {
            @Override
            public void oneClick(Button button, int x, int y) {

            }

            @Override
            public void postClick(Button button, int x, int y) {
                gsm.push(new MainMenu(gsm));
            }

            @Override
            public void doubleClick(Button button, int x, int y) {

            }
        });

        AssetsLoader.scoreButton.addClickListener(new ClickListener() {
            @Override
            public void oneClick(Button button, int x, int y) {

            }

            @Override
            public void postClick(Button button, int x, int y) {
                if (FlappyBird.playServices != null) {
                    FlappyBird.playServices.showScore();
                }else{
                    System.out.println("NULL !!!");
                }
            }

            @Override
            public void doubleClick(Button button, int x, int y) {

            }
        });

        scoreText = new TextView("" + score, (int) getCamera().position.x, (int) (getCamera().position.y + getCamera().viewportHeight / 4),
                0.25f, AssetsLoader.font, Color.WHITE);
        scoreShadow = new TextView("" + score, (int) getCamera().position.x - 1, (int) (getCamera().position.y + getCamera().viewportHeight / 4) - 1,
                0.25f, AssetsLoader.shadow, Color.WHITE);


        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(getCamera().combined);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            if (!gameOverActive) {
                if (getReadyActive) {
                    bird.jump();
                    getReadyActive = false;
                } else {
                    bird.jump();
                }
            }
        }
    }


    @Override
    public void update(float dt) {
        totalTime += dt;
//        frameCount++;
//        if (totalTime >= 1.0f) {
//            fps = frameCount;
//
//            frameCount = 0;
//            totalTime = 0.0f;
//        }

        handleInput();


        if (getReadyActive) {
            getReadyBirdDelta = (int) (2 * Math.sin(totalTime * 12));
            return;
        } else if (gameOverActive) {
            AssetsLoader.playButton.update(dt);
            AssetsLoader.scoreButton.update(dt);
            return;
        } else {
            AssetsLoader.pauseButton.update(dt);
        }

        updateGroud();


        bird.update(dt);

        if (bird.getPosition().y <= getCamera().position.y + AssetsLoader.ground.getHeight() - getCamera().viewportHeight / 2) {
            gameOver();
            return;
        } else if (bird.getPosition().y >= getCamera().position.y + getCamera().viewportHeight / 2 - bird.getBird().getHeight() / 2) {
            bird.getPosition().y = getCamera().position.y + getCamera().viewportHeight / 2 - bird.getBird().getHeight() / 2;
        }

        for (Tube tube : tubes) {
            tube.changex(tube.getPosBottomTube().x - 60 * dt);

            if (!tube.isWasUsed() && tube.getPosBottomTube().x + tube.getBottomTube().getWidth() / 2 < bird.getPosition().x + bird.getBird().getWidth() / 2) {
                tube.setWasUsed(true);

                AssetsLoader.scored.play();

                score++;
                scoreText.setText("" + score);
                scoreShadow.setText("" + score);
            }
        }
        groundPos1.x -= 60 * dt;
        groundPos2.x -= 60 * dt;

        for (int i = 0; i < tubes.size; i++) {
            Tube tube = tubes.get(i);
            if (getCamera().position.x - (getCamera().viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
                tube.reposition(tube.getPosTopTube().x + ((TUBE_SPACING) * TUBE_COUNT));//+ TUBE_SPACING
                tube.setWasUsed(false);
            }
            if (Intersector.overlapConvexPolygons(bird.getBounds().getPolygon(), tube.getBoundsBottom().getPolygon())
                    ||
                    Intersector.overlapConvexPolygons(bird.getBounds().getPolygon(), tube.getBoundsTop().getPolygon())) {
                gameOver();
                return;
                //gsm.set(new GameOver(gsm));
            }
        }

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(getCamera().combined);

        spriteBatch.begin();

        spriteBatch.draw(AssetsLoader.backgroundDay, getCamera().position.x - getCamera().viewportWidth / 2, 0);


        if (getReadyActive) {
            spriteBatch.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y + getReadyBirdDelta);

            spriteBatch.draw(AssetsLoader.tapToPlay,
                    getCamera().position.x - AssetsLoader.tapToPlay.getWidth() / 2,
                    getCamera().position.y - AssetsLoader.tapToPlay.getHeight() / 2);

            spriteBatch.draw(AssetsLoader.getReadyText, getCamera().position.x - AssetsLoader.getReadyText.getWidth() / 2,
                    getCamera().position.y + getCamera().viewportHeight / 4 - AssetsLoader.getReadyText.getHeight() / 2);
        } else if (gameOverActive) {
            for (Tube tube : tubes) {
                spriteBatch.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
                spriteBatch.draw(tube.getBottomTube(), tube.getPosBottomTube().x, tube.getPosBottomTube().y);
            }


            //spriteBatch.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);
            bird.getBird().draw(spriteBatch);

            spriteBatch.draw(AssetsLoader.ground, groundPos1.x, groundPos1.y);
            spriteBatch.draw(AssetsLoader.ground, groundPos2.x, groundPos2.y);

            spriteBatch.draw(AssetsLoader.gameOverText, getCamera().position.x - AssetsLoader.getReadyText.getWidth() / 2,
                    getCamera().position.y + getCamera().viewportHeight / 4 - AssetsLoader.getReadyText.getHeight() / 2);

            AssetsLoader.playButton.render(spriteBatch);
            AssetsLoader.scoreButton.render(spriteBatch);
            spriteBatch.draw(AssetsLoader.scoreBackground, getCamera().position.x - AssetsLoader.scoreBackground.getWidth() / 2,
                    getCamera().position.y - AssetsLoader.scoreBackground.getHeight() / 2);

            smallScoreShadow.render(spriteBatch);
            smallScoreText.render(spriteBatch);

            smallScoreShadowHighScore.render(spriteBatch);
            smallScoreTextHighScore.render(spriteBatch);
        } else {

            for (Tube tube : tubes) {
                spriteBatch.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
                spriteBatch.draw(tube.getBottomTube(), tube.getPosBottomTube().x, tube.getPosBottomTube().y);
            }
            //spriteBatch.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);
            bird.getBird().draw(spriteBatch);

            spriteBatch.draw(AssetsLoader.ground, groundPos1.x, groundPos1.y);
            spriteBatch.draw(AssetsLoader.ground, groundPos2.x, groundPos2.y);

            scoreShadow.render(spriteBatch);
            scoreText.render(spriteBatch);

            AssetsLoader.pauseButton.render(spriteBatch);


        }


        //font.draw(spriteBatch, "fps : "+fps, getCamera().position.x - getCamera().viewportWidth / 2, getCamera().position.y + getCamera().viewportHeight / 2 -10);

        spriteBatch.end();

//        if (!gameOverActive && !getReadyActive) {
//
//            shapeRenderer.setProjectionMatrix(getCamera().combined);
//            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//            shapeRenderer.setColor(new Color(255, 0, 0, 0.0f));
//            shapeRenderer.polygon(bird.getBounds().getPolygon().getTransformedVertices());
//
//            for (Tube tube : tubes) {
//                shapeRenderer.setColor(new Color(0, 0, 2, 0.0f));
//                shapeRenderer.polygon(tube.getBoundsBottom().getPolygon().getTransformedVertices());
//                shapeRenderer.polygon(tube.getBoundsTop().getPolygon().getTransformedVertices());
//            }
//
//            shapeRenderer.end();
//        }


//        playServices.rateGame();
//        //playServices.unlockAchievement();
//        playServices.submitScore(100000);
//        playServices.showScore();
//        //playServices.showAchievement();
//        playServices.isSignedIn();
    }

    private void gameOver() {
        gameOverActive = true;

        AssetsLoader.death.play();
        AssetsLoader.writeNewScore(score);

        smallScoreText = new TextView("" + score, (int) (getCamera().position.x + 35),
                (int) (getCamera().position.y + 8),
                0.15f, AssetsLoader.smallFont, Color.WHITE);
        smallScoreShadow = new TextView("" + score, (int) (getCamera().position.x + 35) - 1,
                (int) (getCamera().position.y + 8) - 1,
                0.15f, AssetsLoader.smallShadow, Color.WHITE);

        smallScoreTextHighScore = new TextView("" + AssetsLoader.currentHighScore, (int) (getCamera().position.x + 35),
                (int) (getCamera().position.y - 14),
                0.15f, AssetsLoader.smallFont, Color.WHITE);
        smallScoreShadowHighScore = new TextView("" + AssetsLoader.currentHighScore, (int) (getCamera().position.x + 35) - 1,
                (int) (getCamera().position.y - 14) - 1,
                0.15f, AssetsLoader.smallShadow, Color.WHITE);

//        scoreText.setCenterX((int)(getCamera().position.x - AssetsLoader.scoreBackground .getWidth()/2 + 30));
//        scoreText.setCenterY((int)(getCamera().position.y - AssetsLoader.scoreBackground.getHeight()/2 + 30));
//
//        scoreShadow.setCenterX((int)(getCamera().position.x - AssetsLoader.scoreBackground .getWidth()/2 + 30));
//        scoreShadow.setCenterY((int)(getCamera().position.y - AssetsLoader.scoreBackground.getHeight()/2 + 30));
    }

    @Override
    public void dispose() {
        //AssetsLoader.backgroundDay.dispose();
        bird.dispose();
        //AssetsLoader.ground.dispose();
        for (Tube tube : tubes) {
            tube.dispose();
        }
    }

    private void updateGroud() {
        if (getCamera().position.x - (getCamera().viewportWidth / 2) > groundPos1.x + AssetsLoader.ground.getWidth()) {
            groundPos1.add(AssetsLoader.ground.getWidth() * 2, 0);
        }
        if (getCamera().position.x - (getCamera().viewportWidth / 2) > groundPos2.x + AssetsLoader.ground.getWidth()) {
            groundPos2.add(AssetsLoader.ground.getWidth() * 2, 0);
        }
    }
}
