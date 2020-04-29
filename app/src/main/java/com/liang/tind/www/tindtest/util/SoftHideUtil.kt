package com.liang.tind.www.tindtest.util

import android.app.Activity
import android.graphics.Rect
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ScrollView
import com.liang.tind.www.tindtest.R

/**
 *@author lonnie.liang
 *@date 2020/03/17 20:20
 *
 */
object SoftHideUtil{
    fun enableAdjustResizeInFullscreen(activity: Activity) {
        val content = activity.findViewById<View>(android.R.id.content) as? FrameLayout ?: return
        val childOfContent = content.getChildAt(0)
        var usableHeightPrevious = 0
        val rootScroll: ScrollView = activity.findViewById(R.id.scrollView)
        val frameLayoutParams = childOfContent.layoutParams as? FrameLayout.LayoutParams ?: return

        fun computeUsableHeight(): Int {
            val r = Rect()
            childOfContent.getWindowVisibleDisplayFrame(r)
            return r.bottom - r.top
        }

        fun possiblyResizeChildOfContent() {
            val usableHeightNow = computeUsableHeight()
            Log.i("tind", "usableHeightNow=$usableHeightNow")
            if (usableHeightNow != usableHeightPrevious) {
                val usableHeightSansKeyboard = childOfContent.rootView.height
                Log.i("tind", "usableHeightSansKeyboard=$usableHeightSansKeyboard")
                val heightDifference = usableHeightSansKeyboard - usableHeightNow
                if (heightDifference > usableHeightSansKeyboard / 4) {
                    // keyboard probably just became visible
                    frameLayoutParams.height = usableHeightSansKeyboard - heightDifference
                    Log.i("tind", "1====>frameLayoutParams.height=${frameLayoutParams.height}")
                } else {
                    // keyboard probably just became hidden
                    frameLayoutParams.height = usableHeightSansKeyboard
                    Log.i("tind", "2++++++>frameLayoutParams.height=$usableHeightSansKeyboard")
                }

                childOfContent.requestLayout()
                usableHeightPrevious = usableHeightNow
            }
        }

        childOfContent.viewTreeObserver.addOnGlobalLayoutListener {
            possiblyResizeChildOfContent()
            rootScroll.post {
                rootScroll.fling(0)
                rootScroll.fullScroll(View.FOCUS_DOWN)
            }
        }
    }
}