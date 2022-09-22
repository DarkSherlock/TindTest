package com.liang.tind.www.tindtest.activty.widget

import android.animation.ObjectAnimator
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.ImageView
import com.liang.tind.www.tindtest.R
import com.liang.tind.www.tindtest.base.BaseActivity

/**
 *@author lonnie.liang
 *@date 2021/03/25 17:14
 *
 */
class LevelListDrawableActivity : BaseActivity() {
    private lateinit var animator: ObjectAnimator
    private var level = 1

    override fun getLayoutId(): Int {
        return R.layout.activity_level_list_drawable
    }

    override fun init() {
        val imageView: ImageView = findViewById(R.id.imageView)
        animator = ObjectAnimator.ofInt(imageView, "imageLevel", 0, 3).apply {
            repeatCount = ObjectAnimator.INFINITE
            interpolator = LinearInterpolator()
            repeatMode = ObjectAnimator.RESTART
            duration = 600
        }

        val startButton: Button = findViewById(R.id.start)
        startButton.setOnClickListener {
            animator.start()
        }
    }
}