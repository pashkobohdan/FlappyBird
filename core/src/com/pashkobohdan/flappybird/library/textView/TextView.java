package com.pashkobohdan.flappybird.library.textView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Bohdan Pashko on 14.09.16.
 */
public class TextView {
    private String text;
    private int centerX;
    private int centerY;

    private int width;
    private BitmapFont font;

    private float scale;

    private Color color;

    private GlyphLayout textFontGlyphLayout;


    public TextView(String text, int centerX, int centerY, int width, BitmapFont font, Color color) {
        this.text = text;
        this.centerX = centerX;
        this.centerY = centerY;
        this.width = width;
        this.font = font;
        this.color = color;

        textFontGlyphLayout = new GlyphLayout();
        textFontGlyphLayout.setText(font, text);

        scale = width/textFontGlyphLayout.width;

        this.font.setColor(color);
        this.font.getData().setScale(scale);

        textFontGlyphLayout.setText(font, text);
    }

    public TextView(String text, int centerX, int centerY, float scale, BitmapFont font, Color color) {
        this.text = text;
        this.centerX = centerX;

        this.centerY = centerY;
        this.font = font;
        this.color = color;

        this.scale = scale;

        textFontGlyphLayout = new GlyphLayout();
        textFontGlyphLayout.setText(font, text);

        this.font.setColor(color);
        this.font.getData().setScale(scale);

        textFontGlyphLayout.setText(font, text);
    }


    public void render(SpriteBatch spriteBatch) {
        font.draw(spriteBatch, text, centerX - textFontGlyphLayout.width / 2, centerY + textFontGlyphLayout.height / 2);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;

        textFontGlyphLayout = new GlyphLayout();
        textFontGlyphLayout.setText(font, text);

        font.setColor(color);
        font.getData().setScale(scale);

        textFontGlyphLayout.setText(font, text);
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public BitmapFont getFont() {
        return font;
    }

    public void setFont(BitmapFont font) {
        this.font = font;
    }
}
