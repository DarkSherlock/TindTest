package com.liang.tind.www.tindtest.activty;

import android.Manifest;
import android.content.Intent;
import android.util.Log;

import com.liang.tind.www.tindtest.R;
import com.liang.tind.www.tindtest.base.BaseActivity;
import com.liang.tind.www.tindtest.fragment.FirstFragment;
import com.lonnie.common.permission.EasyPermission;
import com.lonnie.common.permission.HintData;
import com.lonnie.common.permission.PermissionData;

/**
 * @author lonnie.liang
 * @date 2020/05/06 15:36
 */
public class BtnActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_btn;
    }

    @Override
    protected void init() {
        findViewById(R.id.btn_test)
                .setOnClickListener(
                        v -> {
//                            DoraemonKit.showToolPanel();
//                            AudioUtil.playSound(R.raw.sosumi, BtnActivity.this);
//                            showPermissionAlert();

//                            MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.voice_command_didnt_catch_help);
//                            mediaPlayer.setOnCompletionListener(MediaPlayer::release);
//                            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                            mediaPlayer.start();

                            testClearTask();
                        });

        boolean shouldShow = EasyPermission.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO);
        Log.i(TAG, "init(): shouldShow=" + shouldShow);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, FirstFragment.newInstance()).commitNow();

    }

    private void showPermissionAlert() {
        PermissionData recordAudioPermission = new PermissionData(
                Manifest.permission.RECORD_AUDIO, PermissionData.SHOW_HINT_ALERT,
                new HintData(
                        "Not access your microphone",
                        "please grant the record audio permission",
                        "title",
                        -1
                )
        );

        EasyPermission.with(this)
                .setPermission(recordAudioPermission)
                .onGranted(() -> Log.i(TAG, "onGranted(): "))
                .onDenied(() -> Log.i(TAG, "onDenied() "))
                .request();
    }

    private void testClearTask() {
        Intent intent = new Intent(this, TestEditTextActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
