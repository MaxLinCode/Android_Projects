package com.microlux.maxlin.pointyred;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Max Lin on 10/14/2015.
 */
public class GameView extends View {
    public static final String LOG_TAG = GameView.class.getCanonicalName();
    public static final int FPS = 1;
    public static final long targetTime = 1000/FPS;

    private Paint redPaint;
    private int startX, startY;
    private int speedModifier;
    private int circleX;
    private int circleY;
    public float radius;

    private int dx;  // velocities
    private int dy;
    private long actionDownTime, actionElapsedTime;
    private float dt;

    public GameView(Context context) {
        super(context);
        redPaint = new Paint();
        redPaint.setAntiAlias(true);
        redPaint.setColor(Color.RED);
        dx = 0;
        dy = 0;
        circleX = 100;
        circleY = 100;
        radius = 50;
        speedModifier = 5;
        dt = 0;
        run();
    }

    public void run() {
        long startTime, elapsedTime, wait;
        while (true) {
            startTime = System.nanoTime();
            // handle stuff here
            circleX += dx * speedModifier * dt;
            circleY += dy * speedModifier * dt;
            // render
            postInvalidate();
            // debug
            Log.d(LOG_TAG, "(" + dx + ", " + dy + ")");
            elapsedTime = System.nanoTime() - startTime;
            dt = elapsedTime / 1000000;
            wait = (targetTime - elapsedTime /  1000000);
                try {
                    // delay the thread
                    Thread.sleep(wait);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        int eventAction = event.getAction();
        int x = (int)event.getX();
        int y = (int)event.getY();

        switch (eventAction) {
            case MotionEvent.ACTION_DOWN:
                startX = x;
                startY = y;
                actionDownTime = System.nanoTime();
                break;
            case MotionEvent.ACTION_MOVE:
                circleX = x;
                circleY = y;
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                // release and let the ball go flying
                actionElapsedTime = System.nanoTime() - actionDownTime;
                dx = (int) ((startX-circleX) / (actionElapsedTime * 1000000000));
                dy = (int) ((startY-circleY) / (actionElapsedTime * 1000000000));
                break;
        }

        invalidate();
        return true;
    }

    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(circleX,circleY,radius, redPaint);
    }


}
