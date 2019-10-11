package com.liang.tind.www.tindtest.base;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    protected String TAG;

    protected void showToast(@NonNull CharSequence sequence) {
        Toast.makeText(this, sequence, Toast.LENGTH_SHORT).show();
    }

    protected void showToast(@StringRes int resId) {
        Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
        int layoutId = getLayoutId();
        if (layoutId != 0){
            setContentView(layoutId);
        }
        ButterKnife.bind(this);
        init();
    }


    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void init();
}
