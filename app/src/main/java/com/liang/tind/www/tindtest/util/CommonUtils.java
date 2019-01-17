package com.liang.tind.www.tindtest.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.net.Uri;
import android.os.Looper;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2015/11/23.
 */
public class CommonUtils {

    public static void validateMainThread() {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            throw new IllegalStateException("Must be called from the main thread.");
        }
    }

    private static final long KB_SIZE = 1024;
    private static final long MB_SIZE = 1024 * KB_SIZE;
    private static final long GB_SIZE = 1024 * MB_SIZE;

    /**
     * 将文件大小转换成方便阅读的格式
     *
     * @param size 以byte为单位的大小
     * @return
     */
    public static String getSize(long size) {
        String format;
        if (size >= GB_SIZE) {
            format = String.format("%.1fG", (float) size / GB_SIZE);
        } else if (size >= MB_SIZE) {
            format = String.format("%.1fM", (float) size / MB_SIZE);
        } else if (size >= KB_SIZE) {
            format = String.format("%.1fK", (float) size / KB_SIZE);
        } else {
            format = size + "B";
        }

        return format.replace(".0", "");
    }

    /**
     * 检测网络是否可用
     *
     * @return
     */
//    public static boolean isNetworkAvaiable() {
//        Context context = AppFactory.instance().getApplicationContext();
//        if (context != null) {
//            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
//            if (mNetworkInfo != null) {
//                return mNetworkInfo.isAvailable();
//            }
//        }
//
//        return false;
//    }


    /**
     * 关键字高亮显示
     *
     * @param keyword 需要高亮的关键字
     * @param text    需要显示的文字
     * @return spannable 处理完后的结果，记得不要toString()，否则没有效果
     */
    public static SpannableStringBuilder highlight(String text, String keyword, int color) {
        SpannableStringBuilder spannable = new SpannableStringBuilder(text);
        CharacterStyle span = null;

        Pattern p = Pattern.compile(keyword, Pattern.LITERAL | Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(text);
        while (m.find()) {
            span = new ForegroundColorSpan(color);// 需要重复！
            spannable.setSpan(span, m.start(), m.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannable;
    }

    /**
     * 关键字高亮显示
     *
     * @param keyword 需要高亮的关键字
     * @param cs      需要显示的文字
     * @return spannable 处理完后的结果，记得不要toString()，否则没有效果
     */
    public static SpannableStringBuilder highlightWithEmoji(CharSequence cs, String keyword, int color) {
        SpannableStringBuilder spannable = new SpannableStringBuilder(cs);
        CharacterStyle span = null;

        Pattern p = Pattern.compile(keyword, Pattern.LITERAL | Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(cs);
        while (m.find()) {
            span = new ForegroundColorSpan(color);// 需要重复！
            spannable.setSpan(span, m.start(), m.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannable;
    }

    /**
     * 关键字高亮显示
     *
     * @param keyword 需要高亮的关键字
     * @param cs      需要显示的文字
     * @return spannable 处理完后的结果，记得不要toString()，否则没有效果
     */
    public static SpannableStringBuilder highlightTextWithEmoji(CharSequence cs, String keyword, int color) {
        SpannableStringBuilder spannable = new SpannableStringBuilder(cs);
        CharacterStyle span = null;

        Pattern p = Pattern.compile(keyword, Pattern.LITERAL | Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(cs);
        if (m.find()) {
            span = new ForegroundColorSpan(color);// 需要重复！
            spannable.setSpan(span, m.start(), m.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannable;
    }

    public static boolean isContainkeyword(CharSequence cs, String keyword) {
        Pattern p = Pattern.compile(keyword, Pattern.LITERAL | Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(cs);
        if (m.find()) {
            return true;
        }
        return false;
    }

//    public static SpannableStringBuilder stringToEmoji(String label, TextView textView) {
//        CharSequence cs = EmotionManager.getInstance().decode(label, (int) textView.getTextSize(), (int) textView.getTextSize());
//        SpannableStringBuilder builder = new SpannableStringBuilder();
//        builder.append(cs);
//        return builder;
//    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取状态栏高度—
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {

        int statusBarHeight = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }

    /**
     * 获取屏幕宽高
     *
     * @param context 上下文
     * @return  屏幕宽高
     */
    public static Point getScreenSize(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        Point point = new Point();
        wm.getDefaultDisplay().getSize(point);
        return point;
    }

    /**
     * 对URL进行 uri.encode
     *
     * @param string
     * @return
     */
    public static String encodeString(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        return Uri.encode(string,"utf-8");
    }

    /**
     * 对URL进行 uri.encode
     * 对单引号特殊处理
     * @param string
     * @return
     */
    public static String encodeStringOf27(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        String encodeString = Uri.encode(string,"utf-8");
        if (encodeString.trim().equals("'")||encodeString.trim().contains("'")){
            encodeString = encodeString.replaceAll("\'","%27");
        }
        return encodeString;
    }

    /**
     * 弹出软键盘
     */
    public static void toggleSoftInput(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static void showSoftInput(final Activity activity, final EditText editText) {
        editText.postDelayed(new Runnable() {
            @Override
            public void run() {
                ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE))
                        .showSoftInput(editText, 0);
            }
        }, 100);
    }

    public static void hideSoftInput(final Context context, final View view) {
        if(view == null){
            return;
        }
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) context
                        .getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }, 30);
    }

    /**
     * Created by Administrator .
     * 获取当前系统语言环境
     *
     * @return String
     */
    public static String getCurrSysLanguage() {
        String lang = Locale.getDefault().getLanguage();
        switch (lang) {
            case "zh":
                return "zh-CN";
            case "in":
                return "id";
            case "th":
                return "th";
            default:
                return "en";
        }
    }

    /**
     * 判断是否为生僻字
     * @param str
     * @return
     */
    public static boolean isChineseByREG(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[\u4e00-\u9fa5]*$");
        return pattern.matcher(str.trim()).find();
    }

//    public static String getDeviceID() {
//        return Settings.Secure.getString(AppFactory.instance().getApplicationContext().getContentResolver(),
//                Settings.Secure.ANDROID_ID);
//    }

    public static Activity getCurrentActivity() {
        Class activityThreadClass = null;
        try {
            activityThreadClass = Class.forName("android.app.ActivityThread");
            Object activityThread = activityThreadClass.getMethod("currentActivityThread").invoke(null);
            Field activitiesField = activityThreadClass.getDeclaredField("mActivities");
            activitiesField.setAccessible(true);
            Map activities = (Map) activitiesField.get(activityThread);
            for (Object activityRecord : activities.values()) {
                Class activityRecordClass = activityRecord.getClass();
                Field pausedField = activityRecordClass.getDeclaredField("paused");
                pausedField.setAccessible(true);
                if (!pausedField.getBoolean(activityRecord)) {
                    Field activityField = activityRecordClass.getDeclaredField("activity");
                    activityField.setAccessible(true);
                    Activity activity = (Activity) activityField.get(activityRecord);
                    return activity;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }

}
