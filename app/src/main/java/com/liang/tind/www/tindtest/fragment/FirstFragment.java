package com.liang.tind.www.tindtest.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * desc
 * created by liangtiande
 * date 2018/10/19
 */
public class FirstFragment extends Fragment {
    private static final String TAG = "FirstFragment";


    public static FirstFragment newInstance() {
        FirstFragment fragment = new FirstFragment();
        return fragment;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i(TAG, "onAttach(FirstFragment.java:29): ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate(FirstFragment.java:39): ");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView(FirstFragment.java:48): ");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated()");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated(FirstFragment.java:55): ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart(FirstFragment.java:61): ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume(FirstFragment.java:67): ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause(FirstFragment.java:73): ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop(FirstFragment.java:79): ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView(FirstFragment.java:85): ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy(FirstFragment.java:91): ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, "onDetach(FirstFragment.java:97): ");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState(): ");
    }
}
