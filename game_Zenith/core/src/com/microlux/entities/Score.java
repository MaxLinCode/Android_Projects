package com.microlux.entities;

import com.microlux.game.Game;
import com.microlux.game.GameStateManager;
import com.microlux.states.PlayState;

/**
 * Created by maxlin on 12/9/15.
 */
// Score also includes lives
public class Score {
    // scoring value
    private static final int SCORE_CUSTOMER_NORMAL = 100;
    private static final int SCORE_CUSTOMER_VIP = 500;
    private static final int SCORE_MISSED_SLIDER = 25;

    private int lives = 5;
    private int score = 0;

    public void Score() {

    }

    public void addScore(int CustomerType) {
        switch (CustomerType) {
            case Customers.NORMAL:
                score += SCORE_CUSTOMER_NORMAL;
                break;
            case Customers.VIP:
                score += SCORE_CUSTOMER_VIP;
        }
    }

    public void missedSlider() {
        lives--;
        if (lives <= 0) {
            // end the game
        }
    }

    public void customerComplaint(int CustomerType) {
        switch (CustomerType) {
            case Customers.NORMAL:
                lives--;
                break;
            case Customers.VIP:
                lives -= 2;
        }

        if (lives <= 0) {
            lives = 0;
            // game over
            // game over state
            Game.gsm.selectState(GameStateManager.TYPE_GAMEOVERSTATE);
        }
    }

    public int getLives() {
        return lives;
    }

    public int getScore() {
        return score;
    }
}
