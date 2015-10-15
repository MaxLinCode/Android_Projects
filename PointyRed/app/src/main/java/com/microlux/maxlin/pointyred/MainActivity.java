package com.microlux.maxlin.pointyred;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        GameView gameView = new GameView(this);
//        setContentView(gameView);
        TestView testView = new TestView(this);
        setContentView(testView);
    }
}
