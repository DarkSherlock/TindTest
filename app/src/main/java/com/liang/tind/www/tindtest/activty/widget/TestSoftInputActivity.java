package com.liang.tind.www.tindtest.activty.widget;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

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
        fragmentTransaction.replace(R.id.scrollView,fragment).commit();
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
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

      @Override
      public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
          super.onViewCreated(view, savedInstanceState);
          Button close = view.findViewById(R.id.btn_close);
          close.setOnClickListener(v -> requireActivity().finish());
      }
  }

    private boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] location = {0, 0};
            v.getLocationOnScreen(location);
            int left = location[0];
            int top = location[1];
            Log.d(TAG, "getLocationOnScreen(): left = " + location[0] + "  top=" + location[1]);

            if (event.getX() < left || (event.getX() > left + v.getWidth())
                    || event.getY() < top || (event.getY() > top + v.getHeight())) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
