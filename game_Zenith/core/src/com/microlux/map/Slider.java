package com.microlux.map;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.microlux.game.Game;
import com.microlux.states.PlayState;

/**
 * Created by maxlin on 12/9/15.
 */
public class Slider {
    private float height;
    private float sliderx, sliderWidth;
    private float areax,areaWidth;
    float dx;

    float barWidth, barMargin;

    Texture ingredients = new Texture(Gdx.files.internal("ingredients.png"));
    TextureRegion bar;
    TextureRegion slider;
    TextureRegion area;
    float time = 0;

    public Slider() {
        bar = new TextureRegion(ingredients, 0, 960, 64, 64);
        slider = new TextureRegion(ingredients, 64, 960, 64, 64);
        area = new TextureRegion(ingredients, 128, 960, 64, 64);

        height = 100; // all items heights are the same (not y coord)

        barMargin = 50;
        barWidth = Game.WORLD_WIDTH - barMargin * 2;

        sliderx = 64;
        sliderWidth = 30;
        dx = 500;

        areax = 500;
        areaWidth = 100;
    }

    public boolean isScored() {
        if (isSliderInArea()) {
            PlayState.score.addScore(PlayState.customers.getCustomerType());
            randomizeAreaLocation();
            return true;
        }
        // missing penalty?
        PlayState.score.missedSlider();

        return false;
    }

    public void update(float dt) {
        float translate = dx * dt;
        if (sliderx + translate < barMargin) {
            dx = -dx;
            translate = -translate;
            sliderx = barMargin;
        }
        else if (sliderx + translate > Game.WORLD_WIDTH - barMargin - sliderWidth) {
            dx = -dx;
            translate = -translate;
            sliderx = Game.WORLD_WIDTH - barMargin - sliderWidth;
        }

        sliderx += translate;
        time += dt;
        if (time > 3 && Math.abs(dx) < 800) {
            time = 0;
            dx *= 1.05;
        }
    }

    private void randomizeAreaLocation() {
        areax = (float) (Math.random() * (barWidth - areaWidth)) + barMargin;
    }

    private boolean isSliderInArea() {
        // completely within the area
        return sliderx > areax && sliderx + sliderWidth < areax + areaWidth;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(bar, barMargin, 300, barWidth, height);
        batch.draw(area, areax, 300, areaWidth, height);
        batch.draw(slider, sliderx, 300, sliderWidth, height);

    }

}
