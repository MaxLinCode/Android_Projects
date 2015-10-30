package com.microlux.maxlin.pointyred;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
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

    private Resources res;
    private Paint paint;

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

    public MainThread(SurfaceHolder sh, TestView testView, Resources res) {
        super();
        this.surfaceHolder = sh;
        this.testView = testView;
        this.res = res;

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        juan = new Juan(BitmapFactory.decodeResource(res, R.drawable.juan), 0, 0);
        juan.setX(TestView.SCREEN_WIDTH / 2 - juan.getWidth() / 2);
        juan.setY(TestView.SCREEN_HEIGHT / 2 - juan.getHeight() / 2);
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
                //This is where we draw the game engine.
                c.drawColor(Color.BLACK);
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
        switch (eventAction) {
            case MotionEvent.ACTION_DOWN:
                juan.handleInput(x,y, MotionEvent.ACTION_DOWN);
                break;
            case MotionEvent.ACTION_MOVE:
                juan.handleInput(x,y, MotionEvent.ACTION_MOVE);
                break;
            case MotionEvent.ACTION_UP:

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

