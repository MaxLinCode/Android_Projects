package com.microlux.maxlin.pointyred;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

/**
 * Created by Max Lin on 10/14/2015.
 */
public class MainThread extends Thread {
    public static String LOG_TAG = MainThread.class.getCanonicalName();
    public static final int FPS = 60;
    public static final long targetTime = 1000/FPS;

    private Resources res;
    private Paint paint;

    private float startX, startY;
    private float speedModifier;
    private int circleX;
    private int circleY;
    public float radius;

    private long actionDownTime, actionElapsedTime, actionLastTime;
    private float dt;
    private int fingerX, fingerY;
    private int lastX, lastY;
    private boolean running;

    private SurfaceHolder surfaceHolder;
    private TestView testView;
    private int screenWidth;
    private int screenHeight;

    private Juan juan;

    public MainThread(SurfaceHolder sh, TestView testView) {
        super();
        this.surfaceHolder = sh;
        this.testView = testView;
        res = Resources.getSystem();

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        juan = new Juan(BitmapFactory.decodeResource(res, R.drawable.juan), TestView.SCREEN_WIDTH / 2, TestView.SCREEN_HEIGHT / 2);
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
        /*
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

        lastX = fingerX;
        lastY = fingerY;
        */
        actionLastTime = System.nanoTime();
    }

    public void draw() {
        //DRAW
        Canvas c = null;
        try {
            //lock canvas so nothing else can use it
            c = surfaceHolder.lockCanvas();
            synchronized (surfaceHolder) {
                //clear the screen with the black
                c.drawColor(Color.BLACK);
                //This is where we draw the game engine.
                juan.draw(c);
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
        fingerX = x;
        fingerY = y;
        switch (eventAction) {
            case MotionEvent.ACTION_DOWN:
                //dx = 0 ;
                //dy = 0;
                circleX = x;
                circleY = y;
                //actionDownTime = System.nanoTime();
                break;
            case MotionEvent.ACTION_MOVE:
                circleX = x;
                circleY = y;
                break;
            case MotionEvent.ACTION_UP:
                // release and let the ball go flying
                actionElapsedTime = System.nanoTime() - actionLastTime;
                if (actionElapsedTime / 1000000 != 0) {
                   // dx = ((circleX - lastX) / (actionElapsedTime / 1000000));
                   // dy = ((circleY - lastY) / (actionElapsedTime / 1000000));
                }
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

