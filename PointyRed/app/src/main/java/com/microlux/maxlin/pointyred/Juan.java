package com.microlux.maxlin.pointyred;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

/**
 * Created by MaxLin on 10/29/15.
 */
public class Juan {
    private int x,y;
    private int height, width;
    private boolean touched = true;


    private int box = 400;
    private Bitmap juanImg;
    private Paint paint;

    public Juan(Bitmap b, int x, int y) {
        juanImg = b;
        this.x = x;
        this.y = y;
        height = b.getWidth();
        width = b.getWidth();

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void handleInput(int inputx, int inputy, int eventAction) {
        switch (eventAction) {
            case MotionEvent.ACTION_DOWN:
                if (inputx >= x && inputx <= x + width && inputy >= y && inputy <= y + height) {
                    touched = true;
                }
                else {
                    touched = false;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (touched) {
                    // check if he is within bounds or not
                    int testx = inputx - width / 2;
                    int testy = inputy - height / 2;

                    if (testx <= (TestView.SCREEN_WIDTH / 2) - box) {
                        x = (TestView.SCREEN_WIDTH / 2) - box;
                    }

                    if (testx + width >= (TestView.SCREEN_WIDTH / 2) + box) {
                        x = (TestView.SCREEN_WIDTH / 2) + box - width;
                    }

                    if (testy <= (TestView.SCREEN_HEIGHT / 2) - box) {
                        y = (TestView.SCREEN_HEIGHT / 2) - box;
                    }

                    if (testy + height >= (TestView.SCREEN_HEIGHT / 2) + box) {
                        y = (TestView.SCREEN_HEIGHT / 2) + box - height;
                    }

                    if (testx > (TestView.SCREEN_WIDTH / 2) - box &&
                            testx + width < (TestView.SCREEN_WIDTH / 2) + box) {
                        x = testx;
                    }

                    if (testy > (TestView.SCREEN_HEIGHT / 2) - box &&
                            testy + height < (TestView.SCREEN_HEIGHT / 2) + box) {
                        y = testy;
                    }

                }
                break;
            case MotionEvent.ACTION_UP:
                touched = false;
                break;

        }

    }

    public void draw(Canvas c) {
        paint.setColor(Color.WHITE);
        c.drawRect((TestView.SCREEN_WIDTH / 2) - box - 10, (TestView.SCREEN_HEIGHT / 2) - box - 10, (TestView.SCREEN_WIDTH / 2) + box + 10, (TestView.SCREEN_HEIGHT / 2) + box + 10, paint);
        paint.setColor(Color.BLACK);
        c.drawRect((TestView.SCREEN_WIDTH / 2) - box, (TestView.SCREEN_HEIGHT / 2) - box, (TestView.SCREEN_WIDTH / 2) + box, (TestView.SCREEN_HEIGHT / 2) + box, paint);

        c.drawBitmap(juanImg, x, y, null);
    }
}
