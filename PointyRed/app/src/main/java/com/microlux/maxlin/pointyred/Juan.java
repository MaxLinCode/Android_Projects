package com.microlux.maxlin.pointyred;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * Created by MaxLin on 10/29/15.
 */
public class Juan {
    private int x,y;
    private int height, width;
    private Bitmap juanImg;

    public Juan(Bitmap b, int x, int y) {
        juanImg = b;
        this.x = x;
        this.y = y;
        height = b.getWidth();
        width = b.getWidth();
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getX() {

        return x;
    }

    public float getY() {
        return y;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public void handleInput(int inputx, int inputy) {
            if (inputx > x && inputx < x + width && inputy > y && inputy < y + height) {
                // touch is on juan
                // check if he is within bounds or not
                if (inputx > (TestView.SCREEN_WIDTH / 2) - 200 &&
                        inputx < (TestView.SCREEN_WIDTH / 2) + 200 &&
                        inputx > (TestView.SCREEN_HEIGHT / 2) - 200 &&
                        inputy < (TestView.SCREEN_HEIGHT / 2) + 200)
                // operation move juan is a go
                x = inputx;
                y = inputy;
            }
    }

    public void draw(Canvas c) {
        c.drawBitmap(juanImg, x, y, null);
    }
}
