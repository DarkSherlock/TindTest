package com.liang.tind.www.tindtest.util;

import com.liang.tind.www.tindtest.base.BaseActivity;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author: lonnie.liang
 * date: 2022/09/16 14:25
 */
class Data<VH extends RecyclerView.ViewHolder, E extends BaseActivity> {

    private class Test<E extends BaseActivity> {
        private Data<? extends RecyclerView.ViewHolder, E> data;

        private void test() {

        }
    }
}
