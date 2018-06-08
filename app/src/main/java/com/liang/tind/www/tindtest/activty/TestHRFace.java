package com.liang.tind.www.tindtest.activty;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.arcsoft.facetracking.AFT_FSDKEngine;
import com.arcsoft.facetracking.AFT_FSDKError;
import com.arcsoft.facetracking.AFT_FSDKVersion;
import com.liang.tind.www.tindtest.base.BaseActivity;

/**
 * created by Administrator
 * <p>
 * date 2018/4/27
 */
public class TestHRFace extends BaseActivity {
    public static final  String appId = "7n27iTiV1DjEUAcZsTeDVe9eDCywgHQq7xnajVzuVkts";
    public static final  String FT_key = "BPuc3VWtmZW8mjUFj7HC8BLaGEJxQzKjjjYRAzMCeCAP";
    AFT_FSDKVersion version = new AFT_FSDKVersion();
    AFT_FSDKEngine engine = new AFT_FSDKEngine();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AFT_FSDKError err = engine.AFT_FSDK_InitialFaceEngine(appId, FT_key, AFT_FSDKEngine.AFT_OPF_0_HIGHER_EXT, 16, 5);
        Log.d("faceViewInit", "AFT_FSDK_InitialFaceEngine =" + err.getCode());
        err = engine.AFT_FSDK_GetVersion(version);
        Log.d("faceViewInit", "AFT_FSDK_GetVersion:" + version.toString() + "," + err.getCode());
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void init() {

    }
}