package com.microlux.maxlin.pointyred;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Max Lin on 10/14/2015.
 */
public class TestView extends SurfaceView implements SurfaceHolder.Callback {
    public static final String LOG_TAG = TestView.class.getCanonicalName();
    public static final int SCREEN_WIDTH = Resources.getSystem().getDisplayMetrics().widthPixels;
    public static final int SCREEN_HEIGHT = Resources.getSystem().getDisplayMetrics().heightPixels;

    private MainThread thread;

    public TestView(Context context) {
        super(context);
        getHolder().addCallback(this);
        if (thread == null) thread = new MainThread(getHolder(), this, getResources());

        setFocusable(true);
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
        thread.setRunning(false);
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

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
