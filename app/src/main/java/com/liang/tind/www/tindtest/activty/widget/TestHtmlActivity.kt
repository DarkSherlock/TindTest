package com.liang.tind.www.tindtest.activty.widget

import androidx.core.text.HtmlCompat
import com.liang.tind.www.tindtest.R
import com.liang.tind.www.tindtest.base.BaseActivity
import kotlinx.android.synthetic.main.activity_test_html.*

/**
 *@author lonnie.liang
 *@date 2021/04/16 10:59
 *
 */
class TestHtmlActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_test_html
    }

    override fun init() {
        textView.text = HtmlCompat.fromHtml(
            getString(R.string.html_message, "Hey ring"),
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )
    }
}