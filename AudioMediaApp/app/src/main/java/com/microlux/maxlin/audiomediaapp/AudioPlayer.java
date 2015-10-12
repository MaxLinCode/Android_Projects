package com.microlux.maxlin.audiomediaapp;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.SurfaceHolder;

import java.io.IOException;

/**
 * Created by Max Lin on 10/11/2015.
 */
public class AudioPlayer {
    private MediaPlayer mPlayer;
    private Uri resourceUri = Uri.parse("android.resource://"+"com.microlux.maxlin.audiomediaapp/raw/and_his_name_is_john_cena.mpg");

    public AudioPlayer(SurfaceHolder sh) {
        mPlayer.setDisplay(sh);
    }

    public void play(Context c) throws IOException{
        stop();
        mPlayer.setDataSource(c,resourceUri);
        mPlayer.prepare();
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stop();
            }
        });

        mPlayer.start();
    }

    public void stop() {
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }
}
