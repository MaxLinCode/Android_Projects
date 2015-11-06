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
    public static final int FRAME_TIME = 1000/FPS;
    public static final int MAX_FRAME_SKIPS = 5;

    private boolean running;

    private SurfaceHolder surfaceHolder;
    private Paint paint;
    private TestView testView;
    private int screenWidth, screenHeight;

    private long lastUpdateTime;
    private float dt = 0;

    private Juan juan;
    private Paddle paddle;

    public MainThread(SurfaceHolder sh, TestView testView, Resources res) {
        super();
        this.surfaceHolder = sh;
        this.testView = testView;

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);

        juan = new Juan(BitmapFactory.decodeResource(res, R.drawable.juan), 0, 0);
        juan.setX(TestView.SCREEN_WIDTH / 2 - juan.getWidth() / 2);
        juan.setY(TestView.SCREEN_HEIGHT / 2 - juan.getHeight() / 2);

        paddle = new Paddle();
    }

    public void run() {
        long startTime, elapsedTime;
        int wait;
        int framesSkipped = 0;
        while (running) {
            startTime = System.currentTimeMillis();
            update();
            draw();
            elapsedTime = System.currentTimeMillis() - startTime;

            wait = (int) (FRAME_TIME - elapsedTime);
            // skip draws to catch up (max of 5 skipped)
            while (wait < 0 && framesSkipped <= MAX_FRAME_SKIPS) {
                Log.d(LOG_TAG, "frame skipped");
                update();
                wait += FRAME_TIME;
                framesSkipped++;
            }

            if (wait > 0) {
                try {
                    Thread.sleep(wait);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void update()
    {
        dt = System.currentTimeMillis() - lastUpdateTime;
        lastUpdateTime = System.currentTimeMillis();
        paddle.update(dt);
        juan.update(dt);
        handleCollision();
    }

    public void handleCollision() {
        if (juan.getY() + juan.getHeight() >= paddle.getY() &&
                juan.getX() < paddle.getX() + paddle.getWidth() &&
                paddle.getX() < juan.getX() + juan.getWidth()) {
            juan.setY(paddle.getY() - juan.getWidth());
            juan.dy *= -1;
            juan.dx *= -1;
        }
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
                paddle.draw(c, paint);
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
                paddle.handleInput(event);
                juan.handleInput(x,y, MotionEvent.ACTION_DOWN);
                break;
            case MotionEvent.ACTION_MOVE:
                paddle.handleInput(event);
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

