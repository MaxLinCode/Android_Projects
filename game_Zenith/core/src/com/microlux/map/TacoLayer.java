package com.microlux.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.microlux.game.Game;
import com.microlux.states.PlayState;

/**
 * Created by maxlin on 12/8/15.
 */
public class TacoLayer {
    TextureAtlas atlas;
    TextureRegion tortilla, beef, lettuce, salsa, cheese, sourcream;
    TextureRegion[] items;
    Texture finishedTaco;
    int finishedTacoY;
    int itemCount;
    boolean tacoReady;

    public TacoLayer() {
        finishedTaco = new Texture(Gdx.files.internal("finishedTaco.png"));
        finishedTacoY = Game.WORLD_HEIGHT / 2 - finishedTaco.getHeight() / 2;

        atlas = new TextureAtlas(Gdx.files.internal("ingredients.atlas"));
        tortilla = atlas.findRegion("tortilla");
        beef = atlas.findRegion("beef");
        lettuce = atlas.findRegion("lettuce");
        salsa = atlas.findRegion("salsa");
        cheese = atlas.findRegion("cheese");
        sourcream = atlas.findRegion("sourcream");

        items = new TextureRegion[]{tortilla, beef, lettuce, salsa, cheese, sourcream};
        itemCount = -1;
    }

    public void addIngredient() {
        itemCount++;

        if (itemCount == 6) {
            tacoReady = true;
        }
    }

    public void sendTaco() {
        tacoReady = true;
    }

    public boolean isTacoReady() {
        return tacoReady;
    }

    public void update(float dt) {

    }

    public void draw(SpriteBatch batch) {
        if (tacoReady) {
            // SEND THE TACO
            batch.draw(finishedTaco, Game.WORLD_WIDTH / 2 - finishedTaco.getWidth() / 2, finishedTacoY);
            finishedTacoY += 70;
            if (finishedTacoY + finishedTaco.getHeight() >= Game.WORLD_HEIGHT + finishedTaco.getHeight() / 2) {
                // taco is done sending
                finishedTacoY = Game.WORLD_HEIGHT / 2 - finishedTaco.getHeight() / 2;
                itemCount = -1;
                tacoReady = false;
                PlayState.customers.nextCustomer();
            }
        } else if (itemCount >= 0) {
            batch.draw(tortilla, Game.WORLD_WIDTH / 2 - 256, Game.WORLD_HEIGHT / 2 - 256, 512, 512);
            for (int i = 1; i <= itemCount; i++) {
                batch.draw(items[i], Game.WORLD_WIDTH / 2 - 128, Game.WORLD_HEIGHT / 2 - 128);
            }
        }
    }
}
