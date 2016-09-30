package com.pashkobohdan.flappybird.library.button;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pashkobohdan.flappybird.library.button.core.ButtonState;
import com.pashkobohdan.flappybird.library.button.core.interfaces.ButtonBase;
import com.pashkobohdan.flappybird.library.button.core.interfaces.ClickListener;
import com.pashkobohdan.flappybird.library.textView.TextView;

/**
 * Created by Bohdan Pashko on 14.09.16.
 */
public class Button implements ButtonBase {

    private OrthographicCamera orthographicCamera;
    //private String text;
    private Texture buttonOff;
    private Texture buttonOn;
    private int centerX;
    private int centerY;
    private int width;
    private int height;

    //private TextView textView;
//    private BitmapFont textFont;
//    private GlyphLayout textFontGlyphLayout = new GlyphLayout();

    private int leftX, rightX, upY, downY;
    private int clickX, clickY;
    private ClickListener clickListener;

    private ButtonState currentState;

    public Button(OrthographicCamera orthographicCamera, Texture buttonOff, Texture buttonOn,
                  int centerX, int centerY,
                  int width, int height) {
        this.orthographicCamera = orthographicCamera;
        //this.textView = textView;
        this.buttonOff = buttonOff;
        this.buttonOn = buttonOn;
        this.centerX = centerX;
        this.centerY = centerY;
        this.width = width;
        this.height = height;

//        textFont = new BitmapFont();
//        textFontGlyphLayout.setText(textFont, text);

        resetButtonEdges();
    }

    public Button(OrthographicCamera orthographicCamera, Texture buttonOff, Texture buttonOn,
                  int centerX, int centerY) {
        this.orthographicCamera = orthographicCamera;
        //this.textView = textView;
        this.buttonOff = buttonOff;
        this.buttonOn = buttonOn;
        this.centerX = centerX;
        this.centerY = centerY;
        this.width = buttonOff.getWidth();
        this.height = buttonOff.getHeight();

//        textFont = new BitmapFont();
//        textFontGlyphLayout.setText(textFont, text);

        resetButtonEdges();
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        if (currentState == ButtonState.PRESSED) {
            spriteBatch.draw(buttonOn, leftX, downY, width, height);
        } else {
            spriteBatch.draw(buttonOff, leftX, downY, width, height);
        }

        //textView.render(spriteBatch);
        //textFont.draw(spriteBatch, text, centerX - textFontGlyphLayout.width / 2, centerY);
    }

    @Override
    public void dispose() {
        if (getButtonOff() != null) {
            getButtonOff().dispose();
        }
        if (getButtonOn() != null) {
            getButtonOn().dispose();
        }

        //getTextFont().dispose();
    }

    @Override
    public void handleInput() {
        if (clickListener != null) {
            clickX = (int) ((float) Gdx.input.getX() / Gdx.graphics.getWidth() * orthographicCamera.viewportWidth);
            clickY = (int) (orthographicCamera.viewportHeight * (1.0f - (float) Gdx.input.getY() / Gdx.graphics.getHeight()));

            if (clickX >= leftX && clickX <= rightX && clickY >= downY && clickY <= upY) {
                if (Gdx.input.isTouched()) {
                    clickListener.oneClick(this, clickX, clickY);

                    currentState = ButtonState.PRESSED;
                    return;
                } else {
                    if (currentState == ButtonState.PRESSED) {
                        clickListener.postClick(this, clickX, clickY);
                    }
                }
            }

            currentState = ButtonState.NOT_PRESSED;
        }
    }

    @Override
    public void addClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public void removeClickListener() {
        this.clickListener = null;
    }

    @Override
    public void resetButtonEdges() {
        leftX = getCenterX() - getWidth() / 2;
        rightX = getCenterX() + getWidth() / 2;
        upY = getCenterY() + getHeight() / 2;
        downY = getCenterY() - getHeight() / 2;
    }

    //
    // Button values changing methods
    //
    public void setButtonPosition(int centerX, int centerY) {
        this.centerX = centerX;
        this.centerY = centerX;

        resetButtonEdges();
    }

    public void setButtonSize(int width, int height) {
        this.width = width;
        this.height = height;

        resetButtonEdges();
    }

    //
    // Get's and set's methods
    //
    public OrthographicCamera getOrthographicCamera() {
        return orthographicCamera;
    }

    public void setOrthographicCamera(OrthographicCamera orthographicCamera) {
        this.orthographicCamera = orthographicCamera;
    }

//    public String getText() {
//        return text;
//    }
//
//    public void setText(String text) {
//        this.text = text;
//    }

    public Texture getButtonOff() {
        return buttonOff;
    }

    public void setButtonOff(Texture buttonOff) {
        this.buttonOff = buttonOff;
    }

    public Texture getButtonOn() {
        return buttonOn;
    }

    public void setButtonOn(Texture buttonOn) {
        this.buttonOn = buttonOn;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;

        resetButtonEdges();
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;

        resetButtonEdges();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;

        resetButtonEdges();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;

        resetButtonEdges();
    }

//    public BitmapFont getTextFont() {
//        return textFont;
//    }
//
//    public void setTextFont(BitmapFont textFont) {
//        this.textFont = textFont;
//    }
}
