package com.liang.tind.www.tindtest.widget.chart;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Administrator on 2018/3/19.
 */

public interface ChartTypeDef {
    int LEFT = -1;
    int RIGHT = 1;

    int WEEK_DIVIDE = 6;
    int MOUTH_DIVIDE = 29;

    @IntDef(value = {LEFT, RIGHT})
    @Retention(RetentionPolicy.SOURCE)
    @interface ChartButtonAlign {}

    @IntDef(value = {WEEK_DIVIDE, MOUTH_DIVIDE})
    @Retention(RetentionPolicy.SOURCE)
    @interface ChartAxisDivide {}

}
