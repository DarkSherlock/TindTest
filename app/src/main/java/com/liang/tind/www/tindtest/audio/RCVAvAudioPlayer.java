package com.liang.tind.www.tindtest.audio;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;

import java.io.IOException;

public class RCVAvAudioPlayer {
    private static final String TAG = "RCVAvAudioPlayer";
    private MediaPlayer mediaPlayer;
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    public RCVAvAudioPlayer(int streamType) {
        initPlayer(streamType);
    }


    private void initPlayer(int streamType) {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnCompletionListener((mp -> RCVAvAudioPlayer.this.finish(true)));
        mediaPlayer.setAudioStreamType(streamType);
    }

    private void finish(boolean success) {

    }

    public boolean play() {
        boolean success = true;
        try {
            mediaPlayer.setLooping(false);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            success = false;
            e.printStackTrace();
            mHandler.post(() -> RCVAvAudioPlayer.this.finish(false));
        }
        return success;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
}
