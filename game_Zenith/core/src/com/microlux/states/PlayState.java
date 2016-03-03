package com.microlux.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.microlux.entities.Customers;
import com.microlux.entities.Score;
import com.microlux.game.Game;
import com.microlux.map.Slider;
import com.microlux.map.TacoLayer;

/**
 * Created by MaxLin on 11/6/15.
 */
public class PlayState implements GameState {
    public static Score score;
    public static Customers customers;
    BitmapFont font;
    TacoLayer tacoLayer;
    Slider slider;

    Texture background = new Texture(Gdx.files.internal("mexican_flag.png"));
    Texture ingredients = new Texture(Gdx.files.internal("ingredients.png"));
    TextureRegion brown = new TextureRegion(ingredients, 0, 960, 64, 64);
    TextureRegion orange = new TextureRegion(ingredients, 64, 960, 64, 64);

    public PlayState() {
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(5f, 5f);
        score = new Score();
        customers = new Customers();
        tacoLayer = new TacoLayer();
        slider = new Slider();
    }

    @Override
    public void init() {

    }

    @Override
    public void handleInput() {
        // remember that input is top corner (0,0)
        if(Gdx.input.justTouched() && Gdx.input.isTouched(1)) {
            tacoLayer.sendTaco();
        }
        else if(Gdx.input.justTouched() && slider.isScored()) {
            tacoLayer.addIngredient();
        }
    }

    @Override
    public void update(float dt)
    {
        tacoLayer.update(dt);
        slider.update(dt);
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(background,0, 0);
        font.draw(batch, "Score: " + score.getScore(), 50, Game.WORLD_HEIGHT - 50);
        font.draw(batch, "Lives: " + score.getLives(), 50, Game.WORLD_HEIGHT - 150);
        if (customers.getCustomerType() == Customers.VIP) {
            batch.draw(orange, Game.WORLD_WIDTH / 2 - 64, Game.WORLD_HEIGHT * 3 / 4, 128, 128);
        }
        else {
            // normal
            batch.draw(brown, Game.WORLD_WIDTH / 2 - 64, Game.WORLD_HEIGHT * 3 / 4, 128, 128);
        }
        tacoLayer.draw(batch);
        slider.draw(batch);

    }

    @Override
    public void dispose() {
        font.dispose();
    }
}
