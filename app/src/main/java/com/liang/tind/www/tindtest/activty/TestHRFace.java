package com.liang.tind.www.tindtest.activty;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.liang.tind.www.tindtest.R;
import com.liang.tind.www.tindtest.base.BaseActivity;

import androidx.annotation.Nullable;

/**
 * created by Administrator
 * <p>
 * date 2018/4/27
 */
public class TestHRFace extends BaseActivity {
    public static final  String appId = "7n27iTiV1DjEUAcZsTeDVe9eDCywgHQq7xnajVzuVkts";
    public static final  String FT_key = "BPuc3VWtmZW8mjUFj7HC8BLaGEJxQzKjjjYRAzMCeCAP";
//    AFT_FSDKVersion version = new AFT_FSDKVersion();
//    AFT_FSDKEngine engine = new AFT_FSDKEngine();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        AFT_FSDKError err = engine.AFT_FSDK_InitialFaceEngine(appId, FT_key, AFT_FSDKEngine.AFT_OPF_0_HIGHER_EXT, 16, 5);
//        Log.d("faceViewInit", "AFT_FSDK_InitialFaceEngine =" + err.getCode());
//        err = engine.AFT_FSDK_GetVersion(version);
//        Log.d("faceViewInit", "AFT_FSDK_GetVersion:" + version.toString() + "," + err.getCode());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_test;
    }

    @Override
    protected void init() {

        ImageView image = findViewById(R.id.image);
        image.post(new Runnable() {
            @Override
            public void run() {
                int measuredHeight = image.getMeasuredHeight();
                int measuredWidth = image.getMeasuredWidth();

                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.background);
                Log.e(TAG, "run(TestHRFace.java:52): iamgeview width="+measuredWidth);
                Log.e(TAG, "run(TestHRFace.java:52): iamgeview height="+measuredHeight);
                Log.e(TAG, "run(TestHRFace.java:52): bitmap width="+bitmap.getWidth());
                Log.e(TAG, "run(TestHRFace.java:52): bitmap height="+bitmap.getHeight());
            }
        });
    }
}
