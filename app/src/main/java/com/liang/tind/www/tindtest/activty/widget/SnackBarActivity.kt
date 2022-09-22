package com.liang.tind.www.tindtest.activty.widget

import android.widget.Button
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar
import com.liang.tind.www.tindtest.R
import com.liang.tind.www.tindtest.base.BaseActivity

/**
 *@author lonnie.liang
 *@date 2021/05/08 10:49
 *
 */
class SnackBarActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_snack_bar
    }

    override fun init() {
        val showBtn = findViewById<Button>(R.id.btn_show)
        val rootView = findViewById<CoordinatorLayout>(R.id.root)
        showBtn.setOnClickListener {
            Snackbar.make(rootView, "test", Snackbar.LENGTH_SHORT).show()
        }
    }
}