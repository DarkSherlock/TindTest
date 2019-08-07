package com.liang.tind.www.tindtest.activty.widget;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liang.tind.www.tindtest.R;
import com.liang.tind.www.tindtest.base.BaseActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

/**
 * desc
 * created by liangtiande
 * date 2019/7/26
 */
public class TestSoftInputActivity  extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_soft_input;
    }

    @Override
    protected void init() {
        SoftInputFragment fragment = SoftInputFragment.newInstance();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container,fragment).commit();
    }

  public   static class SoftInputFragment extends Fragment{
        public static SoftInputFragment newInstance() {
            Bundle args = new Bundle();
            SoftInputFragment fragment = new SoftInputFragment();
            fragment.setArguments(args);
            return fragment;
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_soft_inpt,container,false);
            return view;
        }
    }
}
