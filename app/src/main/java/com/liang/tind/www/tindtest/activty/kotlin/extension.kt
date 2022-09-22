package com.liang.tind.www.tindtest.activty.kotlin

import android.view.View

/**
 *@author lonnie.liang
 *@date 2020/07/31 15:17
 *
 */

inline fun View.setOnSingleClickListener(delayMillis: Long = 500, crossinline onClick: (v: View) -> Unit) {
    this.setOnClickListener {
        if (this.isClickable){
            this.isClickable = false
            onClick(this)
            this.postDelayed({
                this.isClickable = true
            }, delayMillis)
        }
    }
}