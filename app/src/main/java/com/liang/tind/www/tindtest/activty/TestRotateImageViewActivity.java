package com.liang.tind.www.tindtest.activty;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.widget.Button;

import com.liang.tind.www.tindtest.R;
import com.liang.tind.www.tindtest.base.BaseActivity;
import com.liang.tind.www.tindtest.widget.RotateImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * created by Administrator
 * <p>
 * date 2018/6/6
 */
public class TestRotateImageViewActivity extends BaseActivity {
    @BindView(R.id.iv_rotate)
    RotateImageView mIvRotate;
    @BindView(R.id.button)
    Button mBtnRotate;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_rotate_iv;
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    void rotate(){
        mIvRotate.startRotate();
    }

    @OnClick(R.id.button2)
    void gray(){
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);
        ColorMatrixColorFilter colorMatrixColorFilter = new ColorMatrixColorFilter(colorMatrix);
        mIvRotate.setColorFilter(colorMatrixColorFilter);
    }
    @OnClick(R.id.button3)
    void clear(){
        mIvRotate.setColorFilter(null);
    }
}
