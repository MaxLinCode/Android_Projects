package com.microlux.maxlin.pointyred;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

/**
 * Created by Max Lin on 10/14/2015.
 */
public class MainThread extends Thread {
    public static String LOG_TAG = MainThread.class.getCanonicalName();

    public static final int FPS = 60;
    public static final long targetTime = 1000/FPS;

    private Paint redPaint;
    private Paint whitePaint;

    private int startX, startY;
    private int speedModifier;
    private int circleX;
    private int circleY;
    public float radius;

    private int dx;  // velocities
    private int dy;
    private long actionDownTime, actionElapsedTime;
    private float dt;
    private boolean running;

    private SurfaceHolder surfaceHolder;
    private TestView testView;
    private int screenWidth;
    private int screenHeight;

    public MainThread(SurfaceHolder sh, TestView testView) {
        super();
        this.surfaceHolder = sh;
        this.testView = testView;

        redPaint = new Paint();
        redPaint.setAntiAlias(true);
        redPaint.setColor(Color.RED);
        whitePaint = new Paint();
        whitePaint.setAntiAlias(true);
        whitePaint.setColor(Color.WHITE);

        dx = 0;
        dy = 0;
        circleX = 100;
        circleY = 100;
        radius = 70;
        speedModifier = 5;
        dt = 0;
    }

    public void run() {
        while (running) {
            long startTime, elapsedTime, wait;
            startTime = System.nanoTime();

            update();
            draw();

            // sleep
            elapsedTime = System.nanoTime() - startTime;
            dt = elapsedTime / 1000;
            wait = (targetTime - elapsedTime /  1000000);

            if (wait < 0) wait = 5;
            try {
                // delay the thread
                Thread.sleep(wait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        // collision with wall
        if (circleX <= radius || circleX >= screenWidth - radius) {
            dx = -dx;
            if (circleX <= 0 + radius ) {
                circleX = (int) radius;
            }
            else {
                circleX = (int) (screenWidth - radius);
            }

        }

        if (circleY <= radius || circleY >= screenHeight - radius) {
            dy = -dy;
            if (circleY <= radius ) {
                circleY = (int) radius;
            }
            else {
                circleY = (int) (screenHeight - radius);
            }
        }

        circleX += dx * speedModifier;
        circleY += dy * speedModifier;

    }

    public void draw() {
        //DRAW
        Canvas c = null;
        try {
            //lock canvas so nothing else can use it
            c = surfaceHolder.lockCanvas();
            synchronized (surfaceHolder) {
                //clear the screen with the black painter.
                c.drawRect(0, 0, c.getWidth(), c.getHeight(), whitePaint);
                //draw stuff
                c.drawCircle(circleX,circleY,radius,redPaint);
                //This is where we draw the game engine.
                // testView.draw(c);
            }
        } finally {
            // do this in a finally so that if an exception is thrown
            // during the above, we don't leave the Surface in an
            // inconsistent state
            if (c != null) {
                surfaceHolder.unlockCanvasAndPost(c);
            }
        }

    }

    public boolean onTouchEvent(MotionEvent event) {
        int eventAction = event.getAction();
        int x = (int)event.getX();
        int y = (int)event.getY();

        switch (eventAction) {
            case MotionEvent.ACTION_DOWN:
                dx = 0 ;
                dy = 0;
                startX = x;
                startY = y;
                actionDownTime = System.nanoTime();
                break;
            case MotionEvent.ACTION_MOVE:
                circleX = x;
                circleY = y;
                break;
            case MotionEvent.ACTION_UP:
                // release and let the ball go flying
                actionElapsedTime = System.nanoTime() - actionDownTime;
                dx = (int) ((circleX-startX) / (actionElapsedTime / 1000000));
                dy = (int) ((circleY-startY) / (actionElapsedTime / 1000000));
                break;
        }

        return true;
    }

    public void setScreenDimensions(int width, int height) {
        screenWidth = width;
        screenHeight = height;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

}

