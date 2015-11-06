package com.microlux.maxlin.pointyred;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

/**
 * Created by MaxLin on 11/4/15.
 */
public class Paddle {
    private float x,y;
    private int width, height;

    public Paddle() {
        width = 250;
        height = 25;
        x = TestView.SCREEN_WIDTH / 2 - width;
        y = TestView.SCREEN_HEIGHT / 2 + 350;
    }

    public void handleInput(MotionEvent event) {
        int eventAction = event.getAction();
        float xInput = event.getX();

        switch (eventAction) {
            case MotionEvent.ACTION_DOWN:
                x = xInput;
                break;
            case MotionEvent.ACTION_MOVE:
                x = xInput;
                break;
        }
    }

    public void update(float dt) {

    }

    public void draw(Canvas c, Paint p) {
        p.setColor(Color.WHITE);
        c.drawRect(x, y, x + width, y + height, p);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
