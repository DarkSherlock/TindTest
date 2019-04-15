package com.liang.tind.www.tindtest.activty.widget;

import android.content.res.ColorStateList;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.SwitchCompat;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.liang.tind.www.tindtest.R;
import com.liang.tind.www.tindtest.base.BaseActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * desc
 * created by liangtiande
 * date 2018/12/27
 */
public class TestWidgetActivity extends BaseActivity {
    private static final String RICHTXT_MSG = "<div style=\"color:red;text-decoration:underline;\">\n" +
            "  <span>这是一段文本消息[sys:1001]</span> \n " +
            "  <img src=\"dentry://2392138091230921\" mime=\"jpeg\" width=\"240\" height=\"320\" encoding='zip' size=\"1201024\" alt=\"图片说明\" md5=\"bcb31b38e4c01691881e38023dea69e9\" />\n" +
            "  <br /> <span>这是另一段文本消息</span>\n" +
            "</div>";

    @Override
    protected int getLayoutId() {
        return R.layout.layout_test;
    }

    @Override
    protected void init() {
        TextView textView = findViewById(R.id.text);
        textView.setText("Fuck this shit code,Fuck 艹");
        StringBuilder stringBuilder = new StringBuilder();

        String[] filter = new String[]{"Fuck", "shit", "bitch", "草", "艹"};
        String text = textView.getText().toString();

        for (String aFilter : filter) {
            if (text.contains(aFilter)) {
                text = text.replaceAll(aFilter, "**");
            }
//            String replaceAll = text.replaceAll(aFilter, "**");
//            Log.e(TAG, "init(TestWidgetActivity.java:39): "+replaceAll);
        }
        Log.e(TAG, "init(TestWidgetActivity.java:41): text = " + text);


        //"<font color='red' size='20'>"+names.get(i)+"</font>"

        stringBuilder.append("<font color='red' size='20'>");
        stringBuilder.append(textView.getText());
        stringBuilder.append("</font>");
//        textView.setText(Html.fromHtml(stringBuilder.toString()));
        textView.setText(Html.fromHtml(RICHTXT_MSG));
        Log.i(TAG, "init(TestWidgetActivity.java:30): " + textView.getText());

        String test = "Hello the <tag>Fucking<tag/> <tag>Fucking<tag/> shit <tag>world<tag/>a123 !";
        Matcher matcher = Pattern.compile("<tag>.*?<tag/>").matcher(test);
        while (matcher.find()) {
            String group0 = matcher.group(0);
            int end = matcher.end();
            int start = matcher.start();
            test = test.replaceFirst(group0, "<font color='red' size='20'>" +
                    "***" +
                    "</font>");
        }

        textView.setText(Html.fromHtml(test));


        SwitchCompat switchCompat = findViewById(R.id.switch_anonymous_question);
//        setSwitchColor(switchCompat);
    }


    public static void setSwitchColor(SwitchCompat v) {

        // thumb color
        int thumbColor = 0xffff0000;

        // trackColor
        int trackColor = 0xffdddddd;

        // set the thumb color
        DrawableCompat.setTintList(v.getThumbDrawable(), new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_checked},
                        new int[]{}
                },
                new int[]{
                        thumbColor,
                        thumbColor
                }));

        // set the track color
        DrawableCompat.setTintList(v.getTrackDrawable(), new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_checked},
                        new int[]{}
                },
                new int[]{
                        thumbColor,
                        trackColor
                }));


    }

}
