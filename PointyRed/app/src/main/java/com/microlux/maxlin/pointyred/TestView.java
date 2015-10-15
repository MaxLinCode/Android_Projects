package com.microlux.maxlin.pointyred;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Max Lin on 10/14/2015.
 */
public class TestView extends SurfaceView implements SurfaceHolder.Callback {
    public static final String LOG_TAG = TestView.class.getCanonicalName();
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

    private MainThread thread;

    public TestView(Context context) {
        super(context);
        getHolder().addCallback(this);
        if (thread == null) thread = new MainThread(getHolder(), this);


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
        setFocusable(true);
    }



    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(circleX,circleY,radius, redPaint);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        thread.setScreenDimensions(width, height);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
                thread.setRunning(false);
                retry  = false;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    public boolean onTouchEvent(MotionEvent event) {
        return thread.onTouchEvent(event);
    }
}
