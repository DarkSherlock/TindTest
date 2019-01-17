package com.liang.tind.www.tindtest.widget.mpchart.interfaces.dataprovider;


import com.liang.tind.www.tindtest.widget.mpchart.data.BarData;

public interface BarDataProvider extends BarLineScatterCandleBubbleDataProvider {

    BarData getBarData();
    boolean isDrawBarShadowEnabled();
    boolean isDrawValueAboveBarEnabled();
    boolean isHighlightFullBarEnabled();
}
